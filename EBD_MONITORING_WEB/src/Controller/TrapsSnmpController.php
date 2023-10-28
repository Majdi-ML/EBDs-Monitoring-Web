<?php

namespace App\Controller;

use App\Entity\TrapsSnmp;
use App\Form\TrapsSnmpType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/admin/trapssnmp')]
class TrapsSnmpController extends AbstractController
{
    #[Route('/', name: 'app_traps_snmp_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $trapsSnmpsRepository = $entityManager->getRepository(trapsSnmp::class);
        $trapsSnmps = $trapsSnmpsRepository->findAll();
    
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
    
            'Supprimé' => $trapsSnmpsRepository->getTrapsSnmpCountByEtat('Supprimé'),
            'Modifié' => $trapsSnmpsRepository->getTrapsSnmpCountByEtat('Modifié'),
            'Nouveau' => $trapsSnmpsRepository->getTrapsSnmpCountByEtat('Nouveau'),
            'Inchangé' => $trapsSnmpsRepository->getTrapsSnmpCountByEtat('Inchangé'),
        ];
    
        $supportValues = [
        
            'Version 1' => $trapsSnmpsRepository->getTrapsSnmpCountByVersionSnmp('Version 1'),
            'Version 2' => $trapsSnmpsRepository->getTrapsSnmpCountByVersionSnmp('Version 2'),

        ];
        $chartos = [
    
            'Critique' => $trapsSnmpsRepository->getTrapsSnmpCountBycriticite('Critique'),
            'Majeure' => $trapsSnmpsRepository->getTrapsSnmpCountBycriticite('Majeure'),
            'Normale' => $trapsSnmpsRepository->getTrapsSnmpCountBycriticite('Normale'),
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

    #[Route('/new', name: 'app_traps_snmp_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $trapsSnmp = new TrapsSnmp();
        $form = $this->createForm(TrapsSnmpType::class, $trapsSnmp);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($trapsSnmp);
            $entityManager->flush();

            return $this->redirectToRoute('app_traps_snmp_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('traps_snmp/new.html.twig', [
            'traps_snmp' => $trapsSnmp,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_traps_snmp_show', methods: ['GET'])]
    public function show(TrapsSnmp $trapsSnmp): Response
    {
        return $this->render('traps_snmp/show.html.twig', [
            'traps_snmp' => $trapsSnmp,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_traps_snmp_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, TrapsSnmp $trapsSnmp, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TrapsSnmpType::class, $trapsSnmp);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_traps_snmp_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('traps_snmp/edit.html.twig', [
            'traps_snmp' => $trapsSnmp,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_traps_snmp_delete', methods: ['POST'])]
    public function delete(Request $request, TrapsSnmp $trapsSnmp, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$trapsSnmp->getId(), $request->request->get('_token'))) {
            $entityManager->remove($trapsSnmp);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_traps_snmp_index', [], Response::HTTP_SEE_OTHER);
    }
}
