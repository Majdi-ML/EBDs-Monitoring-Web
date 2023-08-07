<?php

namespace App\Entity;

use App\Repository\TrapsSnmpRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: TrapsSnmpRepository::class)]
class TrapsSnmp
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
    private ?string $signification = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $versionSnmp = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $oid = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $specificTrap = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $variableBinding = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $criticite = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $messageAlarme = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $instructions = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $acquittement = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $mibAssocie = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $objet = null;

    #[ORM\Column(length: 250, nullable: true)]
    private ?string $compelementInformation = null;

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

    public function getSignification(): ?string
    {
        return $this->signification;
    }

    public function setSignification(string $signification): static
    {
        $this->signification = $signification;

        return $this;
    }

    public function getVersionSnmp(): ?string
    {
        return $this->versionSnmp;
    }

    public function setVersionSnmp(string $versionSnmp): static
    {
        $this->versionSnmp = $versionSnmp;

        return $this;
    }

    public function getOid(): ?string
    {
        return $this->oid;
    }

    public function setOid(string $oid): static
    {
        $this->oid = $oid;

        return $this;
    }

    public function getSpecificTrap(): ?string
    {
        return $this->specificTrap;
    }

    public function setSpecificTrap(string $specificTrap): static
    {
        $this->specificTrap = $specificTrap;

        return $this;
    }

    public function getVariableBinding(): ?string
    {
        return $this->variableBinding;
    }

    public function setVariableBinding(string $variableBinding): static
    {
        $this->variableBinding = $variableBinding;

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

    public function getAcquittement(): ?string
    {
        return $this->acquittement;
    }

    public function setAcquittement(string $acquittement): static
    {
        $this->acquittement = $acquittement;

        return $this;
    }

    public function getMibAssocie(): ?string
    {
        return $this->mibAssocie;
    }

    public function setMibAssocie(string $mibAssocie): static
    {
        $this->mibAssocie = $mibAssocie;

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

    public function getCompelementInformation(): ?string
    {
        return $this->compelementInformation;
    }

    public function setCompelementInformation(string $compelementInformation): static
    {
        $this->compelementInformation = $compelementInformation;

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
