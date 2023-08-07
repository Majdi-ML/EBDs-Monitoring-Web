<?php

namespace App\Entity;

use App\Repository\ClusterRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ClusterRepository::class)]
class Cluster
{
    #[ORM\Id]
    #[ORM\Column(length: 250)]
    private ?String $id = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $ref = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $etat = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $nomDuRessourceGroupPackageServiceGuard = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $adresseIp = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $listeDesServeursConcernés = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $logicielCluster = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $version = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $mode = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $serveurActif = null;

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

    public function getNomDuRessourceGroupPackageServiceGuard(): ?string
    {
        return $this->nomDuRessourceGroupPackageServiceGuard;
    }

    public function setNomDuRessourceGroupPackageServiceGuard(string $nomDuRessourceGroupPackageServiceGuard): static
    {
        $this->nomDuRessourceGroupPackageServiceGuard = $nomDuRessourceGroupPackageServiceGuard;

        return $this;
    }

    public function getAdresseIp(): ?string
    {
        return $this->adresseIp;
    }

    public function setAdresseIp(string $adresseIp): static
    {
        $this->adresseIp = $adresseIp;

        return $this;
    }

    public function getListeDesServeursConcernés(): ?string
    {
        return $this->listeDesServeursConcernés;
    }

    public function setListeDesServeursConcernés(string $listeDesServeursConcernés): static
    {
        $this->listeDesServeursConcernés = $listeDesServeursConcernés;

        return $this;
    }

    public function getLogicielCluster(): ?string
    {
        return $this->logicielCluster;
    }

    public function setLogicielCluster(string $logicielCluster): static
    {
        $this->logicielCluster = $logicielCluster;

        return $this;
    }

    public function getVersion(): ?string
    {
        return $this->version;
    }

    public function setVersion(string $version): static
    {
        $this->version = $version;

        return $this;
    }

    public function getMode(): ?string
    {
        return $this->mode;
    }

    public function setMode(string $mode): static
    {
        $this->mode = $mode;

        return $this;
    }

    public function getServeurActif(): ?string
    {
        return $this->serveurActif;
    }

    public function setServeurActif(string $serveurActif): static
    {
        $this->serveurActif = $serveurActif;

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
