<?php

namespace App\Controller;

use App\Entity\Serveurs;
use App\Form\ServeursType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/admin/serveurs')]
class ServeursController extends AbstractController
{
    #[Route('/', name: 'app_serveurs_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
{
    $serversRepository = $entityManager->getRepository(Serveurs::class);
    $serveurs = $serversRepository->findAll();

    $filteredServeurs = [];
    $uniqueSecondParts = [];

    $filter = $request->query->get('filter');

    foreach ($serveurs as $serveur) {
        $idParts = explode('_', $serveur->getId());

        if (count($idParts) >= 3) {
            $secondPart = $idParts[1];

            if (empty($filter) || $secondPart === $filter) {
                $filteredServeurs[] = $serveur;
            }

            if (!in_array($secondPart, $uniqueSecondParts)) {
                $uniqueSecondParts[] = $secondPart;
            }
        }
    }

    $serveursRepository = $entityManager->getRepository(Serveurs::class);

    $supportValues = [

        'Supprimé' => $serveursRepository->getServeursCountByEtat('Supprimé'),
        'Modifié' => $serveursRepository->getServeursCountByEtat('Modifié'),
        'Nouveau' => $serveursRepository->getServeursCountByEtat('Nouveau'),
        'Inchangé' => $serveursRepository->getServeursCountByEtat('Inchangé'),
    ];

    $chartData = [

        'Prod' => $serveursRepository->getServeursCountByPlatfomre('Prod'),
        'Pré-Prod' => $serveursRepository->getServeursCountByPlatfomre('Pré-Prod'),
    ];

    $chartos = [

        'AIX' => $serveursRepository->getServeursCountByOs('AIX'),
        'HPUX' => $serveursRepository->getServeursCountByOs('HPUX'),
        'Linux' => $serveursRepository->getServeursCountByOs('Linux'),
        'Solaris' => $serveursRepository->getServeursCountByOs('Solaris'),
        'Windows' => $serveursRepository->getServeursCountByOs('Windows'),
    ];

    $pagination = $paginator->paginate(
        $filteredServeurs, // Query
        $request->query->getInt('page', 1), // Page number
        10 // Items per page
    );



    return $this->render('serveurs/index.html.twig', [
        'serveurs' => $pagination,
        'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
        'supportValues' => $supportValues,
        'chartData'=> $chartData,
        'chartos'=> $chartos,
    ]);
}

    #[Route('/new', name: 'app_serveurs_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $repository = $entityManager->getRepository(Serveurs::class);
        $serveur = new Serveurs();
        $form = $this->createForm(ServeursType::class, $serveur);
        $form->handleRequest($request);

        $user = $this->getUser();
             $role = $user->getRoles()[0]; // ROLE_Billing

            // // Afficher la valeur de $role pour le débogage
            $equipe = substr($role, strlen('ROLE_'));

        if ($form->isSubmitted() && $form->isValid()) {
            $nomServeur = $form->get('id')->getData(); // Supposons que le champ d'ID s'appelle 'id' dans votre formulaire
            
            $nombreOccurrences = $repository->countByNomServeur($nomServeur);
    
            if ($nombreOccurrences > 0) {
                $nouveauNbr = $nombreOccurrences + 1;
            } else {
                $nouveauNbr = 1;
            }
    
            $nouvelId = "EBD_" . $nomServeur . "_Serveurs_" . $nouveauNbr;
            $serveur->setId($nouvelId);
    
            $nouvelref="Sv-" . $nouveauNbr;
            $serveur->setRef($nouvelref);
            
            $entityManager->persist($serveur);
            $entityManager->flush();
    
            return $this->redirectToRoute('app_serveurs_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs/new.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs_show', methods: ['GET'])]
    public function show(Serveurs $serveur): Response
    {
        return $this->render('serveurs/show.html.twig', [
            'serveur' => $serveur,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_serveurs_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ServeursType::class, $serveur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_serveurs_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs/edit.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs_delete', methods: ['POST'])]
    public function delete(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$serveur->getId(), $request->request->get('_token'))) {
            $entityManager->remove($serveur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_serveurs_index', [], Response::HTTP_SEE_OTHER);
    }
}
