<?php

namespace App\Controller;

use App\Entity\LogFilesPatterns;
use App\Form\LogFilesPatternsType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/logfilespatterns')]
class LogFilesPatternsController extends AbstractController
{
    #[Route('/autreroles', name: 'app_log_files_patterns', methods: ['GET'])]
    public function afficher(EntityManagerInterface $entityManager): Response
    {
       
        $user = $this->getUser();
        $userRoles = $user->getRoles();
        
        $allowedSupports = ['CLOUD', 'AppIT', 'BI']; // List of allowed supports for non-admin roles
        
        if (in_array('ROLE_ADMIN', $userRoles)) {
            $logFilesPatterns = $entityManager
                ->getRepository(LogFilesPatterns::class)
                ->findAll();
        } else {
            $support = null;
            foreach ($allowedSupports as $allowedSupport) {
                if (in_array('ROLE_' . strtoupper(str_replace(' ', '_', $allowedSupport)), $userRoles)) {
                    $support = $allowedSupport;
                    break;
                }
            }
            
            if (!$support) {
                throw new \Exception('User role not mapped to allowed supports.');
            }
            
            $logFilesPatterns = $entityManager
                ->getRepository(LogFilesPatterns::class)
                ->findBySupport($support);
        }

        return $this->render('log_files_patterns/index.html.twig', [
            'log_files_patterns' => $logFilesPatterns,
        ]);
    
    }
    #[Route('/admin', name: 'app_log_files_patterns_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $logFilesPatterns = $entityManager
            ->getRepository(LogFilesPatterns::class)
            ->findAll();

        return $this->render('log_files_patterns/index.html.twig', [
            'log_files_patterns' => $logFilesPatterns,
        ]);
    }

    #[Route('/new', name: 'app_log_files_patterns_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $logFilesPattern = new LogFilesPatterns();
        $form = $this->createForm(LogFilesPatternsType::class, $logFilesPattern);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($logFilesPattern);
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files_patterns_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files_patterns/new.html.twig', [
            'log_files_pattern' => $logFilesPattern,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files_patterns_show', methods: ['GET'])]
    public function show(LogFilesPatterns $logFilesPattern): Response
    {
        return $this->render('log_files_patterns/show.html.twig', [
            'log_files_pattern' => $logFilesPattern,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_log_files_patterns_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, LogFilesPatterns $logFilesPattern, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(LogFilesPatternsType::class, $logFilesPattern);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_log_files_patterns_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('log_files_patterns/edit.html.twig', [
            'log_files_pattern' => $logFilesPattern,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_log_files_patterns_delete', methods: ['POST'])]
    public function delete(Request $request, LogFilesPatterns $logFilesPattern, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$logFilesPattern->getId(), $request->request->get('_token'))) {
            $entityManager->remove($logFilesPattern);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_log_files_patterns_index', [], Response::HTTP_SEE_OTHER);
    }
}
