<?php

namespace App\Controller;

use App\Entity\Scripts;
use App\Form\ScriptsType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/admin/scripts')]
class ScriptsController extends AbstractController
{
    #[Route('/', name: 'app_scripts_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $scripts = $entityManager
            ->getRepository(Scripts::class)
            ->findAll();

        return $this->render('scripts/index.html.twig', [
            'scripts' => $scripts,
        ]);
    }

    #[Route('/new', name: 'app_scripts_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $script = new Scripts();
        $form = $this->createForm(ScriptsType::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($script);
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts/new.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts_show', methods: ['GET'])]
    public function show(Scripts $script): Response
    {
        return $this->render('scripts/show.html.twig', [
            'script' => $script,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_scripts_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ScriptsType::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts/edit.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts_delete', methods: ['POST'])]
    public function delete(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$script->getId(), $request->request->get('_token'))) {
            $entityManager->remove($script);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_scripts_index', [], Response::HTTP_SEE_OTHER);
    }
}
