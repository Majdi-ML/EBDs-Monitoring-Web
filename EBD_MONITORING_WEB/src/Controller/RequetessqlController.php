<?php

namespace App\Controller;

use App\Entity\Requetessql;
use App\Form\RequetessqlType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/admin/requetessql')]
class RequetessqlController extends AbstractController
{
    #[Route('/', name: 'app_requetessql_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $RequetessqlsRepository = $entityManager->getRepository(Requetessql::class);
        $Requetessqls = $RequetessqlsRepository->findAll();
    
        $filteredRequetessqls = [];
        $uniqueSecondParts = [];
    
        $filter = $request->query->get('filter');
    
        foreach ($Requetessqls as $Requetessql) {
            $idParts = explode('_', $Requetessql->getId());
    
            if (count($idParts) >= 3) {
                $secondPart = $idParts[1];
    
                if (empty($filter) || $secondPart === $filter) {
                    $filteredRequetessqls[] = $Requetessql;
                }
    
                if (!in_array($secondPart, $uniqueSecondParts)) {
                    $uniqueSecondParts[] = $secondPart;
                }
            }
        }
        $supportValues = [
    
            'Supprimé' => $RequetessqlsRepository->getRequetessqlCountByEtat('Supprimé'),
            'Modifié' => $RequetessqlsRepository->getRequetessqlCountByEtat('Modifié'),
            'Nouveau' => $RequetessqlsRepository->getRequetessqlCountByEtat('Nouveau'),
            'Inchangé' => $RequetessqlsRepository->getRequetessqlCountByEtat('Inchangé'),
        ];
    
        $supportValues = [
        
            'OMU' => $RequetessqlsRepository->getRequetessqlCountByMonotoring('OMU'),
            'Sitescope 1' => $RequetessqlsRepository->getRequetessqlCountByMonotoring('Sitescope 1'),
            'Sitescope 2' => $RequetessqlsRepository->getRequetessqlCountByMonotoring('Sitescope 2'),
            'NNMI' => $RequetessqlsRepository->getRequetessqlCountByMonotoring('NNMI'),
            'RUM' => $RequetessqlsRepository->getRequetessqlCountByMonotoring('RUM'),
            'BPM' => $RequetessqlsRepository->getRequetessqlCountByMonotoring('BPM'),
        ];
        $chartos = [
    
            'Critique' => $RequetessqlsRepository->getRequetessqlCountBycriticite('Critique'),
            'Majeure' => $RequetessqlsRepository->getRequetessqlCountBycriticite('Majeure'),
            'Normale' => $RequetessqlsRepository->getRequetessqlCountBycriticite('Normale'),
        ];
    
        $pagination = $paginator->paginate(
            $filteredRequetessqls, // Query
            $request->query->getInt('page', 1), // Page number
            10 // Items per page
        );
    
    
    
        return $this->render('requetessql/index.html.twig', [
            'Requetessqls' => $pagination,
            'filter' => $filter,
            'uniqueSecondParts' => $uniqueSecondParts,
            'supportValues' => $supportValues,
            'chartData'=> $chartData,
            'chartos'=> $chartos,
        ]);
    }

    #[Route('/new', name: 'app_requetessql_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $requetessql = new Requetessql();
        $form = $this->createForm(RequetessqlType::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($requetessql);
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql/new.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql_show', methods: ['GET'])]
    public function show(Requetessql $requetessql): Response
    {
        return $this->render('requetessql/show.html.twig', [
            'requetessql' => $requetessql,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_requetessql_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(RequetessqlType::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql/edit.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql_delete', methods: ['POST'])]
    public function delete(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$requetessql->getId(), $request->request->get('_token'))) {
            $entityManager->remove($requetessql);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_requetessql_index', [], Response::HTTP_SEE_OTHER);
    }
}
