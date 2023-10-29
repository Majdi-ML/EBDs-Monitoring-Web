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
    public function getProcessCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getProcessCountByEtatAndUser(string $etat, string $role): int
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
public function getProcessCountBycriticite(string $criticite): int
{
    return $this->createQueryBuilder('c')
        ->select('COUNT(c)')
        ->where('c.criticite = :criticite')
        ->setParameter('criticite', $criticite)
        ->getQuery()
        ->getSingleScalarResult();
}

public function getProcessCountBycriticiteAndUser(string $criticite, string $role): int
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
public function getProcessCountByMonotoring(string $monitoredBy): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.monitoredBy = :monitoredBy')
            ->setParameter('monitoredBy', $monitoredBy)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getProcessCountByMonotoringAndUser(string $monitoredBy, string $role): int
{
    return $this->createQueryBuilder('c')
    ->select('COUNT(c)')
    ->where('c.monitoredBy = :monitoredBy')
    ->andWhere('LOCATE(:role, c.support) > 0') // Utilisation de LOCATE() à la place de INSTR()
    ->setParameter('monitoredBy', $monitoredBy)
    ->setParameter('role', $role)
    ->getQuery()
    ->getSingleScalarResult();
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
