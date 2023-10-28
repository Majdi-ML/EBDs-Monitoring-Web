<?php

namespace App\Controller;

use App\Entity\Url;
use App\Form\UrlType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/admin/url')]
class UrlController extends AbstractController
{
   #[Route('/', name: 'app_url_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $UrlsRepository = $entityManager->getRepository(Urls::class);
        $Urls = $UrlssRepository->findAll();
    
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
    
            'Supprimé' => $UrlsRepository->getUrlCountByEtat('Supprimé'),
            'Modifié' => $UrlsRepository->getUrlCountByEtat('Modifié'),
            'Nouveau' => $UrlsRepository->getUrlCountByEtat('Nouveau'),
            'Inchangé' => $UrlsRepository->getUrlCountByEtat('Inchangé'),
        ];
    
        $supportValues = [
        
            'OMU' => $UrlsRepository->getUrlCountByMonotoring('OMU'),
            'Sitescope 1' => $UrlsRepository->getUrlCountByMonotoring('Sitescope 1'),
            'Sitescope 2' => $UrlsRepository->getUrlCountByMonotoring('Sitescope 2'),
            'NNMI' => $UrlsRepository->getUrlCountByMonotoring('NNMI'),
            'RUM' => $UrlsRepository->getUrlCountByMonotoring('RUM'),
            'BPM' => $UrlsRepository->getUrlCountByMonotoring('BPM'),
        ];
        $chartos = [
    
            'Critique' => $UrlsRepository->getUrlCountBycriticite('Critique'),
            'Majeure' => $UrlsRepository->getUrlCountBycriticite('Majeure'),
            'Normale' => $UrlsRepository->getUrlCountBycriticite('Normale'),
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
