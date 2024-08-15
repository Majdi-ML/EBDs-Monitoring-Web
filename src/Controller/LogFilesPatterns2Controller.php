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
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/logfilespatterns')]
class LogFilesPatterns2Controller extends AbstractController
{
    #[Route('/', name: 'app_log_files_patterns', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $LogFilesPatternsRepository = $entityManager->getRepository(LogFilesPatterns::class);
        $LogFilesPatterns = $LogFilesPatternsRepository->findAll();
        $user = $this->getUser();
        $role = $user->getRoles()[0]; // ROLE_Billing
        $equipe = substr($role, strlen('ROLE_'));

    $filteredLogFilesPatterns = [];
    $uniqueSecondParts = [];

    $filter = $request->query->get('filter');

    foreach ($LogFilesPatterns as $LogFilesPattern) {
        $idParts = explode('_', $LogFilesPattern->getId());

        if (count($idParts) >= 3) {
            $secondPart = $idParts[1];

            if (empty($filter) || $secondPart === $filter) {
                $filteredLogFilesPatterns[] = $LogFilesPattern;
            }

            if (!in_array($secondPart, $uniqueSecondParts)) {
                $uniqueSecondParts[] = $secondPart;
            }
        }
    }
    $supportValues = [

        'Supprimé' => $LogFilesPatternsRepository->getLogFilePatternsCountByEtatAndUser('Supprimé', $equipe),
        'Modifié' => $LogFilesPatternsRepository->getLogFilePatternsCountByEtatAndUser('Modifié', $equipe),
        'Nouveau' => $LogFilesPatternsRepository->getLogFilePatternsCountByEtatAndUser('Nouveau', $equipe),
        'Inchangé' => $LogFilesPatternsRepository->getLogFilePatternsCountByEtatAndUser('Inchangé', $equipe),
    ];

    $chartos = [

        'Critique' => $LogFilesPatternsRepository->getLogFilePatternsCountBycriticiteAndUser('Critique', $equipe),
        'Majeure' => $LogFilesPatternsRepository->getLogFilePatternsCountBycriticiteAndUser('Majeure', $equipe),
        'Normale' => $LogFilesPatternsRepository->getLogFilePatternsCountBycriticiteAndUser('Normale', $equipe),
    ];

    $pagination = $paginator->paginate(
        $filteredLogFilesPatterns, // Query
        $request->query->getInt('page', 1), // Page number
        10 // Items per page
    );



    return $this->render('log_files_patterns2/index.html.twig', [
        'log_files_patterns' => $pagination,
        'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
        'supportValues'=> $supportValues,
        'chartos'=> $chartos,
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
