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

#[Route('/ooredoo/url')]
class Url2Controller extends AbstractController
{
    #[Route('/', name: 'app_url', methods: ['GET'])]
    public function index(UrlRepository $urlRepository): Response
    {
        return $this->render('url2/index.html.twig', [
            'urls' => $urlRepository->findAll(),
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
