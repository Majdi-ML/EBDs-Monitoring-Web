<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Serveurs
 *
 * @ORM\Table(name="serveurs")
 * @ORM\Entity
 */
class Serveurs
{
    /**
     * @var string
     *
     * @ORM\Column(name="id", type="string", length=50, nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ref", type="string", length=50, nullable=true)
     */
    private $ref;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Etat", type="string", length=50, nullable=true)
     */
    private $etat;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Platforme", type="string", length=50, nullable=true)
     */
    private $platforme;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Hostname", type="string", length=50, nullable=true)
     */
    private $hostname;

    /**
     * @var string|null
     *
     * @ORM\Column(name="FQDN", type="string", length=50, nullable=true)
     */
    private $fqdn;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Type", type="string", length=50, nullable=true)
     */
    private $type;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Modele", type="string", length=50, nullable=true)
     */
    private $modele;

    /**
     * @var string|null
     *
     * @ORM\Column(name="OS", type="string", length=50, nullable=true)
     */
    private $os;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ver_tech__Firmware", type="string", length=50, nullable=true)
     */
    private $verTechFirmware;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Cluster", type="string", length=50, nullable=true)
     */
    private $cluster;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ip_source", type="string", length=50, nullable=true)
     */
    private $ipSource;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Description", type="string", length=50, nullable=true)
     */
    private $description;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Socle_Standard_OMU", type="string", length=50, nullable=true)
     */
    private $socleStandardOmu;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Complements_informations", type="string", length=50, nullable=true)
     */
    private $complementsInformations;

    public function getId(): string
    {
        return $this->id;
    }

    public function getRef(): ?string
    {
        return $this->ref;
    }

    public function setRef(?string $ref): void
    {
        $this->ref = $ref;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(?string $etat): void
    {
        $this->etat = $etat;
    }

    public function getPlatforme(): ?string
    {
        return $this->platforme;
    }

    public function setPlatforme(?string $platforme): void
    {
        $this->platforme = $platforme;
    }

    public function getHostname(): ?string
    {
        return $this->hostname;
    }

    public function setHostname(?string $hostname): void
    {
        $this->hostname = $hostname;
    }

    public function getFqdn(): ?string
    {
        return $this->fqdn;
    }

    public function setFqdn(?string $fqdn): void
    {
        $this->fqdn = $fqdn;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(?string $type): void
    {
        $this->type = $type;
    }

    public function getModele(): ?string
    {
        return $this->modele;
    }

    public function setModele(?string $modele): void
    {
        $this->modele = $modele;
    }

    public function getOs(): ?string
    {
        return $this->os;
    }

    public function setOs(?string $os): void
    {
        $this->os = $os;
    }

    public function getVerTechFirmware(): ?string
    {
        return $this->verTechFirmware;
    }

    public function setVerTechFirmware(?string $verTechFirmware): void
    {
        $this->verTechFirmware = $verTechFirmware;
    }

    public function getCluster(): ?string
    {
        return $this->cluster;
    }

    public function setCluster(?string $cluster): void
    {
        $this->cluster = $cluster;
    }

    public function getIpSource(): ?string
    {
        return $this->ipSource;
    }

    public function setIpSource(?string $ipSource): void
    {
        $this->ipSource = $ipSource;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): void
    {
        $this->description = $description;
    }

    public function getSocleStandardOmu(): ?string
    {
        return $this->socleStandardOmu;
    }

    public function setSocleStandardOmu(?string $socleStandardOmu): void
    {
        $this->socleStandardOmu = $socleStandardOmu;
    }

    public function getComplementsInformations(): ?string
    {
        return $this->complementsInformations;
    }

    public function setComplementsInformations(?string $complementsInformations): void
    {
        $this->complementsInformations = $complementsInformations;
    }

}
