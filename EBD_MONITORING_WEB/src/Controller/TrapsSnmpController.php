<?php

namespace App\Controller;

use App\Entity\TrapsSnmp;
use App\Form\TrapsSnmpType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/trapssnmp')]
class TrapsSnmpController extends AbstractController
{
    #[Route('/', name: 'app_traps_snmp_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $trapsSnmps = $entityManager
            ->getRepository(TrapsSnmp::class)
            ->findAll();

        return $this->render('traps_snmp/index.html.twig', [
            'traps_snmps' => $trapsSnmps,
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
