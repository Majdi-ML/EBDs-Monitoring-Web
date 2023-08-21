<?php

namespace App\Controller;

use App\Entity\LogFilesPatterns;
use App\Form\LogFilesPatterns1Type;
use App\Repository\LogFilesPatternsRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/logfilespatterns')]
class LogFilesPatterns2Controller extends AbstractController
{
    #[Route('/', name: 'app_log_files_patterns', methods: ['GET'])]
    public function index(LogFilesPatternsRepository $logFilesPatternsRepository): Response
    {
        return $this->render('log_files_patterns2/index.html.twig', [
            'log_files_patterns' => $logFilesPatternsRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_log_files_patterns2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $logFilesPattern = new LogFilesPatterns();
        $form = $this->createForm(LogFilesPatterns1Type::class, $logFilesPattern);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($logFilesPattern);
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files_patterns', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files_patterns2/new.html.twig', [
            'log_files_pattern' => $logFilesPattern,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files_patterns2_show', methods: ['GET'])]
    public function show(LogFilesPatterns $logFilesPattern): Response
    {
        return $this->render('log_files_patterns2/show.html.twig', [
            'log_files_pattern' => $logFilesPattern,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_log_files_patterns2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, LogFilesPatterns $logFilesPattern, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(LogFilesPatterns1Type::class, $logFilesPattern);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files_patterns', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files_patterns2/edit.html.twig', [
            'log_files_pattern' => $logFilesPattern,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files_patterns2_delete', methods: ['POST'])]
    public function delete(Request $request, LogFilesPatterns $logFilesPattern, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$logFilesPattern->getId(), $request->request->get('_token'))) {
            $entityManager->remove($logFilesPattern);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_log_files_patterns', [], Response::HTTP_SEE_OTHER);
    }
}
