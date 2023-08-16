<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use App\Entity\Cluster;
use App\Entity\Requetessql;
use App\Entity\TrapsSnmp;
use App\Entity\Serveurs;
use App\Entity\Url;
class OoredooController extends AbstractController
{
    #[Route('/ooredoo', name: 'app_ooredoo')]
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
    

  


}
