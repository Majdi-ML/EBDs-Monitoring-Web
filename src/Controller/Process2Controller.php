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
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/process')]
class Process2Controller extends AbstractController
{
    #[Route('/', name: 'app_process', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $ProcessesRepository = $entityManager->getRepository(Process::class);
    $Processes = $ProcessesRepository->findAll();
    $user = $this->getUser();
        $role = $user->getRoles()[0]; // ROLE_Billing
        $equipe = substr($role, strlen('ROLE_'));


    $filteredProcesses = [];
    $uniqueSecondParts = [];

    $filter = $request->query->get('filter');

    foreach ($Processes as $Process) {
        $idParts = explode('_', $Process->getId());

        if (count($idParts) >= 3) {
            $secondPart = $idParts[1];

            if (empty($filter) || $secondPart === $filter) {
                $filteredProcesses[] = $Process;
            }

            if (!in_array($secondPart, $uniqueSecondParts)) {
                $uniqueSecondParts[] = $secondPart;
            }
        }
    }
    $supportValues = [

        'Supprimé' => $ProcessesRepository->getProcessCountByEtatAndUser('Supprimé', $equipe),
        'Modifié' => $ProcessesRepository->getProcessCountByEtatAndUser('Modifié', $equipe),
        'Nouveau' => $ProcessesRepository->getProcessCountByEtatAndUser('Nouveau', $equipe),
        'Inchangé' => $ProcessesRepository->getProcessCountByEtatAndUser('Inchangé', $equipe),
    ];

    $chartData = [
    
        'OMU' => $ProcessesRepository->getProcessCountByMonotoringAndUser('OMU', $equipe),
        'Sitescope 1' => $ProcessesRepository->getProcessCountByMonotoringAndUser('Sitescope 1', $equipe),
        'Sitescope 2' => $ProcessesRepository->getProcessCountByMonotoringAndUser('Sitescope 2', $equipe),
        'NNMI' => $ProcessesRepository->getProcessCountByMonotoringAndUser('NNMI', $equipe),
        'RUM' => $ProcessesRepository->getProcessCountByMonotoringAndUser('RUM', $equipe),
        'BPM' => $ProcessesRepository->getProcessCountByMonotoringAndUser('BPM', $equipe),
    ];
    $chartos = [

        'Critique' => $ProcessesRepository->getProcessCountBycriticiteAndUser('Critique', $equipe),
        'Majeure' => $ProcessesRepository->getProcessCountBycriticiteAndUser('Majeure', $equipe),
        'Normale' => $ProcessesRepository->getProcessCountBycriticiteAndUser('Normale', $equipe),
    ];

    $pagination = $paginator->paginate(
        $filteredProcesses, // Query
        $request->query->getInt('page', 1), // Page number
        10 // Items per page
    );



    return $this->render('process2/index.html.twig', [
        'processes' => $pagination,
        'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
        'supportValues' => $supportValues,
        'chartData'=> $chartData,
        'chartos'=> $chartos,
    ]);
    }

    #[Route('/new', name: 'app_process2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $process = new Process();
        $form = $this->createForm(Process1Type::class, $process);
        $form->handleRequest($request);

        $user = $this->getUser();
             $role = $user->getRoles()[0]; // ROLE_Billing

            // // Afficher la valeur de $role pour le débogage
            $equipe = substr($role, strlen('ROLE_'));
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
