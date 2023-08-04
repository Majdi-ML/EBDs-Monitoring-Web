-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 04 août 2023 à 14:50
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
  `Complements_informations` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cluster`
--

INSERT INTO `cluster` (`id`, `Ref`, `etat`, `Nom_du_Ressource_Group__Package_Service_Guard`, `Adresse_IP`, `Liste_des_serveurs_concernés`, `Logiciel_Cluster`, `Version`, `Mode`, `Serveur_actif`, `Complements_informations`, `Support`) VALUES
('EBD_MAJDI_Cluster_1', 'Rg-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Cluster_2', 'Rg-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Cluster_1', 'Rg-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Cluster_2', 'Rg-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_Cluster_1', 'Rg-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Cluster_2', 'Rg-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Cluster_3', 'Rg-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_Cluster_1', 'Rg-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Cluster_2', 'Rg-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx');

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
  `Log_Conditions` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `log_files`
--

INSERT INTO `log_files` (`id`, `Ref`, `Etat`, `Ref_composant`, `RG__SG_si_Cluster`, `Logfile`, `Localisation`, `Description`, `Format_logfile`, `Separateur`, `Intervalle_de_polling`, `Monitored_By`, `Fourni_en_annexe`, `Ref_Service`, `Nom_Template`, `Log_Conditions`, `Support`) VALUES
('EBD_MAJDI_LogFiles_1', 'Lg-01', 'Nouveau', 'Sv-01 Sv-02', NULL, '/apps/apache-tomcat-7.0.73/logs/convlog/mga.log', NULL, NULL, NULL, NULL, 'chaque 5 mins', 'OMU', NULL, NULL, 'OOREDOO-LOG-POSANET-WEBSERVICE-ERROR ', 'Alerte-Critique-POSANET-Readtimeout\nAlerte-Critique-POSANET-Connection Reset', 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_LogFiles_2', 'Lg-02', 'Nouveau', 'Sv-01 Sv-03', NULL, 'posanet.log', '/apps/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_LogFiles_3', 'Lg-03', 'Nouveau', 'Sv-01 Sv-04', NULL, 'posanet.log', '/apps2/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_LogFiles_1', 'Lg-01', 'Nouveau', 'Sv-01 Sv-02', NULL, '/apps/apache-tomcat-7.0.73/logs/convlog/mga.log', NULL, NULL, NULL, NULL, 'chaque 5 mins', 'OMU', NULL, NULL, 'OOREDOO-LOG-POSANET-WEBSERVICE-ERROR ', 'Alerte-Critique-POSANET-Readtimeout\nAlerte-Critique-POSANET-Connection Reset', 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_LogFiles_2', 'Lg-02', 'Nouveau', 'Sv-01 Sv-03', NULL, 'posanet.log', '/apps/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_LogFiles_3', 'Lg-03', 'Nouveau', 'Sv-01 Sv-04', NULL, 'posanet.log', '/apps2/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_LogFiles_1', 'Lg-01', 'Nouveau', 'Sv-01 Sv-02', NULL, '/apps/apache-tomcat-7.0.73/logs/convlog/mga.log', NULL, NULL, NULL, NULL, 'chaque 5 mins', 'OMU', NULL, NULL, 'OOREDOO-LOG-POSANET-WEBSERVICE-ERROR ', 'Alerte-Critique-POSANET-Readtimeout\nAlerte-Critique-POSANET-Connection Reset', 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_LogFiles_2', 'Lg-02', 'Nouveau', 'Sv-01 Sv-03', NULL, 'posanet.log', '/apps/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_LogFiles_3', 'Lg-03', 'Nouveau', 'Sv-01 Sv-04', NULL, 'posanet.log', '/apps2/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_LogFiles_4', 'Lg-04', 'Nouveau', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_LogFiles_1', 'Lg-01', 'Nouveau', 'Sv-01 Sv-02', NULL, '/apps/apache-tomcat-7.0.73/logs/convlog/mga.log', NULL, NULL, NULL, NULL, 'chaque 5 mins', 'OMU', NULL, NULL, 'OOREDOO-LOG-POSANET-WEBSERVICE-ERROR ', 'Alerte-Critique-POSANET-Readtimeout\nAlerte-Critique-POSANET-Connection Reset', 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_LogFiles_2', 'Lg-02', 'Nouveau', 'Sv-01 Sv-03', NULL, 'posanet.log', '/apps/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_LogFiles_3', 'Lg-03', 'Nouveau', 'Sv-01 Sv-04', NULL, 'posanet.log', '/apps2/logs/posanet/', NULL, NULL, NULL, 'chaque 30 sec', 'OMU', NULL, NULL, NULL, 'MEMORY-CRITIQUE', 'CLOUD+APPIT .xlsx');

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
  `Objet` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `log_files_patterns`
--

INSERT INTO `log_files_patterns` (`id`, `N_Ref`, `Ref`, `Etat`, `Signification`, `Identification`, `Criticite`, `Message_alarme`, `Instructions`, `Perform_action`, `Acquittement`, `Complements_informations`, `Ref_Service`, `Objet`, `Support`) VALUES
('EBD_MAJDI_LogFilesPatterns_Lg-01', 1, 1, 'Nouveau', NULL, 'I/O error: Connection reset; nested exception is java.net.SocketException: Connection reset', 'Critique', 'Connection reset: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_LogFilesPatterns_Lg-02', 1, 1, 'Nouveau', NULL, '\nI/O error: Read timed out; nested exception is java.net.SocketTimeoutException: Read timed out', 'Critique', 'Read timed out: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_LogFilesPatterns_Lg-03', 2, 2, 'Nouveau', NULL, 'java.lang.OutOfMemoryError: GC overhead limit exceeded', 'Critique', 'Problème java.lang.OutOfMemoryError: GC overhead limit exceeded constaté sur le serveur ($HOSTNAME), Merci de vérifier les paramètres mémoire du serveur d’application (xmms), Merci de contacter l equipe Support Cloud ', 'Merci de contacter l equipe Support Cloud', NULL, NULL, NULL, NULL, 'check_functional_log_MEMORY_POSANET', 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_LogFilesPatterns_Lg-01', 1, 1, 'Nouveau', NULL, 'I/O error: Connection reset; nested exception is java.net.SocketException: Connection reset', 'Critique', 'Connection reset: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_LogFilesPatterns_Lg-02', 1, 1, 'Nouveau', NULL, '\nI/O error: Read timed out; nested exception is java.net.SocketTimeoutException: Read timed out', 'Critique', 'Read timed out: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_LogFilesPatterns_Lg-03', 2, 2, 'Nouveau', NULL, 'java.lang.OutOfMemoryError: GC overhead limit exceeded', 'Critique', 'Problème java.lang.OutOfMemoryError: GC overhead limit exceeded constaté sur le serveur ($HOSTNAME), Merci de vérifier les paramètres mémoire du serveur d’application (xmms), Merci de contacter l equipe Support Cloud ', 'Merci de contacter l equipe Support Cloud', NULL, NULL, NULL, NULL, 'check_functional_log_MEMORY_POSANET', 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_LogFilesPatterns_Lg-01', 1, 1, 'Nouveau', NULL, 'I/O error: Connection reset; nested exception is java.net.SocketException: Connection reset', 'Critique', 'Connection reset: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_LogFilesPatterns_Lg-02', 1, 1, 'Nouveau', NULL, '\nI/O error: Read timed out; nested exception is java.net.SocketTimeoutException: Read timed out', 'Critique', 'Read timed out: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_LogFilesPatterns_Lg-03', 2, 2, 'Nouveau', NULL, 'java.lang.OutOfMemoryError: GC overhead limit exceeded', 'Critique', 'Problème java.lang.OutOfMemoryError: GC overhead limit exceeded constaté sur le serveur ($HOSTNAME), Merci de vérifier les paramètres mémoire du serveur d’application (xmms), Merci de contacter l equipe Support Cloud ', 'Merci de contacter l equipe Support Cloud', NULL, NULL, NULL, NULL, 'check_functional_log_MEMORY_POSANET', 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_LogFilesPatterns_Lg-04', 3, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_LogFilesPatterns_Lg-01', 1, 1, 'Nouveau', NULL, 'I/O error: Connection reset; nested exception is java.net.SocketException: Connection reset', 'Critique', 'Connection reset: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_LogFilesPatterns_Lg-02', 1, 1, 'Nouveau', NULL, '\nI/O error: Read timed out; nested exception is java.net.SocketTimeoutException: Read timed out', 'Critique', 'Read timed out: Web service Down. Merci de contacter Support Billing', 'Merci de contacter Support Billing', NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_LogFilesPatterns_Lg-03', 2, 2, 'Nouveau', NULL, 'java.lang.OutOfMemoryError: GC overhead limit exceeded', 'Critique', 'Problème java.lang.OutOfMemoryError: GC overhead limit exceeded constaté sur le serveur ($HOSTNAME), Merci de vérifier les paramètres mémoire du serveur d’application (xmms), Merci de contacter l equipe Support Cloud ', 'Merci de contacter l equipe Support Cloud', NULL, NULL, NULL, NULL, 'check_functional_log_MEMORY_POSANET', 'CLOUD+APPIT .xlsx');

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
  `Monitored_By` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `process`
--

INSERT INTO `process` (`id`, `Ref`, `Etat`, `Ref_composant`, `Process`, `Criticite`, `Message_alarme`, `Intervalle_de_polling`, `Objet`, `Nom_Template`, `Monitored_By`, `Support`) VALUES
('EBD_MAJDI_Process_1', 'Pr-01', 'Inchangé', 'Sv-01\nSv-03', 'check nombre de connexion', 'Majeure', ' Le nombre des connexions FIN_WAIT2 est  (superieur au seuil tolere : > 150). Contacter l\'astreinte Systeme.', 'lancer chaque 15 mins', 'nombre_connexion', 'OOREDOO-MON-POSANET_nombre_connexion', 'OMU', 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Process_2', 'Pr-02', 'Inchangé', 'Sv-02\nSv-04', 'check_cluster_status', 'Critique', ' Au moins une instance est OFFLINE au niveau du cluster RAC.                                                                                                                                                                                                                                                                                                                                                                                                                                     ', 'chaque 5mins', 'cluster_status', 'OOREDOO-MON-RAC-check_cluster_status', 'OMU', 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Process_3', 'Pr-03', 'Inchangé', 'Sv-06 Sv-07', 'check Log ', 'Critique', 'Probléme de connexion ', 'chaque 30 min', 'activité', ' tail -f  /apps/apache-tomcat-8.0.30/logs/catalina.out', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Process_1', 'Pr-01', 'Inchangé', 'Sv-01\nSv-03', 'check nombre de connexion', 'Majeure', ' Le nombre des connexions FIN_WAIT2 est  (superieur au seuil tolere : > 150). Contacter l\'astreinte Systeme.', 'lancer chaque 15 mins', 'nombre_connexion', 'OOREDOO-MON-POSANET_nombre_connexion', 'OMU', 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Process_2', 'Pr-02', 'Inchangé', 'Sv-02\nSv-04', 'check_cluster_status', 'Critique', ' Au moins une instance est OFFLINE au niveau du cluster RAC.                                                                                                                                                                                                                                                                                                                                                                                                                                     ', 'chaque 5mins', 'cluster_status', 'OOREDOO-MON-RAC-check_cluster_status', 'OMU', 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Process_3', 'Pr-03', 'Inchangé', 'Sv-06 Sv-07', 'check Log ', 'Critique', 'Probléme de connexion ', 'chaque 30 min', 'activité', ' tail -f  /apps/apache-tomcat-8.0.30/logs/catalina.out', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_Process_1', 'Pr-01', 'Inchangé', 'Sv-01\nSv-03', 'check nombre de connexion', 'Majeure', ' Le nombre des connexions FIN_WAIT2 est  (superieur au seuil tolere : > 150). Contacter l\'astreinte Systeme.', 'lancer chaque 15 mins', 'nombre_connexion', 'OOREDOO-MON-POSANET_nombre_connexion', 'OMU', 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Process_2', 'Pr-02', 'Inchangé', 'Sv-02\nSv-04', 'check_cluster_status', 'Critique', ' Au moins une instance est OFFLINE au niveau du cluster RAC.                                                                                                                                                                                                                                                                                                                                                                                                                                     ', 'chaque 5mins', 'cluster_status', 'OOREDOO-MON-RAC-check_cluster_status', 'OMU', 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Process_3', 'Pr-03', 'Inchangé', 'Sv-06 Sv-07', 'check Log ', 'Critique', 'Probléme de connexion ', 'chaque 30 min', 'activité', ' tail -f  /apps/apache-tomcat-8.0.30/logs/catalina.out', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Process_4', 'Pr-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_Process_1', 'Pr-01', 'Inchangé', 'Sv-01\nSv-03', 'check nombre de connexion', 'Majeure', ' Le nombre des connexions FIN_WAIT2 est  (superieur au seuil tolere : > 150). Contacter l\'astreinte Systeme.', 'lancer chaque 15 mins', 'nombre_connexion', 'OOREDOO-MON-POSANET_nombre_connexion', 'OMU', 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Process_2', 'Pr-02', 'Inchangé', 'Sv-02\nSv-04', 'check_cluster_status', 'Critique', ' Au moins une instance est OFFLINE au niveau du cluster RAC.                                                                                                                                                                                                                                                                                                                                                                                                                                     ', 'chaque 5mins', 'cluster_status', 'OOREDOO-MON-RAC-check_cluster_status', 'OMU', 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Process_3', 'Pr-03', 'Inchangé', 'Sv-06 Sv-07', 'check Log ', 'Critique', 'Probléme de connexion ', 'chaque 30 min', 'activité', ' tail -f  /apps/apache-tomcat-8.0.30/logs/catalina.out', NULL, 'CLOUD+APPIT .xlsx');

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
  `Nom_Template` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `requetessql`
--

INSERT INTO `requetessql` (`id`, `Ref`, `Etat`, `Ref_composant`, `RG__SG_si_Cluster`, `Requete_SQL`, `UserName__DB_Name`, `Resultat_Attendu_de_la_requete`, `Perform_action`, `Criticite`, `Message_alarme`, `Instructions`, `Intervalle_de_polling`, `Ref_Service`, `Objet`, `Monitored_By`, `Nom_Template`, `Support`) VALUES
('EBD_MAJDI_RequetesSql_1', 'sql-01', 'Nouveau', 'Sv-02\nSv-05', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_10', 'sql-10', 'Nouveau', 'Sv-02\nSv-14', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le', 'pport cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_12', 'sql-12', 'Nouveau', 'Sv-02\nSv-15', NULL, NULL, 'DB_USER=hpito/ DB=DBCACHE', 'Cette', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignesFixe a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_2', 'sql-02', 'Nouveau', 'Sv-02\nSv-06', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_3', 'sql-03', 'Nouveau', 'Sv-02\nSv-07', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_4', 'sql-04', 'Nouveau', 'Sv-02\nSv-08', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_5', 'sql-05', 'Nouveau', 'Sv-02\nSv-09', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des Clé/ Mifi a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_6', 'sql-06', 'Nouveau', 'Sv-02\nSv-10', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes clé/Mifi a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_7', 'sql-07', 'Nouveau', 'Sv-02\nSv-11', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_8', 'sql-08', 'Nouveau', 'Sv-02\nSv-12', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_RequetesSql_9', 'sql-09', 'Nouveau', 'Sv-02\nSv-13', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles Fixe a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 he', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_1', 'sql-01', 'Nouveau', 'Sv-02\nSv-05', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_10', 'sql-10', 'Nouveau', 'Sv-02\nSv-14', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le', 'pport cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_12', 'sql-12', 'Nouveau', 'Sv-02\nSv-15', NULL, NULL, 'DB_USER=hpito/ DB=DBCACHE', 'Cette', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignesFixe a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_2', 'sql-02', 'Nouveau', 'Sv-02\nSv-06', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_3', 'sql-03', 'Nouveau', 'Sv-02\nSv-07', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_4', 'sql-04', 'Nouveau', 'Sv-02\nSv-08', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_5', 'sql-05', 'Nouveau', 'Sv-02\nSv-09', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des Clé/ Mifi a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_6', 'sql-06', 'Nouveau', 'Sv-02\nSv-10', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes clé/Mifi a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_7', 'sql-07', 'Nouveau', 'Sv-02\nSv-11', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_8', 'sql-08', 'Nouveau', 'Sv-02\nSv-12', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_RequetesSql_9', 'sql-09', 'Nouveau', 'Sv-02\nSv-13', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles Fixe a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 he', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_RequetesSql_1', 'sql-01', 'Nouveau', 'Sv-02\nSv-05', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_10', 'sql-10', 'Nouveau', 'Sv-02\nSv-14', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignesFixe a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_2', 'sql-02', 'Nouveau', 'Sv-02\nSv-06', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_3', 'sql-03', 'Nouveau', 'Sv-02\nSv-07', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_4', 'sql-04', 'Nouveau', 'Sv-02\nSv-08', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_5', 'sql-05', 'Nouveau', 'Sv-02\nSv-09', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des Clé/ Mifi a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_6', 'sql-06', 'Nouveau', 'Sv-02\nSv-10', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes clé/Mifi a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_7', 'sql-07', 'Nouveau', 'Sv-02\nSv-11', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_8', 'sql-08', 'Nouveau', 'Sv-02\nSv-12', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_RequetesSql_9', 'sql-09', 'Nouveau', 'Sv-02\nSv-13', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles Fixe a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_RequetesSql_1', 'sql-01', 'Nouveau', 'Sv-02\nSv-05', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_10', 'sql-10', 'Nouveau', 'Sv-02\nSv-14', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le', 'pport cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_12', 'sql-12', 'Nouveau', 'Sv-02\nSv-15', NULL, NULL, 'DB_USER=hpito/ DB=DBCACHE', 'Cette', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignesFixe a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_2', 'sql-02', 'Nouveau', 'Sv-02\nSv-06', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.awal_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles avec choix a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_3', 'sql-03', 'Nouveau', 'Sv-02\nSv-07', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_4', 'sql-04', 'Nouveau', 'Sv-02\nSv-08', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.prepaid_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes mobiles sans choix de numero a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_5', 'sql-05', 'Nouveau', 'Sv-02\nSv-09', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des Clé/ Mifi a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_6', 'sql-06', 'Nouveau', 'Sv-02\nSv-10', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.data4g_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes clé/Mifi a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_7', 'sql-07', 'Nouveau', 'Sv-02\nSv-11', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_8', 'sql-08', 'Nouveau', 'Sv-02\nSv-12', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.box_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest > 5 une alerte doit être déclenchée.', NULL, 'Critique', 'Le ratio d\'échec d\'activation des lignes Box a atteint un éta critique  . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 heures', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_RequetesSql_9', 'sql-09', 'Nouveau', 'Sv-02\nSv-13', NULL, 'select max(percentage) as percentage from (\nSELECT transaction_status,\n            COUNT ( * ) as count ,\n            count(*) * 100.0 / sum(count(*)) over() as percentage\nFROM POSANET.fixe_activation_transaction\nWHERE creation_date >= TRUNC (SYSDATE) and creation_date < TRUNC (SYSDATE+1)\ngroup by transaction_status\nunion all\nselect -3,0,0 as percentage\nfrom dual\n)\nwhere  transaction_status = -3;\n', 'DB_USER=hpito/ DB=DBCACHE', 'Cette requête s\'il y a Un probleme au niveau des Activations Mobiles avec choix sur POSANET\n et si resultatest entre 2 et 5 une alerte doit être déclenchée.', NULL, 'Majeure', 'Le ratio d\'échec d\'activation des lignes mobiles Fixe a atteint un éta majeur . Merci de contacter l equipe support cloud', 'En cas de probleme merci de contacter l equipe support cloud', 'lancer chaque 2 he', 'POSANET', 'POSANET', 'OMU', NULL, 'CLOUD+APPIT .xlsx');

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
  `Ref_Service` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `scripts`
--

INSERT INTO `scripts` (`id`, `Ref`, `Etat`, `Ref_composant`, `RG__SG_si_Cluster`, `script`, `Code_erreur`, `Criticite`, `Description`, `Instructions`, `Monitored_By`, `Ref_Service`, `Support`) VALUES
('EBD_MAJDI_Scripts_1', 'Ast-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Scripts_2', 'Ast-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Scripts_3', 'Ast-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Scripts_4', 'Ast-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Scripts_5', 'Ast-05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Scripts_6', 'Ast-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Scripts_1', 'Ast-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Scripts_2', 'Ast-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Scripts_3', 'Ast-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_Scripts_1', 'Ast-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Scripts_2', 'Ast-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Scripts_3', 'Ast-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Scripts_4', 'Ast-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_Scripts_1', 'Ast-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Scripts_2', 'Ast-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Scripts_3', 'Ast-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx');

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
  `Complements_informations` varchar(50) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `serveurs`
--

INSERT INTO `serveurs` (`id`, `Ref`, `Etat`, `Platforme`, `Hostname`, `FQDN`, `Type`, `Modele`, `OS`, `Ver_tech__Firmware`, `Cluster`, `Ip_source`, `Description`, `Socle_Standard_OMU`, `Complements_informations`, `Support`) VALUES
('EBD_MAJDI_Serveurs_1', 'Sv-01', 'Inchangé', 'Prod', 'vmposanet2.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Serveurs_2', 'Sv-02', 'Inchangé', 'Prod', 'vmposadb02.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Serveurs_3', 'Sv-03', 'Inchangé', 'Prod', 'vmposanet1.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Serveurs_4', 'Sv-04', 'Inchangé', 'Prod', 'vmposadb01.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Serveurs_5', 'Sv-05', 'Inchangé', 'Prod', 'vmposanet1dr', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Serveurs_6', 'Sv-06', 'Inchangé', 'Prod', 'vmstcws1', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Serveurs_7', 'Sv-07', 'Inchangé', 'Prod', 'vmstcws2', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Serveurs_1', 'Sv-01', 'Inchangé', 'Prod', 'vmposanet2.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Serveurs_2', 'Sv-02', 'Inchangé', 'Prod', 'vmposadb02.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Serveurs_3', 'Sv-03', 'Inchangé', 'Prod', 'vmposanet1.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Serveurs_4', 'Sv-04', 'Inchangé', 'Prod', 'vmposadb01.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Serveurs_5', 'Sv-05', 'Inchangé', 'Prod', 'vmposanet1dr', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Serveurs_6', 'Sv-06', 'Inchangé', 'Prod', 'vmstcws1', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Serveurs_7', 'Sv-07', 'Inchangé', 'Prod', 'vmstcws2', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_Serveurs_1', 'Sv-01', 'Inchangé', 'Prod', 'vmposanet2.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Serveurs_2', 'Sv-02', 'Inchangé', 'Prod', 'vmposadb02.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Serveurs_3', 'Sv-03', 'Inchangé', 'Prod', 'vmposanet1.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Serveurs_4', 'Sv-04', 'Inchangé', 'Prod', 'vmposadb01.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Serveurs_5', 'Sv-05', 'Inchangé', 'Prod', 'vmposanet1dr', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Serveurs_6', 'Sv-06', 'Inchangé', 'Prod', 'vmstcws1', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Serveurs_7', 'Sv-07', 'Inchangé', 'Prod', 'vmstcws2', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Serveurs_8', 'Sv-08', 'Inchangé', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_Serveurs_1', 'Sv-01', 'Inchangé', 'Prod', 'vmposanet2.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Serveurs_2', 'Sv-02', 'Inchangé', 'Prod', 'vmposadb02.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Serveurs_3', 'Sv-03', 'Inchangé', 'Prod', 'vmposanet1.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Serveurs_4', 'Sv-04', 'Inchangé', 'Prod', 'vmposadb01.orascomtunisie.com', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Serveurs_5', 'Sv-05', 'Inchangé', 'Prod', 'vmposanet1dr', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, 'Oui', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Serveurs_6', 'Sv-06', 'Inchangé', 'Prod', 'vmstcws1', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Serveurs_7', 'Sv-07', 'Inchangé', 'Prod', 'vmstcws2', NULL, 'Serveur', NULL, 'Linux', NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx');

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
  `Compelement_information` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `traps_snmp`
--

INSERT INTO `traps_snmp` (`id`, `Ref`, `Etat`, `Ref_composant`, `Signification`, `Version_SNMP`, `OID`, `Specific_Trap`, `Variable_binding`, `Criticite`, `Message_alarme`, `Instructions`, `acquittement`, `MIB_associe`, `Objet`, `Compelement_information`, `Support`) VALUES
('EBD_MAJDI_TrapsSNMP_1', 'Snmp-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_TrapsSNMP_2', 'Snmp-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_TrapsSNMP_3', 'Snmp-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_TrapsSNMP_1', 'Snmp-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_TrapsSNMP_2', 'Snmp-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_TrapsSNMP_3', 'Snmp-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_TrapsSNMP_1', 'Snmp-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_TrapsSNMP_2', 'Snmp-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_TrapsSNMP_3', 'Snmp-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_TrapsSNMP_4', 'Snmp-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_TrapsSNMP_1', 'Snmp-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_TrapsSNMP_2', 'Snmp-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_TrapsSNMP_3', 'Snmp-03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT .xlsx');

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
  `Nom_Template` varchar(250) DEFAULT NULL,
  `Support` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `url`
--

INSERT INTO `url` (`id`, `Ref`, `Etat`, `Ref_composant`, `RG__SG_si_Cluster`, `URL`, `Perform_action`, `Criticite`, `Message_alarme`, `Instructions`, `Intervalle_de_polling`, `Ref_Service`, `Objet`, `Monitored_by`, `Nom_Template`, `Support`) VALUES
('EBD_MAJDI_Url_1', 'Url-01', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8080/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Url_2', 'Url-02', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8080/login.form ne repond pas. Merci de Contacter support cloud.', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Url_3', 'Url-03', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8081/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_MAJDI_Url_4', 'Url-04', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8081/login.form ne repond pas.  Merci de Contacter support cloud.                                                                                                                                                                ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Url_1', 'Url-01', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8080/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Url_2', 'Url-02', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8080/login.form ne repond pas. Merci de Contacter support cloud.', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Url_3', 'Url-03', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8081/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_OOREDOO_Url_4', 'Url-04', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8081/login.form ne repond pas.  Merci de Contacter support cloud.                                                                                                                                                                ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_POSANET_Url_1', 'Url-01', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8080/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Url_2', 'Url-02', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8080/login.form ne repond pas. Merci de Contacter support cloud.', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Url_3', 'Url-03', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8081/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Url_4', 'Url-04', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8081/login.form ne repond pas.  Merci de Contacter support cloud.                                                                                                                                                                ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT.xlsx'),
('EBD_POSANET_Url_5', 'Url-05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CLOUD+APPIT.xlsx'),
('EBD_TELNET_Url_1', 'Url-01', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8080/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Url_2', 'Url-02', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8080/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8080/login.form ne repond pas. Merci de Contacter support cloud.', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Url_3', 'Url-03', 'Nouveau', 'vmposanet1.orascomtunisie.com', NULL, 'http://172.16.6.108:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet1:8081/login.form ne repond pas.    Merci de Contacter support cloud.                                                                                                                                                              ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx'),
('EBD_TELNET_Url_4', 'Url-04', 'Nouveau', 'vmposanet2.orascomtunisie.com', NULL, 'http://172.16.6.109:8081/login.form', NULL, 'Critique', ' L\'URL http://vmposanet2:8081/login.form ne repond pas.  Merci de Contacter support cloud.                                                                                                                                                                ', ' Merci de Contacter support cloud.', 'lancer chaque 5 mins', NULL, 'check_functional_web_url_POSANET', 'Sitescope 2', NULL, 'CLOUD+APPIT .xlsx');

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
