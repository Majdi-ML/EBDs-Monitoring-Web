<?php

namespace App\Repository;

use App\Entity\Cluster;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Cluster>
 *
 * @method Cluster|null find($id, $lockMode = null, $lockVersion = null)
 * @method Cluster|null findOneBy(array $criteria, array $orderBy = null)
 * @method Cluster[]    findAll()
 * @method Cluster[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ClusterRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Cluster::class);
    }
    public function getClusterCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getClusterCountByEtatAndUser(string $etat, string $role): int
{
    return $this->createQueryBuilder('c')
    ->select('COUNT(c)')
    ->where('c.etat = :etat')
    ->andWhere('LOCATE(:role, c.support) > 0') // Utilisation de LOCATE() à la place de INSTR()
    ->setParameter('etat', $etat)
    ->setParameter('role', $role)
    ->getQuery()
    ->getSingleScalarResult();
}
public function getClusters(string $etat, string $role): int
{
    return $this->createQueryBuilder('c')
    ->select('COUNT(c)')
    ->where('c.etat = :etat')
    ->andWhere('LOCATE(:role, c.support) > 0') // Utilisation de LOCATE() à la place de INSTR()
    ->setParameter('etat', $etat)
    ->setParameter('role', $role)
    ->getQuery()
    ->getSingleScalarResult();
}


public function countByNomServeur($nomServeur)
    {
        return $this->createQueryBuilder('s')
            ->select('COUNT(s.id)')
            ->where('s.id LIKE :nomServeurPattern')
            ->setParameter('nomServeurPattern', 'EBD_' . $nomServeur . '_Cluster_%')
            ->getQuery()
            ->getSingleScalarResult();
    }
//    /**
//     * @return Cluster[] Returns an array of Cluster objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('c')
//            ->andWhere('c.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('c.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Cluster
//    {
//        return $this->createQueryBuilder('c')
//            ->andWhere('c.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
