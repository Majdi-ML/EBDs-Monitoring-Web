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


    public function getLogFileCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getLogFileCountByEtatAndUser(string $etat, string $role): int
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
public function getLogFileCountByMonotoring(string $monitoredBy): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.monitoredBy = :monitoredBy')
            ->setParameter('monitoredBy', $monitoredBy)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getLogFileCountByMonotoringAndUser(string $monitoredBy, string $role): int
{
    return $this->createQueryBuilder('c')
    ->select('COUNT(c)')
    ->where('c.monitoredBy = :monitoredBy')
    ->andWhere('LOCATE(:role, c.support) > 0') // Utilisation de LOCATE() à la place de INSTR()
    ->setParameter('etat', $monitoredBy)
    ->setParameter('role', $role)
    ->getQuery()
    ->getSingleScalarResult();
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
