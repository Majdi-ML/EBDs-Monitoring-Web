<?php

namespace App\Controller;

use App\Entity\Scripts;
use App\Form\ScriptsType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
#[Route('/ooredoo/admin/scripts')]
class ScriptsController extends AbstractController
{
    #[Route('/', name: 'app_scripts_index', methods: ['GET'])]
    public function index(Request $request, EntityManagerInterface $entityManager , PaginatorInterface $paginator): Response
    {
        $ScriptsRepository = $entityManager->getRepository(Scripts::class);
        $Scripts = $ScriptsRepository->findAll();
    
        $filteredScripts = [];
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
    
            'Supprimé' => $ScriptsRepository->getScriptsCountByEtat('Supprimé'),
            'Modifié' => $ScriptsRepository->getScriptsCountByEtat('Modifié'),
            'Nouveau' => $ScriptsRepository->getScriptsCountByEtat('Nouveau'),
            'Inchangé' => $ScriptsRepository->getScriptsCountByEtat('Inchangé'),
        ];
    
        $chartData = [
        
            'OMU' => $ScriptsRepository->getScriptsCountByMonotoring('OMU'),
            'Sitescope 1' => $ScriptsRepository->getScriptsCountByMonotoring('Sitescope 1'),
            'Sitescope 2' => $ScriptsRepository->getScriptsCountByMonotoring('Sitescope 2'),
            'NNMI' => $ScriptsRepository->getScriptsCountByMonotoring('NNMI'),
            'RUM' => $ScriptsRepository->getScriptsCountByMonotoring('RUM'),
            'BPM' => $ScriptsRepository->getScriptsCountByMonotoring('BPM'),
        ];
        $chartos = [
    
            'Critique' => $ScriptsRepository->getScriptsCountBycriticite('Critique'),
            'Majeure' => $ScriptsRepository->getScriptsCountBycriticite('Majeure'),
            'Normale' => $ScriptsRepository->getScriptsCountBycriticite('Normale'),
        ];
    
        $pagination = $paginator->paginate(
            $filteredScripts, // Query
            $request->query->getInt('page', 1), // Page number
            10 // Items per page
        );
    
    
    
        return $this->render('scripts/index.html.twig', [
            'scripts' => $pagination,
            'filter' => $filter,
            'uniqueSecondParts' => $uniqueSecondParts,
            'supportValues' => $supportValues,
            'chartData'=> $chartData,
            'chartos'=> $chartos,
        ]);
    }

    #[Route('/new', name: 'app_scripts_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $script = new Scripts();
        $form = $this->createForm(ScriptsType::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($script);
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts/new.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts_show', methods: ['GET'])]
    public function show(Scripts $script): Response
    {
        return $this->render('scripts/show.html.twig', [
            'script' => $script,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_scripts_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ScriptsType::class, $script);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_scripts_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('scripts/edit.html.twig', [
            'script' => $script,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_scripts_delete', methods: ['POST'])]
    public function delete(Request $request, Scripts $script, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$script->getId(), $request->request->get('_token'))) {
            $entityManager->remove($script);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_scripts_index', [], Response::HTTP_SEE_OTHER);
    }
}
