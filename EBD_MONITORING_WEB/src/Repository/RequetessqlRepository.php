<?php

namespace App\Repository;

use App\Entity\Requetessql;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Requetessql>
 *
 * @method Requetessql|null find($id, $lockMode = null, $lockVersion = null)
 * @method Requetessql|null findOneBy(array $criteria, array $orderBy = null)
 * @method Requetessql[]    findAll()
 * @method Requetessql[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RequetessqlRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Requetessql::class);
    }

    // Dans le repository de Requetessql
    public function getRequeteSqlCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getRequeteSqlCountByEtatAndUser(string $etat, string $role): int
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
public function getRequeteSqlCountBycriticite(string $criticite): int
{
    return $this->createQueryBuilder('c')
        ->select('COUNT(c)')
        ->where('c.criticite = :criticite')
        ->setParameter('criticite', $criticite)
        ->getQuery()
        ->getSingleScalarResult();
}

public function getRequeteSqlCountBycriticiteAndUser(string $criticite, string $role): int
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
public function getRequeteSqlCountByMonotoring(string $monitoredBy): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.monitoredBy = :monitoredBy')
            ->setParameter('monitoredBy', $monitoredBy)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getRequeteSqlCountByMonotoringAndUser(string $monitoredBy, string $role): int
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
//     * @return Requetessql[] Returns an array of Requetessql objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('r.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Requetessql
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
