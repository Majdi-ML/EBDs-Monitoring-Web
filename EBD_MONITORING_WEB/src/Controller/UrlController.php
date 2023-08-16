<?php

namespace App\Controller;

use App\Entity\Url;
use App\Form\UrlType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/url')]
class UrlController extends AbstractController
{
    #[Route('/autreroles', name: 'app_url', methods: ['GET'])]
public function afficherurl(EntityManagerInterface $entityManager): Response
{
    $user = $this->getUser();
    $userRoles = $user->getRoles();
    
    $allowedSupports = ['CLOUD', 'AppIT', 'BI']; // List of allowed supports for non-admin roles
    
    if (in_array('ROLE_ADMIN', $userRoles)) {
        $urls = $entityManager
            ->getRepository(Url::class)
            ->findAll();
    } else {
        $support = null;
        foreach ($allowedSupports as $allowedSupport) {
            if (in_array('ROLE_' . strtoupper(str_replace(' ', '_', $allowedSupport)), $userRoles)) {
                $support = $allowedSupport;
                break;
            }
        }
        
        if (!$support) {
            throw new \Exception('User role not mapped to allowed supports.');
        }
        
        $urls = $entityManager
            ->getRepository(Url::class)
            ->findBySupport($support);
    }

    return $this->render('url/index.html.twig', [
        'urls' => $urls,
    ]);
}


    #[Route('/', name: 'app_url_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $urls = $entityManager
            ->getRepository(Url::class)
            ->findAll();

        return $this->render('url/index.html.twig', [
            'urls' => $urls,
        ]);
    }

    #[Route('/new', name: 'app_url_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $url = new Url();
        $form = $this->createForm(UrlType::class, $url);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($url);
            $entityManager->flush();

            return $this->redirectToRoute('app_url_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('url/new.html.twig', [
            'url' => $url,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_url_show', methods: ['GET'])]
    public function show(Url $url): Response
    {
        return $this->render('url/show.html.twig', [
            'url' => $url,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_url_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Url $url, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(UrlType::class, $url);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_url_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('url/edit.html.twig', [
            'url' => $url,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_url_delete', methods: ['POST'])]
    public function delete(Request $request, Url $url, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$url->getId(), $request->request->get('_token'))) {
            $entityManager->remove($url);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_url_index', [], Response::HTTP_SEE_OTHER);
    }
}
