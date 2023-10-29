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
    public function getScriptsCountByEtat(string $etat): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.etat = :etat')
            ->setParameter('etat', $etat)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getScriptsCountByEtatAndUser(string $etat, string $role): int
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
public function getScriptsCountBycriticite(string $criticite): int
{
    return $this->createQueryBuilder('c')
        ->select('COUNT(c)')
        ->where('c.criticite = :criticite')
        ->setParameter('criticite', $criticite)
        ->getQuery()
        ->getSingleScalarResult();
}

public function getScriptsCountBycriticiteAndUser(string $criticite, string $role): int
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
public function getScriptsCountByMonotoring(string $monitoredBy): int
    {
        return $this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.monitoredBy = :monitoredBy')
            ->setParameter('monitoredBy', $monitoredBy)
            ->getQuery()
            ->getSingleScalarResult();
    }

    public function getScriptsCountByMonotoringAndUser(string $monitoredBy, string $role): int
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
