<?php

namespace App\Repository;

use App\Entity\LogFilesPatterns;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<LogFilesPatterns>
 *
 * @method LogFilesPatterns|null find($id, $lockMode = null, $lockVersion = null)
 * @method LogFilesPatterns|null findOneBy(array $criteria, array $orderBy = null)
 * @method LogFilesPatterns[]    findAll()
 * @method LogFilesPatterns[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class LogFilesPatternsRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, LogFilesPatterns::class);
    }

//    /**
//     * @return LogFilesPatterns[] Returns an array of LogFilesPatterns objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('l.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?LogFilesPatterns
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
