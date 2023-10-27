<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20231003133353 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE cluster (id VARCHAR(250) NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, nom_du_ressource_group_package_service_guard VARCHAR(250) DEFAULT NULL, adresse_ip VARCHAR(250) DEFAULT NULL, liste_des_serveurs_concernes VARCHAR(250) DEFAULT NULL, logiciel_cluster VARCHAR(250) DEFAULT NULL, version VARCHAR(250) DEFAULT NULL, mode VARCHAR(250) DEFAULT NULL, serveur_actif VARCHAR(250) DEFAULT NULL, complements_informations VARCHAR(250) DEFAULT NULL, support VARCHAR(250) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE log_files (id VARCHAR(250) NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, ref_composant VARCHAR(250) DEFAULT NULL, rg_sg_si_cluster VARCHAR(250) DEFAULT NULL, logfile VARCHAR(250) DEFAULT NULL, localisation VARCHAR(250) DEFAULT NULL, description VARCHAR(250) DEFAULT NULL, format_logfile VARCHAR(250) DEFAULT NULL, separateur VARCHAR(250) DEFAULT NULL, intervalle_de_polling VARCHAR(250) DEFAULT NULL, monitored_by VARCHAR(250) DEFAULT NULL, fourni_en_annexe VARCHAR(250) DEFAULT NULL, ref_service VARCHAR(250) DEFAULT NULL, nom_template VARCHAR(250) DEFAULT NULL, log_conditions VARCHAR(250) DEFAULT NULL, support VARCHAR(250) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE log_files_patterns (id VARCHAR(250) NOT NULL, n_ref INT NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, signification VARCHAR(250) DEFAULT NULL, identification VARCHAR(250) DEFAULT NULL, criticite VARCHAR(250) DEFAULT NULL, message_alarme VARCHAR(250) DEFAULT NULL, instructions VARCHAR(250) DEFAULT NULL, perform_action VARCHAR(250) DEFAULT NULL, acquittement VARCHAR(250) DEFAULT NULL, complements_informations VARCHAR(250) DEFAULT NULL, ref_service VARCHAR(250) DEFAULT NULL, objet VARCHAR(250) DEFAULT NULL, support VARCHAR(250) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE process (id VARCHAR(250) NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, ref_composant VARCHAR(250) DEFAULT NULL, process VARCHAR(250) DEFAULT NULL, criticite VARCHAR(250) DEFAULT NULL, message_alarme VARCHAR(250) DEFAULT NULL, intervalle_de_polling VARCHAR(250) DEFAULT NULL, objet VARCHAR(250) DEFAULT NULL, nom_template VARCHAR(250) DEFAULT NULL, monitored_by VARCHAR(250) DEFAULT NULL, support VARCHAR(250) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE requetessql (id VARCHAR(500) NOT NULL, ref VARCHAR(500) DEFAULT NULL, etat VARCHAR(500) DEFAULT NULL, ref_composant VARCHAR(500) DEFAULT NULL, rg_sg_si_cluster VARCHAR(500) DEFAULT NULL, requete_sql VARCHAR(500) DEFAULT NULL, username_db_name VARCHAR(500) DEFAULT NULL, resultat_attendu_de_la_requete VARCHAR(500) DEFAULT NULL, perform_action VARCHAR(500) DEFAULT NULL, criticite VARCHAR(500) DEFAULT NULL, message_alarme VARCHAR(500) DEFAULT NULL, instructions VARCHAR(500) DEFAULT NULL, intervalle_de_polling VARCHAR(500) DEFAULT NULL, ref_service VARCHAR(500) DEFAULT NULL, objet VARCHAR(500) DEFAULT NULL, monitored_by VARCHAR(500) DEFAULT NULL, nom_template VARCHAR(500) DEFAULT NULL, support VARCHAR(500) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE scripts (id VARCHAR(250) NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, ref_composant VARCHAR(250) DEFAULT NULL, rg_sg_si_cluster VARCHAR(250) DEFAULT NULL, script VARCHAR(250) DEFAULT NULL, code_erreur VARCHAR(250) DEFAULT NULL, criticite VARCHAR(250) DEFAULT NULL, description VARCHAR(250) DEFAULT NULL, instructions VARCHAR(250) DEFAULT NULL, monitored_by VARCHAR(250) DEFAULT NULL, ref_service VARCHAR(250) DEFAULT NULL, support VARCHAR(250) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE serveurs (id VARCHAR(250) NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, platforme VARCHAR(250) DEFAULT NULL, hostname VARCHAR(250) DEFAULT NULL, fqdn VARCHAR(250) DEFAULT NULL, type VARCHAR(250) DEFAULT NULL, modele VARCHAR(250) DEFAULT NULL, os VARCHAR(250) DEFAULT NULL, ver_tech_firmware VARCHAR(250) DEFAULT NULL, cluster VARCHAR(250) DEFAULT NULL, ip_source VARCHAR(250) DEFAULT NULL, description VARCHAR(250) DEFAULT NULL, socle_standard_omu VARCHAR(250) DEFAULT NULL, complements_informations VARCHAR(250) DEFAULT NULL, support VARCHAR(250) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE traps_snmp (id VARCHAR(250) NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, ref_composant VARCHAR(250) DEFAULT NULL, signification VARCHAR(250) DEFAULT NULL, version_snmp VARCHAR(250) DEFAULT NULL, oid VARCHAR(250) DEFAULT NULL, specific_trap VARCHAR(250) DEFAULT NULL, variable_binding VARCHAR(250) DEFAULT NULL, criticite VARCHAR(250) DEFAULT NULL, message_alarme VARCHAR(250) DEFAULT NULL, instructions VARCHAR(250) DEFAULT NULL, acquittement VARCHAR(250) DEFAULT NULL, mib_associe VARCHAR(250) DEFAULT NULL, objet VARCHAR(250) DEFAULT NULL, compelement_information VARCHAR(250) DEFAULT NULL, support VARCHAR(250) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE url (id VARCHAR(250) NOT NULL, ref VARCHAR(250) DEFAULT NULL, etat VARCHAR(250) DEFAULT NULL, ref_composant VARCHAR(250) DEFAULT NULL, rg_sg_si_cluster VARCHAR(250) DEFAULT NULL, url VARCHAR(250) DEFAULT NULL, perform_action VARCHAR(250) DEFAULT NULL, criticite VARCHAR(250) DEFAULT NULL, message_alarme VARCHAR(250) DEFAULT NULL, instructions VARCHAR(250) DEFAULT NULL, intervalle_de_polling VARCHAR(250) DEFAULT NULL, ref_service VARCHAR(250) DEFAULT NULL, objet VARCHAR(250) DEFAULT NULL, monitored_by VARCHAR(250) DEFAULT NULL, nom_template VARCHAR(250) DEFAULT NULL, support VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(180) NOT NULL, roles LONGTEXT NOT NULL COMMENT \'(DC2Type:json)\', password VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_8D93D649E7927C74 (email), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE cluster');
        $this->addSql('DROP TABLE log_files');
        $this->addSql('DROP TABLE log_files_patterns');
        $this->addSql('DROP TABLE process');
        $this->addSql('DROP TABLE requetessql');
        $this->addSql('DROP TABLE scripts');
        $this->addSql('DROP TABLE serveurs');
        $this->addSql('DROP TABLE traps_snmp');
        $this->addSql('DROP TABLE url');
        $this->addSql('DROP TABLE user');
        $this->addSql('DROP TABLE messenger_messages');
    }
}
