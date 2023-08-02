<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * LogFiles
 *
 * @ORM\Table(name="log_files")
 * @ORM\Entity
 */
class LogFiles
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
     * @ORM\Column(name="Logfile", type="string", length=250, nullable=true)
     */
    private $logfile;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Localisation", type="string", length=250, nullable=true)
     */
    private $localisation;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Description", type="string", length=250, nullable=true)
     */
    private $description;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Format_logfile", type="string", length=250, nullable=true)
     */
    private $formatLogfile;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Separateur", type="string", length=250, nullable=true)
     */
    private $separateur;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Intervalle_de_polling", type="string", length=250, nullable=true)
     */
    private $intervalleDePolling;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Monitored_By", type="string", length=250, nullable=true)
     */
    private $monitoredBy;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Fourni_en_annexe", type="string", length=250, nullable=true)
     */
    private $fourniEnAnnexe;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Ref_Service", type="string", length=250, nullable=true)
     */
    private $refService;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Nom_Template", type="string", length=250, nullable=true)
     */
    private $nomTemplate;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Log_Conditions", type="string", length=250, nullable=true)
     */
    private $logConditions;

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

    public function getLogfile(): ?string
    {
        return $this->logfile;
    }

    public function setLogfile(?string $logfile): void
    {
        $this->logfile = $logfile;
    }

    public function getLocalisation(): ?string
    {
        return $this->localisation;
    }

    public function setLocalisation(?string $localisation): void
    {
        $this->localisation = $localisation;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): void
    {
        $this->description = $description;
    }

    public function getFormatLogfile(): ?string
    {
        return $this->formatLogfile;
    }

    public function setFormatLogfile(?string $formatLogfile): void
    {
        $this->formatLogfile = $formatLogfile;
    }

    public function getSeparateur(): ?string
    {
        return $this->separateur;
    }

    public function setSeparateur(?string $separateur): void
    {
        $this->separateur = $separateur;
    }

    public function getIntervalleDePolling(): ?string
    {
        return $this->intervalleDePolling;
    }

    public function setIntervalleDePolling(?string $intervalleDePolling): void
    {
        $this->intervalleDePolling = $intervalleDePolling;
    }

    public function getMonitoredBy(): ?string
    {
        return $this->monitoredBy;
    }

    public function setMonitoredBy(?string $monitoredBy): void
    {
        $this->monitoredBy = $monitoredBy;
    }

    public function getFourniEnAnnexe(): ?string
    {
        return $this->fourniEnAnnexe;
    }

    public function setFourniEnAnnexe(?string $fourniEnAnnexe): void
    {
        $this->fourniEnAnnexe = $fourniEnAnnexe;
    }

    public function getRefService(): ?string
    {
        return $this->refService;
    }

    public function setRefService(?string $refService): void
    {
        $this->refService = $refService;
    }

    public function getNomTemplate(): ?string
    {
        return $this->nomTemplate;
    }

    public function setNomTemplate(?string $nomTemplate): void
    {
        $this->nomTemplate = $nomTemplate;
    }

    public function getLogConditions(): ?string
    {
        return $this->logConditions;
    }

    public function setLogConditions(?string $logConditions): void
    {
        $this->logConditions = $logConditions;
    }


}
