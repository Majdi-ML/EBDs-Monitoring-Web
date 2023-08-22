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
public function ooredooDashboard(EntityManagerInterface $entityManager): Response
{
    $clusterRepository = $entityManager->getRepository(Cluster::class);

        $etatCounts = [
            'Supprimé' => $clusterRepository->getClusterCountByEtat('Supprimé'),
            'Modifié' => $clusterRepository->getClusterCountByEtat('Modifié'),
            'Nouveau' => $clusterRepository->getClusterCountByEtat('Nouveau'),
            'Inchangé' => $clusterRepository->getClusterCountByEtat('Inchangé'),
        ];

        // Retrieve counts of requetessql by etat
        $requetessqlRepository = $entityManager->getRepository(Requetessql::class);
        $etatCountsRequetessql = [
            'Supprimé' => $requetessqlRepository->getRequetessqlCountByEtat('Supprimé'),
            'Modifié' => $requetessqlRepository->getRequetessqlCountByEtat('Modifié'),
            'Nouveau' => $requetessqlRepository->getRequetessqlCountByEtat('Nouveau'),
            'Inchangé' => $requetessqlRepository->getRequetessqlCountByEtat('Inchangé'),
        ];

        // Retrieve counts of traps SNMP by version SNMP
        $trapsSnmpRepository = $entityManager->getRepository(TrapsSnmp::class);
        
        $versionCountsTrapsSnmp = [
            'Version 1' => $trapsSnmpRepository->getTrapsSnmpCountByVersionSnmp('Version 1'),
            'Version 2' => $trapsSnmpRepository->getTrapsSnmpCountByVersionSnmp('Version 2'),
        ];
        $serveursRepository = $entityManager->getRepository(Serveurs::class);

        $supportValues = [

            'Supprimé' => $serveursRepository->getServeursCountByEtat('Supprimé'),
            'Modifié' => $serveursRepository->getServeursCountByEtat('Modifié'),
            'Nouveau' => $serveursRepository->getServeursCountByEtat('Nouveau'),
            'Inchangé' => $serveursRepository->getServeursCountByEtat('Inchangé'),
        ];

        $urlRepository=$entityManager->getRepository(Url::class);
        $chartData = [

            'Critique' => $urlRepository->getChartDataForPolarAreaByCriticite('Critique'),
            'Majeure' => $urlRepository->getChartDataForPolarAreaByCriticite('Majeure'),
            'Normale' => $urlRepository->getChartDataForPolarAreaByCriticite('Normale'),
        ];





        return $this->render('ooredoo/index.html.twig', [
            'etatCounts' => $etatCounts,
            'etatCountsRequetessql' => $etatCountsRequetessql,
            'versionCountsTrapsSnmp' => $versionCountsTrapsSnmp,
            'supportValues' => $supportValues,
            'chartData' => $chartData,
        ]);
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
