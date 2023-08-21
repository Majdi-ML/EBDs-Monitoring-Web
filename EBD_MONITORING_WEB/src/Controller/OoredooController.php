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

class OoredooController extends AbstractController
{
    #[Route('/ooredoo/admin', name: 'app_ooredoo')]
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
        // Retrieve counts of traps SNMP by version SNMP
        #[Route('/ooredoo', name: 'app_ooredoo2')]
        public function ooredooDashboard2(Request $request,EntityManagerInterface $entityManager,UserRepository $user): Response
        {
            // $user = $this->getUser();
            // $role = $user->getRoles()[0]; // ROLE_Billing
        
            // // Afficher la valeur de $role pour le débogage
            // $roleWithoutPrefix = substr($role, strlen('ROLE_'));
        
            // $clusterRepository = $entityManager->getRepository(Cluster::class);
        
            // // Filtrer les données en fonction du support et du rôle
            // $etatCounts = [
            //     'Supprimé' => $clusterRepository->getClusterCountByEtatAndUser('Supprimé', $roleWithoutPrefix),
            //     'Modifié' => $clusterRepository->getClusterCountByEtatAndUser('Modifié', $roleWithoutPrefix),
            //     'Nouveau' => $clusterRepository->getClusterCountByEtatAndUser('Nouveau', $roleWithoutPrefix),
            //     'Inchangé' => $clusterRepository->getClusterCountByEtatAndUser('Inchangé', $roleWithoutPrefix),
            // ];
        
            // // Afficher les informations sur les états
            // var_dump($etatCounts);
        
            // return $this->render('ooredoo/index2.html.twig', [
            //     'etatCounts' => $etatCounts,
            // ]);
           
            $tables = [
                'Cluster' => 'App\Entity\Cluster',
                'LogFiles' => 'App\Entity\LogFiles',
                'LogFilesPatterns' => 'App\Entity\LogFilesPatterns',
                'Serveurs' => 'App\Entity\Serveurs',
                'Requetessql' => 'App\Entity\Requetessql',
                'TrapsSnmp' => 'App\Entity\TrapsSnmp',
                'Url' => 'App\Entity\Url',
                'Process' => 'App\Entity\Process',
                'Scripts' =>'App\Entity\Scripts',

                // ... Ajoutez les autres tables
            ];
    
            $data = [];
            foreach ($tables as $tableName => $entityClass) {
                $repository = $entityManager->getRepository($entityClass);
                $count = $repository->count([]);
                $data[$tableName] = $count;
            }
    
            return $this->render('ooredoo/index2.html.twig', [
                'data' => $data,
            ]);
        
        }

  


}
