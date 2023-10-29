<?php

namespace App\Controller;

use App\Entity\Scripts;
use App\Form\Scripts1Type;
use App\Repository\ScriptsRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/ooredoo/scripts')]
class Scripts2Controller extends AbstractController
{
    #[Route('/', name: 'app_scripts', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $ScriptsRepository = $entityManager->getRepository(Scripts::class);
        $Scripts = $ScriptsRepository->findAll();
    
        $user = $this->getUser();
        $role = $user->getRoles()[0]; // ROLE_Billing
        $equipe = substr($role, strlen('ROLE_'));
        $filteredScriptss = [];
        $uniqueSecondParts = [];
    
        $filter = $request->query->get('filter');
    
        foreach ($Scripts as $Script) {
            $idParts = explode('_', $Script->getId());
    
            if (count($idParts) >= 3) {
                $secondPart = $idParts[1];
    
                if (empty($filter) || $secondPart === $filter) {
                    $filteredScripts[] = $Script;
                }
    
                if (!in_array($secondPart, $uniqueSecondParts)) {
                    $uniqueSecondParts[] = $secondPart;
                }
            }
        }
        $supportValues = [
    
            'Supprimé' => $ScriptsRepository->getScriptsCountByEtatAndUser('Supprimé', $equipe),
            'Modifié' => $ScriptsRepository->getScriptsCountByEtatAndUser('Modifié', $equipe),
            'Nouveau' => $ScriptsRepository->getScriptsCountByEtatAndUser('Nouveau', $equipe),
            'Inchangé' => $ScriptsRepository->getScriptsCountByEtatAndUser('Inchangé', $equipe),
        ];
    
        $chartData = [
        
            'OMU' => $ScriptsRepository->getScriptsCountByMonotoringAndUser('OMU', $equipe),
            'Sitescope 1' => $ScriptsRepository->getScriptsCountByMonotoringAndUser('Sitescope 1', $equipe),
            'Sitescope 2' => $ScriptsRepository->getScriptsCountByMonotoringAndUser('Sitescope 2', $equipe),
            'NNMI' => $ScriptsRepository->getScriptsCountByMonotoringAndUser('NNMI', $equipe),
            'RUM' => $ScriptsRepository->getScriptsCountByMonotoringAndUser('RUM', $equipe),
            'BPM' => $ScriptsRepository->getScriptsCountByMonotoringAndUser('BPM', $equipe),
        ];
        $chartos = [
    
            'Critique' => $ScriptsRepository->getScriptsCountBycriticiteAndUser('Critique', $equipe),
            'Majeure' => $ScriptsRepository->getScriptsCountBycriticiteAndUser('Majeure', $equipe),
            'Normale' => $ScriptsRepository->getScriptsCountBycriticiteAndUser('Normale', $equipe),
        ];
    
        $pagination = $paginator->paginate(
            $filteredScripts, // Query
            $request->query->getInt('page', 1), // Page number
            10 // Items per page
        );
    
    
    
        return $this->render('scripts2/index.html.twig', [
            'scripts' => $pagination,
            'filter' => $filter,
            'uniqueSecondParts' => $uniqueSecondParts,
            'supportValues' => $supportValues,
            'chartData'=> $chartData,
            'chartos'=> $chartos,
        ]);
    }

    #[Route('/new', name: 'app_scripts2_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $repository = $entityManager->getRepository(Scripts::class);
        $script = new Scripts();
        $form = $this->createForm(Scripts1Type::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($script);
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts2/new.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts2_show', methods: ['GET'])]
    public function show(Scripts $script): Response
    {
        return $this->render('scripts2/show.html.twig', [
            'script' => $script,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_scripts2_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Scripts1Type::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts2/edit.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts2_delete', methods: ['POST'])]
    public function delete(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$script->getId(), $request->request->get('_token'))) {
            $entityManager->remove($script);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_scripts', [], Response::HTTP_SEE_OTHER);
    }
}
