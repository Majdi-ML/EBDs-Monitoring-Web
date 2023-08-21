<?php

namespace App\Controller;

use App\Entity\Requetessql;
use App\Form\Requetessql1Type;
use App\Repository\RequetessqlRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/requetessql')]
class Requetessql2Controller extends AbstractController
{
    #[Route('/', name: 'app_requetessql', methods: ['GET'])]
    public function index(RequetessqlRepository $requetessqlRepository): Response
    {
        return $this->render('requetessql2/index.html.twig', [
            'requetessqls' => $requetessqlRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_requetessql2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $requetessql = new Requetessql();
        $form = $this->createForm(Requetessql1Type::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($requetessql);
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql2/new.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql2_show', methods: ['GET'])]
    public function show(Requetessql $requetessql): Response
    {
        return $this->render('requetessql2/show.html.twig', [
            'requetessql' => $requetessql,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_requetessql2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Requetessql1Type::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql2/edit.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql2_delete', methods: ['POST'])]
    public function delete(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$requetessql->getId(), $request->request->get('_token'))) {
            $entityManager->remove($requetessql);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_requetessql', [], Response::HTTP_SEE_OTHER);
    }
}
