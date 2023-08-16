<?php

namespace App\Controller;

use App\Entity\Serveurs;
use App\Form\ServeursType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ooredoo/serveurs')]
class ServeursController extends AbstractController
{
    #[Route('/autreroles', name: 'app_serveurs', methods: ['GET'])]
public function afficherserveurs(EntityManagerInterface $entityManager): Response
{
    $user = $this->getUser();
    $userRoles = $user->getRoles();
    
    $allowedSupports = ['CLOUD', 'AppIT', 'BI']; // List of allowed supports for non-admin roles
    
    if (in_array('ROLE_ADMIN', $userRoles)) {
        $serveurs = $entityManager
            ->getRepository(Serveurs::class)
            ->findAll();
    } else {
        $support = null;
        foreach ($allowedSupports as $allowedSupport) {
            if (in_array('ROLE_' . strtoupper(str_replace(' ', '_', $allowedSupport)), $userRoles)) {
                $support = $allowedSupport;
                break;
            }
        }
        
        if (!$support) {
            throw new \Exception('User role not mapped to allowed supports.');
        }
        
        $serveurs = $entityManager
            ->getRepository(Serveurs::class)
            ->findBySupport($support);
    }

    return $this->render('serveurs/index.html.twig', [
        'serveurs' => $serveurs,
    ]);
}

    
    #[Route('/', name: 'app_serveurs_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager): Response
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

    return $this->render('serveurs/index.html.twig', [
        'serveurs' => $filteredServeurs,
        'filter' => $filter,
        'uniqueSecondParts' => $uniqueSecondParts,
    ]);
}

    #[Route('/new', name: 'app_serveurs_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $serveur = new Serveurs();
        $form = $this->createForm(ServeursType::class, $serveur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
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
