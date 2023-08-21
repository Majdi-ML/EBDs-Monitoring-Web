<?php

namespace App\Controller;

use App\Entity\LogFiles;
use App\Form\LogFilesType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/admin/logfiles')]
class LogFilesController extends AbstractController
{
    #[Route('/', name: 'app_log_files_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $logFiles = $entityManager
            ->getRepository(LogFiles::class)
            ->findAll();

        return $this->render('log_files/index.html.twig', [
            'log_files' => $logFiles,
        ]);
    }

    #[Route('/new', name: 'app_log_files_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $logFile = new LogFiles();
        $form = $this->createForm(LogFilesType::class, $logFile);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($logFile);
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files/new.html.twig', [
            'log_file' => $logFile,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files_show', methods: ['GET'])]
    public function show(LogFiles $logFile): Response
    {
        return $this->render('log_files/show.html.twig', [
            'log_file' => $logFile,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_log_files_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, LogFiles $logFile, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(LogFilesType::class, $logFile);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files/edit.html.twig', [
            'log_file' => $logFile,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files_delete', methods: ['POST'])]
    public function delete(Request $request, LogFiles $logFile, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$logFile->getId(), $request->request->get('_token'))) {
            $entityManager->remove($logFile);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_log_files_index', [], Response::HTTP_SEE_OTHER);
    }
}
