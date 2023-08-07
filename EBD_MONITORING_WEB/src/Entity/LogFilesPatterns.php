<?php

namespace App\Entity;

use App\Repository\LogFilesPatternsRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: LogFilesPatternsRepository::class)]
class LogFilesPatterns
{
    #[ORM\Id]
    #[ORM\Column(length: 250)]
    private ?string $id = null;

    #[ORM\Column]
    private ?int $nRef = null;

    #[ORM\Column]
    private ?int $ref = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $etat = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $signification = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $identification = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $criticite = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $messageAlarme = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $instructions = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $performAction = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $acquittement = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $complementsInformations = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $refService = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $objet = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $support = null;

    public function getId(): ?string
    {
        return $this->id;
    }


    public function getNRef(): ?int
    {
        return $this->nRef;
    }

    public function setNRef(int $nRef): static
    {
        $this->nRef = $nRef;

        return $this;
    }

    public function getRef(): ?int
    {
        return $this->ref;
    }

    public function setRef(int $ref): static
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

    public function getSignification(): ?string
    {
        return $this->signification;
    }

    public function setSignification(string $signification): static
    {
        $this->signification = $signification;

        return $this;
    }

    public function getIdentification(): ?string
    {
        return $this->identification;
    }

    public function setIdentification(string $identification): static
    {
        $this->identification = $identification;

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

    public function getMessageAlarme(): ?string
    {
        return $this->messageAlarme;
    }

    public function setMessageAlarme(string $messageAlarme): static
    {
        $this->messageAlarme = $messageAlarme;

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

    public function getPerformAction(): ?string
    {
        return $this->performAction;
    }

    public function setPerformAction(string $performAction): static
    {
        $this->performAction = $performAction;

        return $this;
    }

    public function getAcquittement(): ?string
    {
        return $this->acquittement;
    }

    public function setAcquittement(string $acquittement): static
    {
        $this->acquittement = $acquittement;

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

    public function getRefService(): ?string
    {
        return $this->refService;
    }

    public function setRefService(string $refService): static
    {
        $this->refService = $refService;

        return $this;
    }

    public function getObjet(): ?string
    {
        return $this->objet;
    }

    public function setObjet(string $objet): static
    {
        $this->objet = $objet;

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
