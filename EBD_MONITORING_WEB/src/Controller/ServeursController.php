<?php

namespace App\Controller;

use App\Entity\Serveurs;
use App\Form\ServeursType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/serveurs')]
class ServeursController extends AbstractController
{
    #[Route('/', name: 'app_serveurs_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $serveurs = $entityManager
            ->getRepository(Serveurs::class)
            ->findAll();

        return $this->render('serveurs/index.html.twig', [
            'serveurs' => $serveurs,
        ]);
    }

    #[Route('/new', name: 'app_serveurs_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $serveur = new Serveurs();
        $form = $this->createForm(ServeursType::class, $serveur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($serveur);
            $entityManager->flush();

            return $this->redirectToRoute('app_serveurs_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs/new.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs_show', methods: ['GET'])]
    public function show(Serveurs $serveur): Response
    {
        return $this->render('serveurs/show.html.twig', [
            'serveur' => $serveur,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_serveurs_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ServeursType::class, $serveur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_serveurs_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs/edit.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs_delete', methods: ['POST'])]
    public function delete(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$serveur->getId(), $request->request->get('_token'))) {
            $entityManager->remove($serveur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_serveurs_index', [], Response::HTTP_SEE_OTHER);
    }
}
