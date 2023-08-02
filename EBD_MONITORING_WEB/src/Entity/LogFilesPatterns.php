<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * LogFilesPatterns
 *
 * @ORM\Table(name="log_files_patterns")
 * @ORM\Entity
 */
class LogFilesPatterns
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
     * @var int|null
     *
     * @ORM\Column(name="N_Ref", type="integer", nullable=true)
     */
    private $nRef;

    /**
     * @var int|null
     *
     * @ORM\Column(name="Ref", type="integer", nullable=true)
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
     * @ORM\Column(name="Signification", type="string", length=250, nullable=true)
     */
    private $signification;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Identification", type="text", length=65535, nullable=true)
     */
    private $identification;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Criticite", type="string", length=250, nullable=true)
     */
    private $criticite;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Message_alarme", type="text", length=65535, nullable=true)
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
     * @ORM\Column(name="Perform_action", type="string", length=250, nullable=true)
     */
    private $performAction;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Acquittement", type="string", length=250, nullable=true)
     */
    private $acquittement;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Complements_informations", type="string", length=250, nullable=true)
     */
    private $complementsInformations;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ref_Service", type="string", length=250, nullable=true)
     */
    private $refService;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Objet", type="string", length=250, nullable=true)
     */
    private $objet;

    public function getId(): string
    {
        return $this->id;
    }

    public function getNRef(): ?int
    {
        return $this->nRef;
    }

    public function setNRef(?int $nRef): void
    {
        $this->nRef = $nRef;
    }

    public function getRef(): ?int
    {
        return $this->ref;
    }

    public function setRef(?int $ref): void
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

    public function getSignification(): ?string
    {
        return $this->signification;
    }

    public function setSignification(?string $signification): void
    {
        $this->signification = $signification;
    }

    public function getIdentification(): ?string
    {
        return $this->identification;
    }

    public function setIdentification(?string $identification): void
    {
        $this->identification = $identification;
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

    public function getPerformAction(): ?string
    {
        return $this->performAction;
    }

    public function setPerformAction(?string $performAction): void
    {
        $this->performAction = $performAction;
    }

    public function getAcquittement(): ?string
    {
        return $this->acquittement;
    }

    public function setAcquittement(?string $acquittement): void
    {
        $this->acquittement = $acquittement;
    }

    public function getComplementsInformations(): ?string
    {
        return $this->complementsInformations;
    }

    public function setComplementsInformations(?string $complementsInformations): void
    {
        $this->complementsInformations = $complementsInformations;
    }

    public function getRefService(): ?string
    {
        return $this->refService;
    }

    public function setRefService(?string $refService): void
    {
        $this->refService = $refService;
    }

    public function getObjet(): ?string
    {
        return $this->objet;
    }

    public function setObjet(?string $objet): void
    {
        $this->objet = $objet;
    }
}
