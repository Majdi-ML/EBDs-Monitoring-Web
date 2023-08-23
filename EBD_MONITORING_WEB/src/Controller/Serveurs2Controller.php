<?php

namespace App\Controller;

use App\Entity\Serveurs;
use App\Form\Serveurs1Type;
use App\Repository\ServeursRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;


#[Route('/ooredoo/serveurs')]
class Serveurs2Controller extends AbstractController
{
    #[Route('/', name: 'app_serveurs', methods: ['GET'])]
    public function index(ServeursRepository $serveursRepository,Request $request,PaginatorInterface $paginator): Response
    {
      
    $serveurs = $serveursRepository->findAll();
    $user = $this->getUser();
    $role = $user->getRoles()[0]; // ROLE_Billing
    $equipe = substr($role, strlen('ROLE_'));
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

    

    $supportValues = [

        'Supprimé' => $serveursRepository->getServeursCountByEtat2('Supprimé',$equipe),
        'Modifié' => $serveursRepository->getServeursCountByEtat2('Modifié',$equipe),
        'Nouveau' => $serveursRepository->getServeursCountByEtat2('Nouveau',$equipe),
        'Inchangé' => $serveursRepository->getServeursCountByEtat2('Inchangé',$equipe),
    ];

    $chartData = [

        'Prod' => $serveursRepository->getServeursCountByPlatfomre2('Prod',$equipe),
        'Pré-Prod' => $serveursRepository->getServeursCountByPlatfomre2('Pré-Prod',$equipe),
    ];

    $chartos = [

        'AIX' => $serveursRepository->getServeursCountByOs2('AIX',$equipe),
        'HPUX' => $serveursRepository->getServeursCountByOs2('HPUX',$equipe),
        'Linux' => $serveursRepository->getServeursCountByOs2('Linux',$equipe),
        'Solaris' => $serveursRepository->getServeursCountByOs2('Solaris',$equipe),
        'Windows' => $serveursRepository->getServeursCountByOs2('Windows',$equipe),
    ];

    $pagination = $paginator->paginate(
        $filteredServeurs, // Query
        $request->query->getInt('page', 1), // Page number
        4 // Items per page
    );

        return $this->render('serveurs2/index.html.twig', [
            'serveurs' => $filteredServeurs,
        'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
        'supportValues' => $supportValues,
        'chartData'=> $chartData,
        'chartos'=> $chartos,
        ]);
    }

    #[Route('/new', name: 'app_serveurs2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $repository = $entityManager->getRepository(Serveurs::class);
        $serveur = new Serveurs();
        $form = $this->createForm(Serveurs1Type::class, $serveur);
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
            $serveur->setSupport($equipe);

            $entityManager->persist($serveur);
            $entityManager->flush();
    

            return $this->redirectToRoute('app_serveurs', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs2/new.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs2_show', methods: ['GET'])]
    public function show(Serveurs $serveur): Response
    {
        return $this->render('serveurs2/show.html.twig', [
            'serveur' => $serveur,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_serveurs2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Serveurs1Type::class, $serveur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_serveurs', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('serveurs2/edit.html.twig', [
            'serveur' => $serveur,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_serveurs2_delete', methods: ['POST'])]
    public function delete(Request $request, Serveurs $serveur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$serveur->getId(), $request->request->get('_token'))) {
            $entityManager->remove($serveur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_serveurs', [], Response::HTTP_SEE_OTHER);
    }
}
