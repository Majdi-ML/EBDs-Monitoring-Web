# Projet de Stage chez Ooredoo - Application Web de Gestion et Classification des EBDs

Ce projet de stage réalisé chez Ooredoo a pour objectif de développer une application web de gestion et de classification des EBDs (Evénements Basés sur les Données). Pendant ce stage, j'ai travaillé sur deux aspects clés du projet :

## 1. Partie Talend (Insertion des données Excel dans MySQL)

- Collecte des fichiers EBDs : Utilisation de Talend pour extraire les informations de monitoring de chaque plateforme de service à partir des fichiers Excel fournis.

- Normalisation des fichiers : Les fichiers Excel sont traités et normalisés à l'aide de Talend afin de les préparer à l'importation dans la base de données.

- Importation dans la base de données : Les données normalisées sont insérées dans la base de données MySQL (MariaDB) pour permettre leur exploitation ultérieure.

## 2. Partie Symfony (Application Web)

- Affichage des données EBDs : Développement d'une application web en utilisant Symfony pour afficher clairement le contenu des fichiers EBDs importés.

- Tableaux de bord dynamiques : Mise en place de tableaux de bord dynamiques permettant une visualisation et une analyse aisée des données par service, type de monitoring, intervalle de temps, etc.

- Requêtes de filtrage : Implémentation de mécanismes de filtrage pour que les utilisateurs puissent obtenir des informations spécifiques selon leurs besoins.

## Technologies Utilisées

- Talend : Pour l'extraction, la normalisation et l'insertion des données EBDs dans la base de données.
- Symfony : Pour le développement de l'application web et la gestion des données EBDs.
- MySQL (MariaDB) : Base de données utilisée pour le stockage des données normalisées.
- XAMPP : Serveur local utilisé pour le développement et le test de l'application web.

## Installation et configuration

1. Cloner ce dépôt Git sur votre machine locale :
   ```
   git clone https://github.com/votre_utilisateur/nom_du_projet.git
   ```

2. Configurer la base de données MySQL (MariaDB) :
   - Créez une base de données nommée "ebds_db".
   - Configurez les informations de connexion à la base de données dans le fichier de configuration Symfony.

3. Importer les données avec Talend :
   - Lancez Talend et ouvrez le projet.
   - Configurez les connexions pour les fichiers Excel sources et la base de données de destination (MySQL).
   - Exécutez le job pour insérer les données dans la base de données.

4. Démarrer l'application Symfony :
   ```
   cd nom_du_projet
   composer install
   php bin/console server:run
   ```

5. Accédez à l'application web via votre navigateur à l'adresse `http://localhost:8000` (ou autre port défini).
