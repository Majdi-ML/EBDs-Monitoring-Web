<?php

namespace App\Controller;

use App\Entity\Process;
use App\Form\ProcessType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/admin/process')]
class ProcessController extends AbstractController
{
    #[Route('/', name: 'app_process_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $ProcessesRepository = $entityManager->getRepository(Process::class);
    $Processes = $ProcessesRepository->findAll();

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

        'Supprimé' => $ProcessesRepository->getProcessCountByEtat('Supprimé'),
        'Modifié' => $ProcessesRepository->getProcessCountByEtat('Modifié'),
        'Nouveau' => $ProcessesRepository->getProcessCountByEtat('Nouveau'),
        'Inchangé' => $ProcessesRepository->getProcessCountByEtat('Inchangé'),
    ];

    $supportValues = [
    
        'OMU' => $ProcessesRepository->getProcessCountByMonotoring('OMU'),
        'Sitescope 1' => $ProcessesRepository->getProcessCountByMonotoring('Sitescope 1'),
        'Sitescope 2' => $ProcessesRepository->getProcessCountByMonotoring('Sitescope 2'),
        'NNMI' => $ProcessesRepository->getProcessCountByMonotoring('NNMI'),
        'RUM' => $ProcessesRepository->getProcessCountByMonotoring('RUM'),
        'BPM' => $ProcessesRepository->getProcessCountByMonotoring('BPM'),
    ];
    $chartos = [

        'Critique' => $ProcessesRepository->getProcessCountBycriticite('Critique'),
        'Majeure' => $ProcessesRepository->getProcessCountBycriticite('Majeure'),
        'Normale' => $ProcessesRepository->getProcessCountBycriticite('Normale'),
    ];

    $pagination = $paginator->paginate(
        $filteredProcesses, // Query
        $request->query->getInt('page', 1), // Page number
        10 // Items per page
    );



    return $this->render('process/index.html.twig', [
        'Process' => $pagination,
        'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
        'supportValues' => $supportValues,
        'chartData'=> $chartData,
        'chartos'=> $chartos,
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
