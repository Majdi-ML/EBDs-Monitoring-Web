<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Process
 *
 * @ORM\Table(name="process")
 * @ORM\Entity
 */
class Process
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
     * @ORM\Column(name="Process", type="string", length=250, nullable=true)
     */
    private $process;

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
     * @ORM\Column(name="Intervalle_de_polling", type="string", length=250, nullable=true)
     */
    private $intervalleDePolling;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Objet", type="string", length=250, nullable=true)
     */
    private $objet;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Nom_Template", type="string", length=250, nullable=true)
     */
    private $nomTemplate;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Monitored_By", type="string", length=250, nullable=true)
     */
    private $monitoredBy;

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

    public function getProcess(): ?string
    {
        return $this->process;
    }

    public function setProcess(?string $process): void
    {
        $this->process = $process;
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

    public function getIntervalleDePolling(): ?string
    {
        return $this->intervalleDePolling;
    }

    public function setIntervalleDePolling(?string $intervalleDePolling): void
    {
        $this->intervalleDePolling = $intervalleDePolling;
    }

    public function getObjet(): ?string
    {
        return $this->objet;
    }

    public function setObjet(?string $objet): void
    {
        $this->objet = $objet;
    }

    public function getNomTemplate(): ?string
    {
        return $this->nomTemplate;
    }

    public function setNomTemplate(?string $nomTemplate): void
    {
        $this->nomTemplate = $nomTemplate;
    }

    public function getMonitoredBy(): ?string
    {
        return $this->monitoredBy;
    }

    public function setMonitoredBy(?string $monitoredBy): void
    {
        $this->monitoredBy = $monitoredBy;
    }
}
