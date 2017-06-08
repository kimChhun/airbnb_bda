# Projet BDA 2017 - AirBnB New Customer Prediction
## Context du projet
Dans le cadre de ce projet, nous allons relever le défi du projet "airbnb" proposé par kaggele. Nous avons à disposition une liste d'utilisateurs ainsi que leurs données démographiques, les enregistrements des session Web et certaines statistiques récapitulatives. 

L'objectif du projet est d'entrainer des modèles de Machine Learning sur les données disponibles afin de prédire quel pays sera la première destination de réservation d'un nouvel utilisateur.

Pour ce faire nous utiliserons le framework SPARK.

## Données
### Lien 
https://www.kaggle.com/c/airbnb-recruiting-new-user-bookings
### Variable cible
La variable cible est le pays de destination. Cette variable peut avoir 12 résultats possibles: 'US', 'FR', 'CA', 'GB', 'ES', 'IT', 'PT', 'NL', 'DE', 'AU' 'NDF' (aucune destination trouvée) et 'autre'.

### Variables explicatives
Nous avons à disposition les différentes variables suivantes pour constituer la base d'apprentissage. 
- Id: id utilisateur 
- Date_account_created: la date de création du compte 
- Date_first_booking: date de la première réservation 
- gendre d'utilisateur 
- âge d'utilisateur 
- Signup_method 
- Signup_flow: la page à laquelle un utilisateur est venu s'inscrire 
- Langue: préférence linguistique 
- Affiliate_channel: canal de paiement 
- Affiliate_provider: exemple Google, craigslist, autres 
- First_affiliate_tracked 
- Signup_app 
- First_device_type 
- First_browser

## Techniques et algorithmes utilisés
Dans le cadre de ce projet, nous avons développé en Scala les traitments pour nettoyer et transformer les données. La patie d'entrainer des modèles est réalisée avec la librarie MlLib de Scala. Plusieurs algorithmes sont testés tels que : Decision Tree, Regression Logistic, Random Forest, SVM, Multilayer Perceptron, etc. Pour évaluer nos modèles, nous sommes basé sur le criètre de "Accuracy".

## Planning
### Semaine 1:
Exploration et interprétation des données
- Exploration des données commencées en Python. Continuer profiling avec Python et puis selon l'interpretation, le data prerocessing et modeling vont être traités en Scala ?

### Semaine 2:
Modeling
1. Traitement des données et selection des Feactures
- Gestion des données manquantes (ex: âge = null) et aberrante (ex: âge =2014)
- Transformation des données (de discrètes en continues)
2. Model application
- Decision Tree, Regression Logistic, Random Forest, SVM, Multilayer Perceptron

### Semaine 3:
Test et évaluation des diverses modèles
- tester les différents hyper-paramètres et les différents algorithms pour trouver la configuration la plus optimisisé

### Semaine 4:
Wrap up et préparation de la présentation

## Importer le Projet
1. Téléchargé le code source:
1. `git clone https://github.com/kimChhun/airbnb_bda.git`

#### Eclipse (Scala IDE)
1. `cd airbnb_bda`
2. `sbt eclipse`
3. Ouvrir Scala IDE et importer en tant que "Existing Project"
#### IntelliJ
1. Installer le plugin Sbt/Scala pour Intellij
2. Importer le projet (projet SBT)

#### Remarques
Evitez d'ajouter des fichiers relatifs à l'IDE sur GitHub.<br />
Verfifiez les fichier ajoutés avant de faire votre commit et push en utilisant `git status`, et `git reset HEAD <file>` pour ne pas inclure les fichiers non désirés dans le commit.<br />
Pensez à ajouter tel fichiers au fichier `.gitignore`
