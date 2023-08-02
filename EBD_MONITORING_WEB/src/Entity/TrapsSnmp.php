<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * TrapsSnmp
 *
 * @ORM\Table(name="traps_snmp")
 * @ORM\Entity
 */
class TrapsSnmp
{
    /**
     * @var string
     *
     * @ORM\Column(name="id", type="string", length=250, nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ref", type="string", length=250, nullable=true)
     */
    private $ref;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Etat", type="string", length=250, nullable=true)
     */
    private $etat;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ref_composant", type="string", length=250, nullable=true)
     */
    private $refComposant;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Signification", type="string", length=250, nullable=true)
     */
    private $signification;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Version_SNMP", type="string", length=250, nullable=true)
     */
    private $versionSnmp;

    /**
     * @var string|null
     *
     * @ORM\Column(name="OID", type="string", length=250, nullable=true)
     */
    private $oid;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Specific_Trap", type="string", length=250, nullable=true)
     */
    private $specificTrap;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Variable_binding", type="string", length=250, nullable=true)
     */
    private $variableBinding;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Criticite", type="string", length=250, nullable=true)
     */
    private $criticite;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Message_alarme", type="string", length=250, nullable=true)
     */
    private $messageAlarme;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Instructions", type="string", length=250, nullable=true)
     */
    private $instructions;

    /**
     * @var string|null
     *
     * @ORM\Column(name="acquittement", type="string", length=250, nullable=true)
     */
    private $acquittement;

    /**
     * @var string|null
     *
     * @ORM\Column(name="MIB_associe", type="string", length=250, nullable=true)
     */
    private $mibAssocie;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Objet", type="string", length=250, nullable=true)
     */
    private $objet;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Compelement_information", type="string", length=250, nullable=true)
     */
    private $compelementInformation;

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

    public function getRefComposant(): ?string
    {
        return $this->refComposant;
    }

    public function setRefComposant(?string $refComposant): void
    {
        $this->refComposant = $refComposant;
    }

    public function getSignification(): ?string
    {
        return $this->signification;
    }

    public function setSignification(?string $signification): void
    {
        $this->signification = $signification;
    }

    public function getVersionSnmp(): ?string
    {
        return $this->versionSnmp;
    }

    public function setVersionSnmp(?string $versionSnmp): void
    {
        $this->versionSnmp = $versionSnmp;
    }

    public function getOid(): ?string
    {
        return $this->oid;
    }

    public function setOid(?string $oid): void
    {
        $this->oid = $oid;
    }

    public function getSpecificTrap(): ?string
    {
        return $this->specificTrap;
    }

    public function setSpecificTrap(?string $specificTrap): void
    {
        $this->specificTrap = $specificTrap;
    }

    public function getVariableBinding(): ?string
    {
        return $this->variableBinding;
    }

    public function setVariableBinding(?string $variableBinding): void
    {
        $this->variableBinding = $variableBinding;
    }

    public function getCriticite(): ?string
    {
        return $this->criticite;
    }

    public function setCriticite(?string $criticite): void
    {
        $this->criticite = $criticite;
    }

    public function getMessageAlarme(): ?string
    {
        return $this->messageAlarme;
    }

    public function setMessageAlarme(?string $messageAlarme): void
    {
        $this->messageAlarme = $messageAlarme;
    }

    public function getInstructions(): ?string
    {
        return $this->instructions;
    }

    public function setInstructions(?string $instructions): void
    {
        $this->instructions = $instructions;
    }

    public function getAcquittement(): ?string
    {
        return $this->acquittement;
    }

    public function setAcquittement(?string $acquittement): void
    {
        $this->acquittement = $acquittement;
    }

    public function getMibAssocie(): ?string
    {
        return $this->mibAssocie;
    }

    public function setMibAssocie(?string $mibAssocie): void
    {
        $this->mibAssocie = $mibAssocie;
    }

    public function getObjet(): ?string
    {
        return $this->objet;
    }

    public function setObjet(?string $objet): void
    {
        $this->objet = $objet;
    }

    public function getCompelementInformation(): ?string
    {
        return $this->compelementInformation;
    }

    public function setCompelementInformation(?string $compelementInformation): void
    {
        $this->compelementInformation = $compelementInformation;
    }
}
