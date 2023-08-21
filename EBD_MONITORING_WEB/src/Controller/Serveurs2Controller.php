<?php

namespace App\Controller;

use App\Entity\Serveurs;
use App\Form\Serveurs1Type;
use App\Repository\ServeursRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/serveurs')]
class Serveurs2Controller extends AbstractController
{
    #[Route('/', name: 'app_serveurs', methods: ['GET'])]
    public function index(ServeursRepository $serveursRepository): Response
    {
        return $this->render('serveurs2/index.html.twig', [
            'serveurs' => $serveursRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_serveurs2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $serveur = new Serveurs();
        $form = $this->createForm(Serveurs1Type::class, $serveur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($serveur);
            $entityManager->flush();

            return $this->redirectToRoute('app_serveurs', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs2/new.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs2_show', methods: ['GET'])]
    public function show(Serveurs $serveur): Response
    {
        return $this->render('serveurs2/show.html.twig', [
            'serveur' => $serveur,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_serveurs2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Serveurs1Type::class, $serveur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_serveurs', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs2/edit.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs2_delete', methods: ['POST'])]
    public function delete(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$serveur->getId(), $request->request->get('_token'))) {
            $entityManager->remove($serveur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_serveurs', [], Response::HTTP_SEE_OTHER);
    }
}
