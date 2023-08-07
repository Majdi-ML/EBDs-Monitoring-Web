<?php

namespace App\Entity;

use App\Repository\ServeursRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ServeursRepository::class)]
class Serveurs
{
    #[ORM\Id]
    #[ORM\Column(length: 250)]
    private ?string $id = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $ref = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $etat = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $platforme = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $hostname = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $fqdn = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $type = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $modele = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $os = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $verTechFirmware = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $cluster = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $ipSource = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $description = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $socleStandardOmu = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $complementsInformations = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $support = null;

    public function getId(): ?string
    {
        return $this->id;
    }

    public function getRef(): ?string
    {
        return $this->ref;
    }

    public function setRef(string $ref): static
    {
        $this->ref = $ref;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): static
    {
        $this->etat = $etat;

        return $this;
    }

    public function getPlatforme(): ?string
    {
        return $this->platforme;
    }

    public function setPlatforme(string $platforme): static
    {
        $this->platforme = $platforme;

        return $this;
    }

    public function getHostname(): ?string
    {
        return $this->hostname;
    }

    public function setHostname(string $hostname): static
    {
        $this->hostname = $hostname;

        return $this;
    }

    public function getFqdn(): ?string
    {
        return $this->fqdn;
    }

    public function setFqdn(string $fqdn): static
    {
        $this->fqdn = $fqdn;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): static
    {
        $this->type = $type;

        return $this;
    }

    public function getModele(): ?string
    {
        return $this->modele;
    }

    public function setModele(string $modele): static
    {
        $this->modele = $modele;

        return $this;
    }

    public function getOs(): ?string
    {
        return $this->os;
    }

    public function setOs(string $os): static
    {
        $this->os = $os;

        return $this;
    }

    public function getVerTechFirmware(): ?string
    {
        return $this->verTechFirmware;
    }

    public function setVerTechFirmware(string $verTechFirmware): static
    {
        $this->verTechFirmware = $verTechFirmware;

        return $this;
    }

    public function getCluster(): ?string
    {
        return $this->cluster;
    }

    public function setCluster(string $cluster): static
    {
        $this->cluster = $cluster;

        return $this;
    }

    public function getIpSource(): ?string
    {
        return $this->ipSource;
    }

    public function setIpSource(string $ipSource): static
    {
        $this->ipSource = $ipSource;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): static
    {
        $this->description = $description;

        return $this;
    }

    public function getSocleStandardOmu(): ?string
    {
        return $this->socleStandardOmu;
    }

    public function setSocleStandardOmu(string $socleStandardOmu): static
    {
        $this->socleStandardOmu = $socleStandardOmu;

        return $this;
    }

    public function getComplementsInformations(): ?string
    {
        return $this->complementsInformations;
    }

    public function setComplementsInformations(string $complementsInformations): static
    {
        $this->complementsInformations = $complementsInformations;

        return $this;
    }

    public function getSupport(): ?string
    {
        return $this->support;
    }

    public function setSupport(string $support): static
    {
        $this->support = $support;

        return $this;
    }
}
