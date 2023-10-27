<?php

namespace App\Repository;

use App\Entity\Url;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Url>
 *
 * @method Url|null find($id, $lockMode = null, $lockVersion = null)
 * @method Url|null findOneBy(array $criteria, array $orderBy = null)
 * @method Url[]    findAll()
 * @method Url[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class UrlRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Url::class);
    }
    public function getUrlCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getUrlCountByEtatAndUser(string $etat, string $role): int
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
public function getUrlCountBycriticite(string $criticite): int
{
    return $this->createQueryBuilder('c')
        ->select('COUNT(c)')
        ->where('c.criticite = :criticite')
        ->setParameter('criticite', $criticite)
        ->getQuery()
        ->getSingleScalarResult();
}

public function getUrlCountBycriticiteAndUser(string $criticite, string $role): int
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
public function getUrlCountByMonotoring(string $monitoredBy): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.monitoredBy = :monitoredBy')
            ->setParameter('monitoredBy', $monitoredBy)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getUrlCountByMonotoringAndUser(string $monitoredBy, string $role): int
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
//     * @return Url[] Returns an array of Url objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('u')
//            ->andWhere('u.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('u.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Url
//    {
//        return $this->createQueryBuilder('u')
//            ->andWhere('u.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
