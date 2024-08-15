<?php

namespace App\Controller;

use App\Entity\Cluster;
use App\Form\Cluster1Type;
use App\Repository\ClusterRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/cluster')]
class Cluster2Controller extends AbstractController
{
    #[Route('/', name: 'app_cluster', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $clusters = $entityManager
            ->getRepository(Cluster::class)
            ->findAll();

            $filteredclusters = [];
    $uniqueSecondParts = [];
    $filter = $request->query->get('filter');
    foreach ($clusters as $Cluster) {
        $idParts = explode('_', $Cluster->getId());

        if (count($idParts) >= 3) {
            $secondPart = $idParts[1];

            if (empty($filter) || $secondPart === $filter) {
                $filteredclusters[] = $Cluster;
            }

            if (!in_array($secondPart, $uniqueSecondParts)) {
                $uniqueSecondParts[] = $secondPart;
            }
        }
    }


    $ClusterRepository = $entityManager->getRepository(Cluster::class);

    $supportValues = [

        'Supprimé' => $ClusterRepository->getClusterCountByEtat('Supprimé'),
        'Modifié' => $ClusterRepository->getClusterCountByEtat('Modifié'),
        'Nouveau' => $ClusterRepository->getClusterCountByEtat('Nouveau'),
        'Inchangé' => $ClusterRepository->getClusterCountByEtat('Inchangé'),
    ];



    $pagination = $paginator->paginate(
        $filteredclusters, // Query
        $request->query->getInt('page', 1), // Page number
        10 // Items per page
    );
        return $this->render('cluster2/index.html.twig', [
            'clusters' =>$pagination ,
            'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
        'supportValues' => $supportValues,
        ]);
    }

    #[Route('/new', name: 'app_cluster2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $cluster = new Cluster();
        $form = $this->createForm(Cluster1Type::class, $cluster);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($cluster);
            $entityManager->flush();

            return $this->redirectToRoute('app_cluster', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('cluster2/new.html.twig', [
            'cluster' => $cluster,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_cluster2_show', methods: ['GET'])]
    public function show(Cluster $cluster): Response
    {
        return $this->render('cluster2/show.html.twig', [
            'cluster' => $cluster,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_cluster2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Cluster $cluster, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Cluster1Type::class, $cluster);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_cluster', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('cluster2/edit.html.twig', [
            'cluster' => $cluster,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_cluster2_delete', methods: ['POST'])]
    public function delete(Request $request, Cluster $cluster, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$cluster->getId(), $request->request->get('_token'))) {
            $entityManager->remove($cluster);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_cluster', [], Response::HTTP_SEE_OTHER);
    }
}
