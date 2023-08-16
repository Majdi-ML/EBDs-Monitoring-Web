<?php

namespace App\Repository;

use App\Entity\TrapsSnmp;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<TrapsSnmp>
 *
 * @method TrapsSnmp|null find($id, $lockMode = null, $lockVersion = null)
 * @method TrapsSnmp|null findOneBy(array $criteria, array $orderBy = null)
 * @method TrapsSnmp[]    findAll()
 * @method TrapsSnmp[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TrapsSnmpRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TrapsSnmp::class);
    }
    
    public function getTrapsSnmpCountByVersionSnmp(string $versionSnmp): int
{
    return $this->createQueryBuilder('t')
        ->select('COUNT(t)')
        ->where('t.versionSnmp = :versionSnmp')
        ->setParameter('versionSnmp', $versionSnmp)
        ->getQuery()
        ->getSingleScalarResult();
}

//    /**
//     * @return TrapsSnmp[] Returns an array of TrapsSnmp objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('t')
//            ->andWhere('t.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('t.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?TrapsSnmp
//    {
//        return $this->createQueryBuilder('t')
//            ->andWhere('t.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
