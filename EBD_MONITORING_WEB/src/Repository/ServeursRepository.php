<?php

namespace App\Repository;

use App\Entity\Serveurs;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Serveurs>
 *
 * @method Serveurs|null find($id, $lockMode = null, $lockVersion = null)
 * @method Serveurs|null findOneBy(array $criteria, array $orderBy = null)
 * @method Serveurs[]    findAll()
 * @method Serveurs[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ServeursRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Serveurs::class);
    }
    public function getServeursCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('s')
            ->select('COUNT(s)')
            ->where('s.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }
    public function getServeursCountByPlatfomre(string $platforme): int
    {
        return $this->createQueryBuilder('a')
            ->select('COUNT(a)')
            ->where('a.platforme = :platforme')
            ->setParameter('platforme', $platforme)
            ->getQuery()
            ->getSingleScalarResult();
    }
    public function getServeursCountByOs(string $os): int
    {
        return $this->createQueryBuilder('a')
            ->select('COUNT(a)')
            ->where('a.os = :os')
            ->setParameter('os', $os)
            ->getQuery()
            ->getSingleScalarResult();
    }
    public function countByNomServeur($nomServeur)
    {
        return $this->createQueryBuilder('s')
            ->select('COUNT(s.id)')
            ->where('s.id LIKE :nomServeurPattern')
            ->setParameter('nomServeurPattern', 'EBD_' . $nomServeur . '_SERVEURS_%')
            ->getQuery()
            ->getSingleScalarResult();
    }
//    /**
//     * @return Serveurs[] Returns an array of Serveurs objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('s')
//            ->andWhere('s.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('s.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Serveurs
//    {
//        return $this->createQueryBuilder('s')
//            ->andWhere('s.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
