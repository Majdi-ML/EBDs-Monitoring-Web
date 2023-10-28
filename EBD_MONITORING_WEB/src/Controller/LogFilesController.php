<?php

namespace App\Controller;

use App\Entity\LogFiles;
use App\Form\LogFilesType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/admin/logfiles')]
class LogFilesController extends AbstractController
{
    #[Route('/', name: 'app_log_files_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $logFiles = $entityManager->getRepository(LogFiles::class)->findAll();
        $filteredLogFiles = [];
        $uniqueSecondParts = [];
    
        $filter = $request->query->get('filter');
    
        foreach ($logFiles as $LogFile) {
            $idParts = explode('_', $LogFile->getId());
    
            if (count($idParts) >= 3) {
                $secondPart = $idParts[1];
    
                if (empty($filter) || $secondPart === $filter) {
                    $filteredLogFiles[] = $LogFile;
                }
    
                if (!in_array($secondPart, $uniqueSecondParts)) {
                    $uniqueSecondParts[] = $secondPart;
                }
            }
        }
    
        $LogFilesRepository = $entityManager->getRepository(LogFiles::class);
    
        $supportValues = [
    
            'Supprimé' => $LogFilesRepository->getLogFileCountByEtat('Supprimé'),
            'Modifié' => $LogFilesRepository->getLogFileCountByEtat('Modifié'),
            'Nouveau' => $LogFilesRepository->getLogFileCountByEtat('Nouveau'),
            'Inchangé' => $LogFilesRepository->getLogFileCountByEtat('Inchangé'),
        ];
        $chartos = [
    
            'OMU' => $LogFilesRepository->getLogFileCountByMonotoring('OMU'),
            'Sitescope 1' => $LogFilesRepository->getLogFileCountByMonotoring('Sitescope 1'),
            'Sitescope 2' => $LogFilesRepository->getLogFileCountByMonotoring('Sitescope 2'),
            'NNMI' => $LogFilesRepository->getLogFileCountByMonotoring('NNMI'),
            'RUM' => $LogFilesRepository->getLogFileCountByMonotoring('RUM'),
            'BPM' => $LogFilesRepository->getLogFileCountByMonotoring('BPM'),
        ];




        $pagination = $paginator->paginate(
            $filteredLogFiles, // Query
            $request->query->getInt('page', 1), // Page number
            10 // Items per page
        );
        return $this->render('log_files2/index.html.twig', [
            'log_files' => $pagination,
            'filter' => $filter,
            'uniqueSecondParts' => $uniqueSecondParts,
            'supportValues' => $supportValues,
            'chartos'=> $chartos,
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
