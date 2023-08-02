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
