<?php

namespace App\Repository;

use App\Entity\TrapsSnmp;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<TrapsSnmp>
 *
 * @method TrapsSnmp|null find($id, $lockMode = null, $lockVersion = null)
 * @method TrapsSnmp|null findOneBy(array $criteria, array $orderBy = null)
 * @method TrapsSnmp[]    findAll()
 * @method TrapsSnmp[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TrapsSnmpRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TrapsSnmp::class);
    }
    
    public function getTrapsSnmpCountByVersionSnmp(string $versionSnmp): int
{
    return $this->createQueryBuilder('t')
        ->select('COUNT(t)')
        ->where('t.versionSnmp = :versionSnmp')
        ->setParameter('versionSnmp', $versionSnmp)
        ->getQuery()
        ->getSingleScalarResult();
}
public function getTrapsSnmpCountByVersionSnmpAndUser(string $versionSnmp,string $role): int
{
    return $this->createQueryBuilder('t')
        ->select('COUNT(t)')
        ->where('t.versionSnmp = :versionSnmp')
        ->andWhere('LOCATE(:role, c.support) > 0')
        ->setParameter('versionSnmp', $versionSnmp)
        ->setParameter('role', $role)
        ->getQuery()
        ->getSingleScalarResult();
}
public function getTrapsSnmpCountByEtat(string $etat): int
{
    return $this->createQueryBuilder('c')
        ->select('COUNT(c)')
        ->where('c.etat = :etat')
        ->setParameter('etat', $etat)
        ->getQuery()
        ->getSingleScalarResult();
}

public function getTrapsSnmpCountByEtatAndUser(string $etat, string $role): int
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
public function getTrapsSnmpCountBycriticite(string $criticite): int
{
return $this->createQueryBuilder('c')
    ->select('COUNT(c)')
    ->where('c.criticite = :criticite')
    ->setParameter('criticite', $criticite)
    ->getQuery()
    ->getSingleScalarResult();
}

public function getTrapsSnmpCountBycriticiteAndUser(string $criticite, string $role): int
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
//     * @return TrapsSnmp[] Returns an array of TrapsSnmp objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('t')
//            ->andWhere('t.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('t.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?TrapsSnmp
//    {
//        return $this->createQueryBuilder('t')
//            ->andWhere('t.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
