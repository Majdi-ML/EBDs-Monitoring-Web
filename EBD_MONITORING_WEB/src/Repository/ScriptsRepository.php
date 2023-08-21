<?php

namespace App\Repository;

use App\Entity\Scripts;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Scripts>
 *
 * @method Scripts|null find($id, $lockMode = null, $lockVersion = null)
 * @method Scripts|null findOneBy(array $criteria, array $orderBy = null)
 * @method Scripts[]    findAll()
 * @method Scripts[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ScriptsRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Scripts::class);
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
//     * @return Scripts[] Returns an array of Scripts objects
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

//    public function findOneBySomeField($value): ?Scripts
//    {
//        return $this->createQueryBuilder('s')
//            ->andWhere('s.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
