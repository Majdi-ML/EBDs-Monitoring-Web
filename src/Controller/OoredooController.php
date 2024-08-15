<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use App\Repository\UserRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Cluster;
use App\Entity\Requetessql;
use App\Entity\TrapsSnmp;
use App\Entity\Serveurs;
use App\Entity\Url;
use App\Entity\LogFilesPatterns;
use App\Entity\LogFiles;
use App\Entity\Process;
use App\Entity\Scripts;
use Symfony\Component\HttpFoundation\JsonResponse ;

class OoredooController extends AbstractController
{
    #[Route('/ooredoo', name: 'app_ooredoo2')]
public function ooredooDashboard(EntityManagerInterface $entityManager,UserRepository $user): Response
{

    $user = $this->getUser();
             $role = $user->getRoles()[0]; // ROLE_Billing

            // // Afficher la valeur de $role pour le débogage
            $equipe = substr($role, strlen('ROLE_'));

    $serversRepository = $entityManager->getRepository(Serveurs::class);
            $serveurs = $serversRepository->findAll();
            $secondParts = [];
            foreach ($serveurs as $serveur) {
                $idParts = explode('_', $serveur->getId());
                
                if (isset($idParts[1])) {
                    $secondPart = $idParts[1];
                    $secondParts[] = $secondPart;
                }
            }
            
            $uniqueSecondParts = array_unique($secondParts);

$uniqueSecondParts = array_unique($secondParts);
            // Calculer les totaux pour chaque tableau
            $tables = [
                'Cluster' => 'App\Entity\Cluster',
                'LogFiles' => 'App\Entity\LogFiles',
                'LogFilesPatterns' => 'App\Entity\LogFilesPatterns',
                'Serveurs' => 'App\Entity\Serveurs',
                'Requetessql' => 'App\Entity\Requetessql',
                'TrapsSnmp' => 'App\Entity\TrapsSnmp',
                'Url' => 'App\Entity\Url',
                'Process' => 'App\Entity\Process',
                'Scripts' => 'App\Entity\Scripts',
                // ... Ajouter d'autres tables
            ];
    
            $data = [];
    
            foreach ($tables as $tableName => $entityClass) {
                $repository = $entityManager->getRepository($entityClass);
                
                // Obtenez l'équipe à partir du rôle utilisateur
                
            
                // Construisez la condition WHERE
                $whereCondition = ['support' => $equipe];
            
                // Comptez les entités qui satisfont la condition
                $count = $repository->count($whereCondition);
            
                // Ajoutez le résultat au tableau de données
                $data[$tableName] = $count;
            
                // Affichez la valeur de l'équipe pour le débogage
                echo "Équipe: $equipe\n";
            }
    
            return $this->render('ooredoo/index.html.twig', [
                'data' => $data,
                'secondPart' => $uniqueSecondParts,
            ]);
    
    }

    #[Route('/get-filter-data/{selectedPart2}', name: 'get_filter')]
    public function getFilteredData2(Request $request, EntityManagerInterface $entityManager, string $selectedPart2,UserRepository $user): JsonResponse
    {
        $tables = [
            'Cluster' => 'App\Entity\Cluster',
            'LogFiles' => 'App\Entity\LogFiles',
            'LogFilesPatterns' => 'App\Entity\LogFilesPatterns',
            'Serveurs' => 'App\Entity\Serveurs',
            'Requetessql' => 'App\Entity\Requetessql',
            'TrapsSnmp' => 'App\Entity\TrapsSnmp',
            'Url' => 'App\Entity\Url',
            'Process' => 'App\Entity\Process',
            'Scripts' => 'App\Entity\Scripts',
            // ... Ajoutez les autres tables
        ];
    
        $filteredData = [];
    
        foreach ($tables as $tableName => $entityClass) {
            $repository = $entityManager->getRepository($entityClass);
            $queryBuilder = $repository->createQueryBuilder('t');
            $user = $this->getUser();
             $role = $user->getRoles()[0]; // ROLE_Billing

            // // Afficher la valeur de $role pour le débogage
            $equipe = substr($role, strlen('ROLE_'));
    
             $count = $queryBuilder
        ->where($queryBuilder->expr()->andX(
            $queryBuilder->expr()->like('t.id', ':selectedPart'),
            $queryBuilder->expr()->like('t.support', ':equipe')
        ))
        ->setParameter('selectedPart', '%' . $selectedPart2 . '%')
        ->setParameter('equipe', '%' . $equipe . '%')
        ->select($queryBuilder->expr()->count('t'))
        ->getQuery()
        ->getSingleScalarResult();
    
            $filteredData[$tableName] = $count;
        }
    
        return new JsonResponse($filteredData);
    }
        
        #[Route('/ooredoo/admin', name: 'app_ooredoo')]
        public function ooredooDashboard2(Request $request,EntityManagerInterface $entityManager,UserRepository $user): Response
        {
            $serversRepository = $entityManager->getRepository(Serveurs::class);
            $serveurs = $serversRepository->findAll();
            $secondParts = [];
            foreach ($serveurs as $serveur) {
                $idParts = explode('_', $serveur->getId());
                
                if (isset($idParts[1])) {
                    $secondPart = $idParts[1];
                    $secondParts[] = $secondPart;
                }
            }
            
            $uniqueSecondParts = array_unique($secondParts);

$uniqueSecondParts = array_unique($secondParts);
            // Calculer les totaux pour chaque tableau
            $tables = [
                'Cluster' => 'App\Entity\Cluster',
                'LogFiles' => 'App\Entity\LogFiles',
                'LogFilesPatterns' => 'App\Entity\LogFilesPatterns',
                'Serveurs' => 'App\Entity\Serveurs',
                'Requetessql' => 'App\Entity\Requetessql',
                'TrapsSnmp' => 'App\Entity\TrapsSnmp',
                'Url' => 'App\Entity\Url',
                'Process' => 'App\Entity\Process',
                'Scripts' => 'App\Entity\Scripts',
                // ... Ajouter d'autres tables
            ];
    
            $data = [];
    
            foreach ($tables as $tableName => $entityClass) {
                $repository = $entityManager->getRepository($entityClass);
                $count = $repository->count([]);
                $data[$tableName] = $count;
            }
    
            return $this->render('ooredoo/index2.html.twig', [
                'data' => $data,
                'secondPart' => $uniqueSecondParts,
            ]);
            
                
        }

        #[Route('/get-filtered-data/{selectedPart}', name: 'get_filtered_data')]
        public function getFilteredData(Request $request, EntityManagerInterface $entityManager, string $selectedPart): JsonResponse
        {
            $tables = [
                'Cluster' => 'App\Entity\Cluster',
                'LogFiles' => 'App\Entity\LogFiles',
                'LogFilesPatterns' => 'App\Entity\LogFilesPatterns',
                'Serveurs' => 'App\Entity\Serveurs',
                'Requetessql' => 'App\Entity\Requetessql',
                'TrapsSnmp' => 'App\Entity\TrapsSnmp',
                'Url' => 'App\Entity\Url',
                'Process' => 'App\Entity\Process',
                'Scripts' => 'App\Entity\Scripts',
                // ... Ajoutez les autres tables
            ];
        
            $filteredData = [];
        
            foreach ($tables as $tableName => $entityClass) {
                $repository = $entityManager->getRepository($entityClass);
                $queryBuilder = $repository->createQueryBuilder('t');
        
                $count = $queryBuilder
                    ->where($queryBuilder->expr()->like('t.id', ':selectedPart'))
                    ->setParameter('selectedPart', '%' . $selectedPart . '%')
                    ->select($queryBuilder->expr()->count('t'))
                    ->getQuery()
                    ->getSingleScalarResult();
        
                $filteredData[$tableName] = $count;
            }
        
            return new JsonResponse($filteredData);
        }

       

       


}
