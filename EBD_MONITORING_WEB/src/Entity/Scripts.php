<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Scripts
 *
 * @ORM\Table(name="scripts")
 * @ORM\Entity
 */
class Scripts
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
     * @ORM\Column(name="RG__SG_si_Cluster", type="string", length=250, nullable=true)
     */
    private $rgSgSiCluster;

    /**
     * @var string|null
     *
     * @ORM\Column(name="script", type="string", length=250, nullable=true)
     */
    private $script;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Code_erreur", type="string", length=250, nullable=true)
     */
    private $codeErreur;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Criticite", type="string", length=250, nullable=true)
     */
    private $criticite;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Description", type="string", length=250, nullable=true)
     */
    private $description;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Instructions", type="string", length=250, nullable=true)
     */
    private $instructions;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Monitored_By", type="string", length=250, nullable=true)
     */
    private $monitoredBy;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ref_Service", type="string", length=250, nullable=true)
     */
    private $refService;

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

    public function getRgSgSiCluster(): ?string
    {
        return $this->rgSgSiCluster;
    }

    public function setRgSgSiCluster(?string $rgSgSiCluster): void
    {
        $this->rgSgSiCluster = $rgSgSiCluster;
    }

    public function getScript(): ?string
    {
        return $this->script;
    }

    public function setScript(?string $script): void
    {
        $this->script = $script;
    }

    public function getCodeErreur(): ?string
    {
        return $this->codeErreur;
    }

    public function setCodeErreur(?string $codeErreur): void
    {
        $this->codeErreur = $codeErreur;
    }

    public function getCriticite(): ?string
    {
        return $this->criticite;
    }

    public function setCriticite(?string $criticite): void
    {
        $this->criticite = $criticite;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): void
    {
        $this->description = $description;
    }

    public function getInstructions(): ?string
    {
        return $this->instructions;
    }

    public function setInstructions(?string $instructions): void
    {
        $this->instructions = $instructions;
    }

    public function getMonitoredBy(): ?string
    {
        return $this->monitoredBy;
    }

    public function setMonitoredBy(?string $monitoredBy): void
    {
        $this->monitoredBy = $monitoredBy;
    }

    public function getRefService(): ?string
    {
        return $this->refService;
    }

    public function setRefService(?string $refService): void
    {
        $this->refService = $refService;
    }
}
