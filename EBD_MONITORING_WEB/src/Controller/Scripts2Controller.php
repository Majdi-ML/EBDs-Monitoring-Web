<?php

namespace App\Controller;

use App\Entity\Scripts;
use App\Form\Scripts1Type;
use App\Repository\ScriptsRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/scripts')]
class Scripts2Controller extends AbstractController
{
    #[Route('/', name: 'app_scripts', methods: ['GET'])]
    public function index(ScriptsRepository $scriptsRepository): Response
    {
        return $this->render('scripts2/index.html.twig', [
            'scripts' => $scriptsRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_scripts2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $script = new Scripts();
        $form = $this->createForm(Scripts1Type::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($script);
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts2/new.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts2_show', methods: ['GET'])]
    public function show(Scripts $script): Response
    {
        return $this->render('scripts2/show.html.twig', [
            'script' => $script,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_scripts2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Scripts1Type::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts2/edit.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts2_delete', methods: ['POST'])]
    public function delete(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$script->getId(), $request->request->get('_token'))) {
            $entityManager->remove($script);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_scripts', [], Response::HTTP_SEE_OTHER);
    }
}
