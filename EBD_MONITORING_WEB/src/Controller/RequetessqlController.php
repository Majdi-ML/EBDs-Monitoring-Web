<?php

namespace App\Controller;

use App\Entity\Requetessql;
use App\Form\RequetessqlType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/admin/requetessql')]
class RequetessqlController extends AbstractController
{
    #[Route('/', name: 'app_requetessql_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $requetessqls = $entityManager
            ->getRepository(Requetessql::class)
            ->findAll();

        return $this->render('requetessql/index.html.twig', [
            'requetessqls' => $requetessqls,
        ]);
    }

    #[Route('/new', name: 'app_requetessql_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $requetessql = new Requetessql();
        $form = $this->createForm(RequetessqlType::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($requetessql);
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql/new.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql_show', methods: ['GET'])]
    public function show(Requetessql $requetessql): Response
    {
        return $this->render('requetessql/show.html.twig', [
            'requetessql' => $requetessql,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_requetessql_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(RequetessqlType::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql/edit.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql_delete', methods: ['POST'])]
    public function delete(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$requetessql->getId(), $request->request->get('_token'))) {
            $entityManager->remove($requetessql);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_requetessql_index', [], Response::HTTP_SEE_OTHER);
    }
}
