<?php

namespace App\Entity;

use App\Repository\LogFilesRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: LogFilesRepository::class)]
class LogFiles
{
    #[ORM\Id]
    #[ORM\Column(length: 250)]
    private ?string $id = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $ref = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $etat = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $refComposant = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $rgSgSiCluster = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $logfile = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $localisation = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $description = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $formatLogfile = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $separateur = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $intervalleDePolling = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $monitoredBy = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $fourniEnAnnexe = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $refService = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $nomTemplate = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $logConditions = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $support = null;

    public function getId(): ?string
    {
        return $this->id;
    }
    public function setId(string $id): static
    {
        $this->id = $id;

        return $this;
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

    public function getRefComposant(): ?string
    {
        return $this->refComposant;
    }

    public function setRefComposant(string $refComposant): static
    {
        $this->refComposant = $refComposant;

        return $this;
    }

    public function getRgSgSiCluster(): ?string
    {
        return $this->rgSgSiCluster;
    }

    public function setRgSgSiCluster(string $rgSgSiCluster): static
    {
        $this->rgSgSiCluster = $rgSgSiCluster;

        return $this;
    }

    public function getLogfile(): ?string
    {
        return $this->logfile;
    }

    public function setLogfile(string $logfile): static
    {
        $this->logfile = $logfile;

        return $this;
    }

    public function getLocalisation(): ?string
    {
        return $this->localisation;
    }

    public function setLocalisation(string $localisation): static
    {
        $this->localisation = $localisation;

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

    public function getFormatLogfile(): ?string
    {
        return $this->formatLogfile;
    }

    public function setFormatLogfile(string $formatLogfile): static
    {
        $this->formatLogfile = $formatLogfile;

        return $this;
    }

    public function getSeparateur(): ?string
    {
        return $this->separateur;
    }

    public function setSeparateur(string $separateur): static
    {
        $this->separateur = $separateur;

        return $this;
    }

    public function getIntervalleDePolling(): ?string
    {
        return $this->intervalleDePolling;
    }

    public function setIntervalleDePolling(string $intervalleDePolling): static
    {
        $this->intervalleDePolling = $intervalleDePolling;

        return $this;
    }

    public function getMonitoredBy(): ?string
    {
        return $this->monitoredBy;
    }

    public function setMonitoredBy(string $monitoredBy): static
    {
        $this->monitoredBy = $monitoredBy;

        return $this;
    }

    public function getFourniEnAnnexe(): ?string
    {
        return $this->fourniEnAnnexe;
    }

    public function setFourniEnAnnexe(string $fourniEnAnnexe): static
    {
        $this->fourniEnAnnexe = $fourniEnAnnexe;

        return $this;
    }

    public function getRefService(): ?string
    {
        return $this->refService;
    }

    public function setRefService(string $refService): static
    {
        $this->refService = $refService;

        return $this;
    }

    public function getNomTemplate(): ?string
    {
        return $this->nomTemplate;
    }

    public function setNomTemplate(string $nomTemplate): static
    {
        $this->nomTemplate = $nomTemplate;

        return $this;
    }

    public function getLogConditions(): ?string
    {
        return $this->logConditions;
    }

    public function setLogConditions(string $logConditions): static
    {
        $this->logConditions = $logConditions;

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
