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
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/logfiles')]
class LogFiles2Controller extends AbstractController
{
    #[Route('/', name: 'app_log_files', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $logFiles = $entityManager->getRepository(LogFiles::class)->findAll();
        
        $user = $this->getUser();
        $role = $user->getRoles()[0]; // ROLE_Billing
        $equipe = substr($role, strlen('ROLE_'));

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
    
            'Supprimé' => $LogFilesRepository->getLogFileCountByEtatAndUser('Supprimé', $equipe),
            'Modifié' => $LogFilesRepository->getLogFileCountByEtatAndUser('Modifié', $equipe),
            'Nouveau' => $LogFilesRepository->getLogFileCountByEtatAndUser('Nouveau', $equipe),
            'Inchangé' => $LogFilesRepository->getLogFileCountByEtatAndUser('Inchangé', $equipe),
        ];
        $chartos = [
    
            'OMU' => $LogFilesRepository->getLogFileCountByMonotoringAndUser('OMU', $equipe),
            'Sitescope 1' => $LogFilesRepository->getLogFileCountByMonotoringAndUser('Sitescope 1', $equipe),
            'Sitescope 2' => $LogFilesRepository->getLogFileCountByMonotoringAndUser('Sitescope 2', $equipe),
            'NNMI' => $LogFilesRepository->getLogFileCountByMonotoringAndUser('NNMI', $equipe),
            'RUM' => $LogFilesRepository->getLogFileCountByMonotoringAndUser('RUM', $equipe),
            'BPM' => $LogFilesRepository->getLogFileCountByMonotoringAndUser('BPM', $equipe),
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
