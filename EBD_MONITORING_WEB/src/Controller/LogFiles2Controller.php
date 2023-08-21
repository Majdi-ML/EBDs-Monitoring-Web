<?php

namespace App\Controller;

use App\Entity\LogFiles;
use App\Form\LogFiles1Type;
use App\Repository\LogFilesRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/logfiles')]
class LogFiles2Controller extends AbstractController
{
    #[Route('/', name: 'app_log_files', methods: ['GET'])]
    public function index(LogFilesRepository $logFilesRepository): Response
    {
        return $this->render('log_files2/index.html.twig', [
            'log_files' => $logFilesRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_log_files2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $logFile = new LogFiles();
        $form = $this->createForm(LogFiles1Type::class, $logFile);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($logFile);
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files2/new.html.twig', [
            'log_file' => $logFile,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files2_show', methods: ['GET'])]
    public function show(LogFiles $logFile): Response
    {
        return $this->render('log_files2/show.html.twig', [
            'log_file' => $logFile,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_log_files2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, LogFiles $logFile, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(LogFiles1Type::class, $logFile);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files2/edit.html.twig', [
            'log_file' => $logFile,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files2_delete', methods: ['POST'])]
    public function delete(Request $request, LogFiles $logFile, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$logFile->getId(), $request->request->get('_token'))) {
            $entityManager->remove($logFile);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_log_files', [], Response::HTTP_SEE_OTHER);
    }
}
