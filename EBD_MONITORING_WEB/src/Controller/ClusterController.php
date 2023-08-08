<?php

namespace App\Controller;

use App\Entity\Cluster;
use App\Form\ClusterType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/cluster')]
class ClusterController extends AbstractController
{
    #[Route('/', name: 'app_cluster_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $clusters = $entityManager
            ->getRepository(Cluster::class)
            ->findAll();

        return $this->render('cluster/index.html.twig', [
            'clusters' => $clusters,
        ]);
    }

    #[Route('/new', name: 'app_cluster_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $cluster = new Cluster();
        $form = $this->createForm(ClusterType::class, $cluster);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($cluster);
            $entityManager->flush();

            return $this->redirectToRoute('app_cluster_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('cluster/new.html.twig', [
            'cluster' => $cluster,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_cluster_show', methods: ['GET'])]
    public function show(Cluster $cluster): Response
    {
        return $this->render('cluster/show.html.twig', [
            'cluster' => $cluster,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_cluster_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Cluster $cluster, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ClusterType::class, $cluster);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_cluster_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('cluster/edit.html.twig', [
            'cluster' => $cluster,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_cluster_delete', methods: ['POST'])]
    public function delete(Request $request, Cluster $cluster, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$cluster->getId(), $request->request->get('_token'))) {
            $entityManager->remove($cluster);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_cluster_index', [], Response::HTTP_SEE_OTHER);
    }
}
