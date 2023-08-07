<?php

namespace App\Entity;

use App\Repository\ScriptsRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ScriptsRepository::class)]
class Scripts
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
    private ?string $script = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $codeErreur = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $criticite = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $description = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $instructions = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $monitoredBy = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $refService = null;

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

    public function getScript(): ?string
    {
        return $this->script;
    }

    public function setScript(string $script): static
    {
        $this->script = $script;

        return $this;
    }

    public function getCodeErreur(): ?string
    {
        return $this->codeErreur;
    }

    public function setCodeErreur(string $codeErreur): static
    {
        $this->codeErreur = $codeErreur;

        return $this;
    }

    public function getCriticite(): ?string
    {
        return $this->criticite;
    }

    public function setCriticite(string $criticite): static
    {
        $this->criticite = $criticite;

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

    public function getInstructions(): ?string
    {
        return $this->instructions;
    }

    public function setInstructions(string $instructions): static
    {
        $this->instructions = $instructions;

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

    public function getRefService(): ?string
    {
        return $this->refService;
    }

    public function setRefService(string $refService): static
    {
        $this->refService = $refService;

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
