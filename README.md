# Projet BDA 2017 - AirBnB New Customer Prediction

## Imorter le Projet
1. Télecharger le code source:
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

## Planning

### Semaine 1:
Exploiration de données (semaine 1)

  Exploration des données commencées en Python. Continuer profiling avec Python et puis selon l'interpretation, la data prerocessing et modeling vont être traités en Scala ? 
  Questions : 
    Possibilité de faire cela. 
    
Librairie à utiliser :
  Saddle with Scala ?
  Other recommendations ? 


### Semaine 2:
Modeling  (semaine 2)
1. Feactures selections

On a des features nan, sans sens , incorrects. ils faut les laisser tonber en entier ? garder nan comme catégorie du feature ce q ui    peut biaiser le résultat.
      => moins de données
       =>plus de données moins pertinentes
       =>laisser tomber le feature 

2. Model application 
  - Decision Trees
      
      plusieurs models et tester : randomforest
  
  - SVM
  
### Semaine 3:
Testing, personnalizing and enhancing of algorithms (semaine 3 )

### Semaine 4:
Wrap up and Presentation  (semaine 4 )
