<?php

namespace App\Controller;

use App\Entity\Process;
use App\Form\Process1Type;
use App\Repository\ProcessRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/process')]
class Process2Controller extends AbstractController
{
    #[Route('/', name: 'app_process', methods: ['GET'])]
    public function index(ProcessRepository $processRepository): Response
    {
        return $this->render('process2/index.html.twig', [
            'processes' => $processRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_process2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $process = new Process();
        $form = $this->createForm(Process1Type::class, $process);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($process);
            $entityManager->flush();

            return $this->redirectToRoute('app_process', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('process2/new.html.twig', [
            'process' => $process,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_process2_show', methods: ['GET'])]
    public function show(Process $process): Response
    {
        return $this->render('process2/show.html.twig', [
            'process' => $process,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_process2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Process $process, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Process1Type::class, $process);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_process', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('process2/edit.html.twig', [
            'process' => $process,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_process2_delete', methods: ['POST'])]
    public function delete(Request $request, Process $process, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$process->getId(), $request->request->get('_token'))) {
            $entityManager->remove($process);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_process', [], Response::HTTP_SEE_OTHER);
    }
}
