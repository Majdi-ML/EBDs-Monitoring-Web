<?php

namespace App\Controller;

use App\Entity\Requetessql;
use App\Form\Requetessql1Type;
use App\Repository\RequetessqlRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;


#[Route('/ooredoo/requetessql')]
class Requetessql2Controller extends AbstractController
{
    #[Route('/', name: 'app_requetessql', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        
        $Requetessqls = $entityManager->getRepository(Requetessql::class)->findAll();
        $user = $this->getUser();
        $role = $user->getRoles()[0]; // ROLE_Billing
        $equipe = substr($role, strlen('ROLE_'));

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
        $RequetessqlsRepository = $entityManager->getRepository(Requetessql::class);
        $supportValues = [
    
            'Supprimé' => $RequetessqlsRepository->getRequeteSqlCountByEtatAndUser('Supprimé', $equipe),
            'Modifié' => $RequetessqlsRepository->getRequeteSqlCountByEtatAndUser('Modifié', $equipe),
            'Nouveau' => $RequetessqlsRepository->getRequeteSqlCountByEtatAndUser('Nouveau', $equipe),
            'Inchangé' => $RequetessqlsRepository->getRequeteSqlCountByEtatAndUser('Inchangé', $equipe),
        ];
    
        $chartData = [
        
            'OMU' => $RequetessqlsRepository->getRequeteSqlCountByMonotoringAndUser('OMU', $equipe),
            'Sitescope 1' => $RequetessqlsRepository->getRequeteSqlCountByMonotoringAndUser('Sitescope 1', $equipe),
            'Sitescope 2' => $RequetessqlsRepository->getRequeteSqlCountByMonotoringAndUser('Sitescope 2', $equipe),
            'NNMI' => $RequetessqlsRepository->getRequeteSqlCountByMonotoringAndUser('NNMI', $equipe),
            'RUM' => $RequetessqlsRepository->getRequeteSqlCountByMonotoringAndUser('RUM', $equipe),
            'BPM' => $RequetessqlsRepository->getRequeteSqlCountByMonotoringAndUser('BPM', $equipe),
        ];
        $chartos = [
    
            'Critique' => $RequetessqlsRepository->getRequeteSqlCountBycriticiteAndUser('Critique', $equipe),
            'Majeure' => $RequetessqlsRepository->getRequeteSqlCountBycriticiteAndUser('Majeure', $equipe),
            'Normale' => $RequetessqlsRepository->getRequeteSqlCountBycriticiteAndUser('Normale', $equipe),
        ];
    
        
    
    
        return $this->render('requetessql2/index.html.twig', [
            'requetessqls' => $filteredRequetessqls,
            'filter' => $filter,
            'uniqueSecondParts' => $uniqueSecondParts,
            'supportValues' => $supportValues,
            'chartData'=> $chartData,
            'chartos'=> $chartos,
        ]);
    }
    #[Route('/new', name: 'app_requetessql2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $requetessql = new Requetessql();
        $form = $this->createForm(Requetessql1Type::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($requetessql);
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql2/new.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql2_show', methods: ['GET'])]
    public function show(Requetessql $requetessql): Response
    {
        return $this->render('requetessql2/show.html.twig', [
            'requetessql' => $requetessql,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_requetessql2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Requetessql1Type::class, $requetessql);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_requetessql', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('requetessql2/edit.html.twig', [
            'requetessql' => $requetessql,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_requetessql2_delete', methods: ['POST'])]
    public function delete(Request $request, Requetessql $requetessql, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$requetessql->getId(), $request->request->get('_token'))) {
            $entityManager->remove($requetessql);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_requetessql', [], Response::HTTP_SEE_OTHER);
    }
}
