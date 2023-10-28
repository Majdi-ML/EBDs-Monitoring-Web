<?php

namespace App\Controller;

use App\Entity\Url;
use App\Form\Url1Type;
use App\Repository\UrlRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/url')]
class Url2Controller extends AbstractController
{
    #[Route('/', name: 'app_url', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $UrlsRepository = $entityManager->getRepository(Urls::class);
        $Urls = $UrlssRepository->findAll();
    
        $user = $this->getUser();
        $role = $user->getRoles()[0]; // ROLE_Billing
        $equipe = substr($role, strlen('ROLE_'));

        $filteredUrlss = [];
        $uniqueSecondParts = [];
    
        $filter = $request->query->get('filter');
    
        foreach ($Urls as $Url) {
            $idParts = explode('_', $Url->getId());
    
            if (count($idParts) >= 3) {
                $secondPart = $idParts[1];
    
                if (empty($filter) || $secondPart === $filter) {
                    $filteredUrls[] = $Url;
                }
    
                if (!in_array($secondPart, $uniqueSecondParts)) {
                    $uniqueSecondParts[] = $secondPart;
                }
            }
        }
        $supportValues = [
    
            'Supprimé' => $UrlsRepository->getUrlCountByEtatAndUser('Supprimé'),
            'Modifié' => $UrlsRepository->getUrlCountByEtatAndUser('Modifié'),
            'Nouveau' => $UrlsRepository->getUrlCountByEtatAndUser('Nouveau'),
            'Inchangé' => $UrlsRepository->getUrlCountByEtatAndUser('Inchangé'),
        ];
    
        $supportValues = [
        
            'OMU' => $UrlsRepository->getUrlCountByMonotoringAndUser('OMU'),
            'Sitescope 1' => $UrlsRepository->getUrlCountByMonotoringAndUser('Sitescope 1'),
            'Sitescope 2' => $UrlsRepository->getUrlCountByMonotoringAndUser('Sitescope 2'),
            'NNMI' => $UrlsRepository->getUrlCountByMonotoringAndUser('NNMI'),
            'RUM' => $UrlsRepository->getUrlCountByMonotoringAndUser('RUM'),
            'BPM' => $UrlsRepository->getUrlCountByMonotoringAndUser('BPM'),
        ];
        $chartos = [
    
            'Critique' => $UrlsRepository->getUrlCountBycriticiteAndUser('Critique'),
            'Majeure' => $UrlsRepository->getUrlCountBycriticiteAndUser('Majeure'),
            'Normale' => $UrlsRepository->getUrlCountBycriticiteAndUser('Normale'),
        ];
    
        $pagination = $paginator->paginate(
            $filteredUrls, // Query
            $request->query->getInt('page', 1), // Page number
            10 // Items per page
        );
    
    
    
        return $this->render('url/index.html.twig', [
            'Urls' => $pagination,
            'filter' => $filter,
            'uniqueSecondParts' => $uniqueSecondParts,
            'supportValues' => $supportValues,
            'chartData'=> $chartData,
            'chartos'=> $chartos,
        ]);
    }

    #[Route('/new', name: 'app_url2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $url = new Url();
        $form = $this->createForm(Url1Type::class, $url);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($url);
            $entityManager->flush();

            return $this->redirectToRoute('app_url', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('url2/new.html.twig', [
            'url' => $url,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_url2_show', methods: ['GET'])]
    public function show(Url $url): Response
    {
        return $this->render('url2/show.html.twig', [
            'url' => $url,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_url2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Url $url, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Url1Type::class, $url);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_url', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('url2/edit.html.twig', [
            'url' => $url,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_url2_delete', methods: ['POST'])]
    public function delete(Request $request, Url $url, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$url->getId(), $request->request->get('_token'))) {
            $entityManager->remove($url);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_url', [], Response::HTTP_SEE_OTHER);
    }
}
