<?php

namespace App\Controller;

use App\Entity\TrapsSnmp;
use App\Form\TrapsSnmp1Type;
use App\Repository\TrapsSnmpRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/trapssnmp')]
class TrapsSnmp2Controller extends AbstractController
{
    #[Route('/', name: 'app_traps_snmp', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $trapsSnmpsRepository = $entityManager->getRepository(trapsSnmp::class);
        $trapsSnmps = $trapsSnmpsRepository->findAll();
        
        $user = $this->getUser();
        $role = $user->getRoles()[0]; // ROLE_Billing
        $equipe = substr($role, strlen('ROLE_'));
    
        $filteredtrapsSnmps = [];
        $uniqueSecondParts = [];
    
        $filter = $request->query->get('filter');
    
        foreach ($trapsSnmps as $trapsSnmp) {
            $idParts = explode('_', $trapsSnmp->getId());
    
            if (count($idParts) >= 3) {
                $secondPart = $idParts[1];
    
                if (empty($filter) || $secondPart === $filter) {
                    $filteredtrapsSnmps[] = $trapsSnmp;
                }
    
                if (!in_array($secondPart, $uniqueSecondParts)) {
                    $uniqueSecondParts[] = $secondPart;
                }
            }
        }
        $supportValues = [
    
            'Supprimé' => $trapsSnmpsRepository->getTrapsSnmpCountByEtatAndUser('Supprimé'),
            'Modifié' => $trapsSnmpsRepository->getTrapsSnmpCountByEtatAndUser('Modifié'),
            'Nouveau' => $trapsSnmpsRepository->getTrapsSnmpCountByEtatAndUser('Nouveau'),
            'Inchangé' => $trapsSnmpsRepository->getTrapsSnmpCountByEtatAndUser('Inchangé'),
        ];
    
        $supportValues = [
        
            'Version 1' => $trapsSnmpsRepository->getTrapsSnmpCountByVersionSnmpAndUser('Version 1'),
            'Version 2' => $trapsSnmpsRepository->getTrapsSnmpCountByVersionSnmpAndUser('Version 2'),

        ];
        $chartos = [
    
            'Critique' => $trapsSnmpsRepository->getTrapsSnmpCountBycriticiteAndUser('Critique'),
            'Majeure' => $trapsSnmpsRepository->getTrapsSnmpCountBycriticiteAndUser('Majeure'),
            'Normale' => $trapsSnmpsRepository->getTrapsSnmpCountBycriticiteAndUser('Normale'),
        ];
    
        $pagination = $paginator->paginate(
            $filteredtrapsSnmps, // Query
            $request->query->getInt('page', 1), // Page number
            10 // Items per page
        );
    
    
    
        return $this->render('traps_snmp/index.html.twig', [
            'traps_snmp' => $pagination,
            'filter' => $filter,
            'uniqueSecondParts' => $uniqueSecondParts,
            'supportValues' => $supportValues,
            'chartData'=> $chartData,
            'chartos'=> $chartos,
        ]);
    }

    #[Route('/new', name: 'app_traps_snmp2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $trapsSnmp = new TrapsSnmp();
        $form = $this->createForm(TrapsSnmp1Type::class, $trapsSnmp);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($trapsSnmp);
            $entityManager->flush();

            return $this->redirectToRoute('app_traps_snmp', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('traps_snmp2/new.html.twig', [
            'traps_snmp' => $trapsSnmp,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_traps_snmp2_show', methods: ['GET'])]
    public function show(TrapsSnmp $trapsSnmp): Response
    {
        return $this->render('traps_snmp2/show.html.twig', [
            'traps_snmp' => $trapsSnmp,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_traps_snmp2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, TrapsSnmp $trapsSnmp, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TrapsSnmp1Type::class, $trapsSnmp);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_traps_snmp', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('traps_snmp2/edit.html.twig', [
            'traps_snmp' => $trapsSnmp,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_traps_snmp2_delete', methods: ['POST'])]
    public function delete(Request $request, TrapsSnmp $trapsSnmp, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$trapsSnmp->getId(), $request->request->get('_token'))) {
            $entityManager->remove($trapsSnmp);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_traps_snmp', [], Response::HTTP_SEE_OTHER);
    }
}
