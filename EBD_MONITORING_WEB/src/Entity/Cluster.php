<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Cluster
 *
 * @ORM\Table(name="cluster")
 * @ORM\Entity
 */
class Cluster
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
     * @ORM\Column(name="etat", type="string", length=250, nullable=true)
     */
    private $etat;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Nom_du_Ressource_Group__Package_Service_Guard", type="string", length=250, nullable=true)
     */
    private $nomDuRessourceGroupPackageServiceGuard;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Adresse_IP", type="string", length=250, nullable=true)
     */
    private $adresseIp;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Liste_des_serveurs_concernés", type="string", length=250, nullable=true)
     */
    private $listeDesServeursConcernés;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Logiciel_Cluster", type="string", length=250, nullable=true)
     */
    private $logicielCluster;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Version", type="string", length=250, nullable=true)
     */
    private $version;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Mode", type="string", length=250, nullable=true)
     */
    private $mode;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Serveur_actif", type="string", length=250, nullable=true)
     */
    private $serveurActif;

    /**
     * @var string|null
     *
     * @ORM\Column(name="Complements_informations", type="string", length=250, nullable=true)
     */
    private $complementsInformations;

    public function __construct()
    {
        // You can add any initialization logic here if needed.
    }

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

    public function getNomDuRessourceGroupPackageServiceGuard(): ?string
    {
        return $this->nomDuRessourceGroupPackageServiceGuard;
    }

    public function setNomDuRessourceGroupPackageServiceGuard(?string $nomDuRessourceGroupPackageServiceGuard): void
    {
        $this->nomDuRessourceGroupPackageServiceGuard = $nomDuRessourceGroupPackageServiceGuard;
    }

    public function getAdresseIp(): ?string
    {
        return $this->adresseIp;
    }

    public function setAdresseIp(?string $adresseIp): void
    {
        $this->adresseIp = $adresseIp;
    }

    public function getListeDesServeursConcernés(): ?string
    {
        return $this->listeDesServeursConcernés;
    }

    public function setListeDesServeursConcernés(?string $listeDesServeursConcernés): void
    {
        $this->listeDesServeursConcernés = $listeDesServeursConcernés;
    }

    public function getLogicielCluster(): ?string
    {
        return $this->logicielCluster;
    }

    public function setLogicielCluster(?string $logicielCluster): void
    {
        $this->logicielCluster = $logicielCluster;
    }

    public function getVersion(): ?string
    {
        return $this->version;
    }

    public function setVersion(?string $version): void
    {
        $this->version = $version;
    }

    public function getMode(): ?string
    {
        return $this->mode;
    }

    public function setMode(?string $mode): void
    {
        $this->mode = $mode;
    }

    public function getServeurActif(): ?string
    {
        return $this->serveurActif;
    }

    public function setServeurActif(?string $serveurActif): void
    {
        $this->serveurActif = $serveurActif;
    }

    public function getComplementsInformations(): ?string
    {
        return $this->complementsInformations;
    }

    public function setComplementsInformations(?string $complementsInformations): void
    {
        $this->complementsInformations = $complementsInformations;
    }
}
