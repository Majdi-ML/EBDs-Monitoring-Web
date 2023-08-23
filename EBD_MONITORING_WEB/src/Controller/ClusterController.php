<?php

namespace App\Controller;

use App\Entity\Cluster;
use App\Form\ClusterType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/admin/cluster')]
class ClusterController extends AbstractController
{
    #[Route('/admin', name: 'app_cluster_index', methods: ['GET'])]
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
        return $this->render('cluster/index.html.twig', [
            'clusters' =>$pagination ,
            'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
        'supportValues' => $supportValues,
        ]);
    }

    #[Route('/new', name: 'app_cluster_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $repository = $entityManager->getRepository(Cluster::class);
        $cluster = new Cluster();
        $form = $this->createForm(ClusterType::class, $cluster);
         

        if ($form->isSubmitted() && $form->isValid()) {

            $nomServeur = $form->get('id')->getData(); // Supposons que le champ d'ID s'appelle 'id' dans votre formulaire
            
            $nombreOccurrences = $repository->countByNomServeur($nomServeur);
    
            if ($nombreOccurrences > 0) {
                $nouveauNbr = $nombreOccurrences + 1;
            } else {
                $nouveauNbr = 1;
            }
    
            $nouvelId = "EBD_" . $nomServeur . "_Cluster_" . $nouveauNbr;
            $cluster->setId($nouvelId);
    
            $nouvelref="Rg-" . $nouveauNbr;
            $cluster->setRef($nouvelref);
            
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
