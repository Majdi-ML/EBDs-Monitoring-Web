<?php
namespace App\Security;

use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Symfony\Component\Security\Core\Security;
use Symfony\Component\Security\Http\Authenticator\AbstractLoginFormAuthenticator;
use Symfony\Component\Security\Http\Authenticator\Passport\Badge\CsrfTokenBadge;
use Symfony\Component\Security\Http\Authenticator\Passport\Badge\RememberMeBadge;
use Symfony\Component\Security\Http\Authenticator\Passport\Badge\UserBadge;
use Symfony\Component\Security\Http\Authenticator\Passport\Credentials\PasswordCredentials;
use Symfony\Component\Security\Http\Authenticator\Passport\Passport;
use Symfony\Component\Security\Http\Util\TargetPathTrait;
use Symfony\Component\Ldap\Ldap;
use Symfony\Component\Ldap\Entry;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use App\Entity\User;

class LdapAuthenticator extends AbstractLoginFormAuthenticator
{
    use TargetPathTrait;

    public const LOGIN_ROUTE = 'app_login_ldap';

    public function __construct(
        private UrlGeneratorInterface $urlGenerator,
        private Ldap $ldap,
        private UserRepository $userRepository,
        private EntityManagerInterface $entityManager
    ) {
    }

    private function ldapAuthenticate(string $username, string $password): ?Entry
    {
        if (empty($username)) {
            throw new \Exception('Username is required');
        }
    
        try {
            $ldap = Ldap::create('ext_ldap', [
                'host' => 'ldap.forumsys.com',
                'port' => 389,
            ]);
    
            $ldap->bind('cn=read-only-admin,dc=example,dc=com', 'password');
    
            $query = $ldap->query('dc=example,dc=com', "(uid={$username})");
            $results = $query->execute();
    
            if (count($results) === 0) {
                throw new \Exception('User not found.');
            }
    
            $entry = $results[0];
    
            // Bind with the user credentials to verify the password
            $ldap->bind($entry->getDn(), $password);
    
            return $entry;
        } catch (LdapException $e) {
            // Log exception details if needed
            throw new \Exception('Invalid LDAP credentials.');
        }
    }
    
    public function authenticate(Request $request): Passport
    {
        if ($request->get('auth_type') !== 'ldap') {
            throw new \LogicException('This authenticator should not be called.');
        }
    
        $username = $request->request->get('username', '');
        $password = $request->request->get('password', '');
    
        $entry = $this->ldapAuthenticate($username, $password);
    
        if ($entry) {
            $user = $this->findOrCreateUser($entry, $password);
            return new Passport(
                new UserBadge($user->getEmail()),
                new PasswordCredentials($password),
                [
                    new CsrfTokenBadge('authenticate', $request->request->get('_csrf_token')),
                    new RememberMeBadge(),
                ]
            );
        }
    
        throw new \Exception('Invalid LDAP credentials.');
    }
    


    private function findOrCreateUser(Entry $ldapUser, string $password): User
    {
        $email = $ldapUser->getAttribute('mail')[0];
        $username = $ldapUser->getAttribute('cn')[0];

        $user = $this->userRepository->findOneBy(['email' => $email]);

        if (!$user) {
            $user = new User();
            $user->setEmail($email);
            $user->setUsername($username);
            $user->setRoles(['ROLE_USER']);
            $user->setPassword(password_hash($password, PASSWORD_BCRYPT)); // Hash password for local storage
            $this->entityManager->persist($user);
            $this->entityManager->flush();
        }

        return $user;
    }

    public function onAuthenticationSuccess(Request $request, TokenInterface $token, string $firewallName): ?Response
    {
        if ($targetPath = $this->getTargetPath($request->getSession(), $firewallName)) {
            return new RedirectResponse($targetPath);
        }

        $user = $token->getUser();
        $roles = $user->getRoles();

        if (in_array('ROLE_ADMIN', $roles)) {
            return new RedirectResponse($this->urlGenerator->generate('app_ooredoo'));
        } elseif (in_array('ROLE_USER', $roles)) {
            return new RedirectResponse($this->urlGenerator->generate('app_ooredoo2'));
        }

        throw new \Exception('User role not recognized.');
    }

    protected function getLoginUrl(Request $request): string
    {
        return $this->urlGenerator->generate(self::LOGIN_ROUTE);
    }
}
