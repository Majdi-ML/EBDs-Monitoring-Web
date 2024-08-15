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
    public function getLogFilePatternsCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getLogFilePatternsCountByEtatAndUser(string $etat, string $role): int
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
public function getLogFilePatternsCountBycriticite(string $criticite): int
{
    return $this->createQueryBuilder('c')
        ->select('COUNT(c)')
        ->where('c.criticite = :criticite')
        ->setParameter('criticite', $criticite)
        ->getQuery()
        ->getSingleScalarResult();
}

public function getLogFilePatternsCountBycriticiteAndUser(string $criticite, string $role): int
{
return $this->createQueryBuilder('c')
->select('COUNT(c)')
->where('c.criticite = :criticite')
->andWhere('LOCATE(:role, c.support) > 0') // Utilisation de LOCATE() à la place de INSTR()
->setParameter('criticite', $criticite)
->setParameter('role', $role)
->getQuery()
->getSingleScalarResult();
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
