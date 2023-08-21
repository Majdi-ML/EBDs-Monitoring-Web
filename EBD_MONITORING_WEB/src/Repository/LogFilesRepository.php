<?php

namespace App\Repository;

use App\Entity\LogFiles;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<LogFiles>
 *
 * @method LogFiles|null find($id, $lockMode = null, $lockVersion = null)
 * @method LogFiles|null findOneBy(array $criteria, array $orderBy = null)
 * @method LogFiles[]    findAll()
 * @method LogFiles[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class LogFilesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, LogFiles::class);
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
//     * @return LogFiles[] Returns an array of LogFiles objects
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

//    public function findOneBySomeField($value): ?LogFiles
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
