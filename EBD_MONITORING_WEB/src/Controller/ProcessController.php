<?php

namespace App\Controller;

use App\Entity\Process;
use App\Form\ProcessType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/process')]
class ProcessController extends AbstractController
{
    #[Route('/', name: 'app_process_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $processes = $entityManager
            ->getRepository(Process::class)
            ->findAll();

        return $this->render('process/index.html.twig', [
            'processes' => $processes,
        ]);
    }

    #[Route('/new', name: 'app_process_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $process = new Process();
        $form = $this->createForm(ProcessType::class, $process);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($process);
            $entityManager->flush();

            return $this->redirectToRoute('app_process_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('process/new.html.twig', [
            'process' => $process,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_process_show', methods: ['GET'])]
    public function show(Process $process): Response
    {
        return $this->render('process/show.html.twig', [
            'process' => $process,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_process_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Process $process, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ProcessType::class, $process);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_process_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('process/edit.html.twig', [
            'process' => $process,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_process_delete', methods: ['POST'])]
    public function delete(Request $request, Process $process, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$process->getId(), $request->request->get('_token'))) {
            $entityManager->remove($process);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_process_index', [], Response::HTTP_SEE_OTHER);
    }
}
