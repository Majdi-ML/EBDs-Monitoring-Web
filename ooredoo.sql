-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 01 août 2023 à 14:33
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ooredoo`
--

-- --------------------------------------------------------

--
-- Structure de la table `cluster`
--

CREATE TABLE `cluster` (
  `id` varchar(250) NOT NULL,
  `Ref` varchar(250) DEFAULT NULL,
  `etat` varchar(250) DEFAULT NULL,
  `Nom_du_Ressource_Group__Package_Service_Guard` varchar(250) DEFAULT NULL,
  `Adresse_IP` varchar(250) DEFAULT NULL,
  `Liste_des_serveurs_concernés` varchar(250) DEFAULT NULL,
  `Logiciel_Cluster` varchar(250) DEFAULT NULL,
  `Version` varchar(250) DEFAULT NULL,
  `Mode` varchar(250) DEFAULT NULL,
  `Serveur_actif` varchar(250) DEFAULT NULL,
  `Compléments_informations` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `log_files`
--

CREATE TABLE `log_files` (
  `id` varchar(250) NOT NULL,
  `Ref` varchar(250) DEFAULT NULL,
  `Etat` varchar(250) DEFAULT NULL,
  `Ref_composant` varchar(250) DEFAULT NULL,
  `RG__SG_si_Cluster` varchar(250) DEFAULT NULL,
  `Logfile` varchar(250) DEFAULT NULL,
  `Localisation` varchar(250) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `Format_logfile` varchar(250) DEFAULT NULL,
  `Separateur` varchar(250) DEFAULT NULL,
  `Intervalle_de_polling` varchar(250) DEFAULT NULL,
  `Monitored_By` varchar(250) DEFAULT NULL,
  `Fourni_en_annexe` varchar(250) DEFAULT NULL,
  `Ref_Service` varchar(250) DEFAULT NULL,
  `Nom_Template` varchar(250) DEFAULT NULL,
  `Log_Conditions` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `log_files_patterns`
--

CREATE TABLE `log_files_patterns` (
  `id` varchar(250) NOT NULL,
  `N_Ref` int(11) DEFAULT NULL,
  `Ref` int(11) DEFAULT NULL,
  `Etat` varchar(250) DEFAULT NULL,
  `Signification` varchar(250) DEFAULT NULL,
  `Identification` text DEFAULT NULL,
  `Criticite` varchar(250) DEFAULT NULL,
  `Message_alarme` text DEFAULT NULL,
  `Instructions` varchar(250) DEFAULT NULL,
  `Perform_action` varchar(250) DEFAULT NULL,
  `Acquittement` varchar(250) DEFAULT NULL,
  `Complements_informations` varchar(250) DEFAULT NULL,
  `Ref_Service` varchar(250) DEFAULT NULL,
  `Objet` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `process`
--

CREATE TABLE `process` (
  `id` varchar(250) NOT NULL,
  `Ref` varchar(250) DEFAULT NULL,
  `Etat` varchar(250) DEFAULT NULL,
  `Ref_composant` varchar(250) DEFAULT NULL,
  `Process` varchar(250) DEFAULT NULL,
  `Criticite` varchar(250) DEFAULT NULL,
  `Message_alarme` text DEFAULT NULL,
  `Intervalle_de_polling` varchar(250) DEFAULT NULL,
  `Objet` varchar(250) DEFAULT NULL,
  `Nom_Template` varchar(250) DEFAULT NULL,
  `Monitored_By` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `requetessql`
--

CREATE TABLE `requetessql` (
  `id` varchar(250) NOT NULL,
  `Ref` varchar(250) DEFAULT NULL,
  `Etat` varchar(250) DEFAULT NULL,
  `Ref_composant` varchar(250) DEFAULT NULL,
  `RG__SG_si_Cluster` varchar(250) DEFAULT NULL,
  `Requete_SQL` text DEFAULT NULL,
  `UserName__DB_Name` varchar(250) DEFAULT NULL,
  `Resultat_Attendu_de_la_requete` text DEFAULT NULL,
  `Perform_action` varchar(250) DEFAULT NULL,
  `Criticite` varchar(250) DEFAULT NULL,
  `Message_alarme` text DEFAULT NULL,
  `Instructions` varchar(250) DEFAULT NULL,
  `Intervalle_de_polling` varchar(250) DEFAULT NULL,
  `Ref_Service` varchar(250) DEFAULT NULL,
  `Objet` varchar(250) DEFAULT NULL,
  `Monitored_By` varchar(250) DEFAULT NULL,
  `Nom_Template` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `scripts`
--

CREATE TABLE `scripts` (
  `id` varchar(250) NOT NULL,
  `Ref` varchar(250) DEFAULT NULL,
  `Etat` varchar(250) DEFAULT NULL,
  `Ref_composant` varchar(250) DEFAULT NULL,
  `RG__SG_si_Cluster` varchar(250) DEFAULT NULL,
  `script` varchar(250) DEFAULT NULL,
  `Code_erreur` varchar(250) DEFAULT NULL,
  `Criticite` varchar(250) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `Instructions` varchar(250) DEFAULT NULL,
  `Monitored_By` varchar(250) DEFAULT NULL,
  `Ref_Service` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `serveurs`
--

CREATE TABLE `serveurs` (
  `id` varchar(50) NOT NULL,
  `Ref` varchar(50) DEFAULT NULL,
  `Etat` varchar(50) DEFAULT NULL,
  `Platforme` varchar(50) DEFAULT NULL,
  `Hostname` varchar(50) DEFAULT NULL,
  `FQDN` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Modele` varchar(50) DEFAULT NULL,
  `OS` varchar(50) DEFAULT NULL,
  `Ver_tech__Firmware` varchar(50) DEFAULT NULL,
  `Cluster` varchar(50) DEFAULT NULL,
  `Ip_source` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Socle_Standard_OMU` varchar(50) DEFAULT NULL,
  `Complements_informations` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `traps_snmp`
--

CREATE TABLE `traps_snmp` (
  `id` varchar(250) NOT NULL,
  `Ref` varchar(250) DEFAULT NULL,
  `Etat` varchar(250) DEFAULT NULL,
  `Ref_composant` varchar(250) DEFAULT NULL,
  `Signification` varchar(250) DEFAULT NULL,
  `Version_SNMP` varchar(250) DEFAULT NULL,
  `OID` varchar(250) DEFAULT NULL,
  `Specific_Trap` varchar(250) DEFAULT NULL,
  `Variable_binding` varchar(250) DEFAULT NULL,
  `Criticite` varchar(250) DEFAULT NULL,
  `Message_alarme` varchar(250) DEFAULT NULL,
  `Instructions` varchar(250) DEFAULT NULL,
  `acquittement` varchar(250) DEFAULT NULL,
  `MIB_associe` varchar(250) DEFAULT NULL,
  `Objet` varchar(250) DEFAULT NULL,
  `Compelement_information` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `url`
--

CREATE TABLE `url` (
  `id` varchar(250) NOT NULL,
  `Ref` varchar(250) DEFAULT NULL,
  `Etat` varchar(250) DEFAULT NULL,
  `Ref_composant` varchar(250) DEFAULT NULL,
  `RG__SG_si_Cluster` varchar(250) DEFAULT NULL,
  `URL` varchar(250) DEFAULT NULL,
  `Perform_action` varchar(250) DEFAULT NULL,
  `Criticite` varchar(250) DEFAULT NULL,
  `Message_alarme` varchar(250) DEFAULT NULL,
  `Instructions` varchar(250) DEFAULT NULL,
  `Intervalle_de_polling` varchar(250) DEFAULT NULL,
  `Ref_Service` varchar(250) DEFAULT NULL,
  `Objet` varchar(250) DEFAULT NULL,
  `Monitored_by` varchar(250) DEFAULT NULL,
  `Nom_Template` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cluster`
--
ALTER TABLE `cluster`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `log_files`
--
ALTER TABLE `log_files`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `log_files_patterns`
--
ALTER TABLE `log_files_patterns`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `process`
--
ALTER TABLE `process`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `requetessql`
--
ALTER TABLE `requetessql`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `scripts`
--
ALTER TABLE `scripts`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `serveurs`
--
ALTER TABLE `serveurs`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Index pour la table `traps_snmp`
--
ALTER TABLE `traps_snmp`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `url`
--
ALTER TABLE `url`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
