<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230807124202 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE cluster (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, nom_du_ressource_group_package_service_guard VARCHAR(250) NOT NULL, adresse_ip VARCHAR(250) NOT NULL, liste_des_serveurs_concernés VARCHAR(250) NOT NULL, logiciel_cluster VARCHAR(250) NOT NULL, version VARCHAR(250) NOT NULL, mode VARCHAR(250) NOT NULL, serveur_actif VARCHAR(250) NOT NULL, complements_informations VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE log_files (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, ref_composant VARCHAR(250) NOT NULL, rg_sg_si_cluster VARCHAR(250) NOT NULL, logfile VARCHAR(250) NOT NULL, localisation VARCHAR(250) NOT NULL, description VARCHAR(250) NOT NULL, format_logfile VARCHAR(250) NOT NULL, separateur VARCHAR(250) NOT NULL, intervalle_de_polling VARCHAR(250) NOT NULL, monitored_by VARCHAR(250) NOT NULL, fourni_en_annexe VARCHAR(250) NOT NULL, ref_service VARCHAR(250) NOT NULL, nom_template VARCHAR(250) NOT NULL, log_conditions VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE log_files_patterns (id VARCHAR(250) NOT NULL, n_ref INT NOT NULL, ref INT NOT NULL, etat VARCHAR(250) NOT NULL, signification VARCHAR(250) NOT NULL, identification VARCHAR(250) NOT NULL, criticite VARCHAR(250) NOT NULL, message_alarme VARCHAR(250) NOT NULL, instructions VARCHAR(250) NOT NULL, perform_action VARCHAR(250) NOT NULL, acquittement VARCHAR(250) NOT NULL, complements_informations VARCHAR(250) NOT NULL, ref_service VARCHAR(250) NOT NULL, objet VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE process (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, ref_composant VARCHAR(250) NOT NULL, process VARCHAR(250) NOT NULL, criticite VARCHAR(250) NOT NULL, message_alarme VARCHAR(250) NOT NULL, intervalle_de_polling VARCHAR(250) NOT NULL, objet VARCHAR(250) NOT NULL, nom_template VARCHAR(250) NOT NULL, monitored_by VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE requetessql (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, ref_composant VARCHAR(250) NOT NULL, rg_sg_si_cluster VARCHAR(250) NOT NULL, requete_sql VARCHAR(250) NOT NULL, username_db_name VARCHAR(250) NOT NULL, resultat_attendu_de_la_requete VARCHAR(250) NOT NULL, perform_action VARCHAR(250) NOT NULL, criticite VARCHAR(250) NOT NULL, message_alarme VARCHAR(250) NOT NULL, instructions VARCHAR(250) NOT NULL, intervalle_de_polling VARCHAR(250) NOT NULL, ref_service VARCHAR(250) NOT NULL, objet VARCHAR(250) NOT NULL, monitored_by VARCHAR(250) NOT NULL, nom_template VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE scripts (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, ref_composant VARCHAR(250) NOT NULL, rg_sg_si_cluster VARCHAR(250) NOT NULL, script VARCHAR(250) NOT NULL, code_erreur VARCHAR(250) NOT NULL, criticite VARCHAR(250) NOT NULL, description VARCHAR(250) NOT NULL, instructions VARCHAR(250) NOT NULL, monitored_by VARCHAR(250) NOT NULL, ref_service VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE serveurs (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, platforme VARCHAR(250) NOT NULL, hostname VARCHAR(250) NOT NULL, fqdn VARCHAR(250) NOT NULL, type VARCHAR(250) NOT NULL, modele VARCHAR(250) NOT NULL, os VARCHAR(250) NOT NULL, ver_tech_firmware VARCHAR(250) NOT NULL, cluster VARCHAR(250) NOT NULL, ip_source VARCHAR(250) NOT NULL, description VARCHAR(250) NOT NULL, socle_standard_omu VARCHAR(250) NOT NULL, complements_informations VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE traps_snmp (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, ref_composant VARCHAR(250) NOT NULL, signification VARCHAR(250) NOT NULL, version_snmp VARCHAR(250) NOT NULL, oid VARCHAR(250) NOT NULL, specific_trap VARCHAR(250) NOT NULL, variable_binding VARCHAR(250) NOT NULL, criticite VARCHAR(250) NOT NULL, message_alarme VARCHAR(250) NOT NULL, instructions VARCHAR(250) NOT NULL, acquittement VARCHAR(250) NOT NULL, mib_associe VARCHAR(250) NOT NULL, objet VARCHAR(250) NOT NULL, compelement_information VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE url (id VARCHAR(250) NOT NULL, ref VARCHAR(250) NOT NULL, etat VARCHAR(250) NOT NULL, ref_composant VARCHAR(250) NOT NULL, rg_sg_si_cluster VARCHAR(250) NOT NULL, url VARCHAR(250) NOT NULL, perform_action VARCHAR(250) NOT NULL, criticite VARCHAR(250) NOT NULL, message_alarme VARCHAR(250) NOT NULL, instructions VARCHAR(250) NOT NULL, intervalle_de_polling VARCHAR(250) NOT NULL, ref_service VARCHAR(250) NOT NULL, objet VARCHAR(250) NOT NULL, monitored_by VARCHAR(250) NOT NULL, nom_template VARCHAR(250) NOT NULL, support VARCHAR(250) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
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
        $this->addSql('DROP TABLE messenger_messages');
    }
}
