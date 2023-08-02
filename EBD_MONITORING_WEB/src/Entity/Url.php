<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Url
 *
 * @ORM\Table(name="url")
 * @ORM\Entity
 */
class Url
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
     * @ORM\Column(name="URL", type="string", length=250, nullable=true)
     */
    private $url;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Perform_action", type="string", length=250, nullable=true)
     */
    private $performAction;

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
     * @ORM\Column(name="Intervalle_de_polling", type="string", length=250, nullable=true)
     */
    private $intervalleDePolling;

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

    /**
     * @var string|null
     *
     * @ORM\Column(name="Monitored_by", type="string", length=250, nullable=true)
     */
    private $monitoredBy;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Nom_Template", type="string", length=250, nullable=true)
     */
    private $nomTemplate;

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

    public function getUrl(): ?string
    {
        return $this->url;
    }

    public function setUrl(?string $url): void
    {
        $this->url = $url;
    }

    public function getPerformAction(): ?string
    {
        return $this->performAction;
    }

    public function setPerformAction(?string $performAction): void
    {
        $this->performAction = $performAction;
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

    public function getIntervalleDePolling(): ?string
    {
        return $this->intervalleDePolling;
    }

    public function setIntervalleDePolling(?string $intervalleDePolling): void
    {
        $this->intervalleDePolling = $intervalleDePolling;
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

    public function getMonitoredBy(): ?string
    {
        return $this->monitoredBy;
    }

    public function setMonitoredBy(?string $monitoredBy): void
    {
        $this->monitoredBy = $monitoredBy;
    }

    public function getNomTemplate(): ?string
    {
        return $this->nomTemplate;
    }

    public function setNomTemplate(?string $nomTemplate): void
    {
        $this->nomTemplate = $nomTemplate;
    }

}
