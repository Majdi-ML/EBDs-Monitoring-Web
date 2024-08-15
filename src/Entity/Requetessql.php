<?php

namespace App\Entity;

use App\Repository\RequetessqlRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: RequetessqlRepository::class)]
class Requetessql
{
    #[ORM\Id]
    #[ORM\Column(length: 500)]
    private ?string $id = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $ref = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $etat = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $refComposant = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $rgSgSiCluster = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $requeteSql = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $usernameDbName = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $resultatAttenduDeLaRequete = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $performAction = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $criticite = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $messageAlarme = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $instructions = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $intervalleDePolling = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $refService = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $objet = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $monitoredBy = null;

    #[ORM\Column(length: 500, nullable: true)]
    private ?string $nomTemplate = null;

    #[ORM\Column(length: 500, nullable: true)]
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

    public function getRequeteSql(): ?string
    {
        return $this->requeteSql;
    }

    public function setRequeteSql(string $requeteSql): static
    {
        $this->requeteSql = $requeteSql;

        return $this;
    }

    public function getUsernameDbName(): ?string
    {
        return $this->usernameDbName;
    }

    public function setUsernameDbName(string $usernameDbName): static
    {
        $this->usernameDbName = $usernameDbName;

        return $this;
    }

    public function getResultatAttenduDeLaRequete(): ?string
    {
        return $this->resultatAttenduDeLaRequete;
    }

    public function setResultatAttenduDeLaRequete(string $resultatAttenduDeLaRequete): static
    {
        $this->resultatAttenduDeLaRequete = $resultatAttenduDeLaRequete;

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

    public function getIntervalleDePolling(): ?string
    {
        return $this->intervalleDePolling;
    }

    public function setIntervalleDePolling(string $intervalleDePolling): static
    {
        $this->intervalleDePolling = $intervalleDePolling;

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

    public function getMonitoredBy(): ?string
    {
        return $this->monitoredBy;
    }

    public function setMonitoredBy(string $monitoredBy): static
    {
        $this->monitoredBy = $monitoredBy;

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
