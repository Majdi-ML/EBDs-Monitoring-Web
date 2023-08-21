<?php

namespace App\Repository;

use App\Entity\Process;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Process>
 *
 * @method Process|null find($id, $lockMode = null, $lockVersion = null)
 * @method Process|null findOneBy(array $criteria, array $orderBy = null)
 * @method Process[]    findAll()
 * @method Process[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProcessRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Process::class);
    }
    public function getDataCountByServer($server): array
    {
        $queryBuilder = $this->createQueryBuilder('s')
            ->select('SUBSTRING(s.id, LENGTH(s.id) - 3) as tableName, COUNT(s.id) as count')
            ->where('s.id LIKE :server')
            ->groupBy('tableName')
            ->setParameter('server', '%_'.$server.'%')
            ->getQuery();
    
        $result = $queryBuilder->getResult();
    
        $data = [];
        foreach ($result as $row) {
            $data[$row['tableName']] = $row['count'];
        }
    
        return $data;
    }
//    /**
//     * @return Process[] Returns an array of Process objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('p.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Process
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
