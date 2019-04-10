## Master 2 DL - PCR - Introduction à RMI

### Préliminaire

1. Mettez dans vos favoris le lien vers la [documentation de RMI](https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/).
2. Récupérez le projet "m2-pcr-rmi-etu" et importez le comme projet Maven dans IntelliJ.


### Partie 1 - Hello World (travail individuel)

Oracle met à disposition un [guide de démarrage avec RMI](https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/hello/hello-world.html).
Étudiez et implantez dans votre projet l'exemple proposé par ce guide de démarrage.
Vérifiez en lançant les différents composants de l'architecture distribuée que tout fonctionne comme attendu.
En cas de problème au lancement du serveur, ce lien peut-être utile : http://docs.oracle.com/javase/7/docs/technotes/guides/rmi/enhancements-7.html

### Partie 2 - LightSlack (travail en binôme)

L'objectif de cette activité est le développement d'un serveur et d'un client de discussion (très) rudimentaire.

1. Implantez un serveur de discussion en mode synchrone exposant ses opérations clés via RMI.
2. Implantez un client RMI de votre serveur de discussion en utilisant les librairies d'interface de votre choix (JavaFX, Swing, ligne de commande).
 
### Aide pour lancer RMI

Lancer le serveur RMI:
```
rmiregistry -J-Djava.rmi.server.codebase=file:/{Project-Path}/m2-pcr-rmi-etu/target/classes/
```

Lancer un main:
Ajouter dans la configuration d'Intellij, dans VM Options
```
-Djava.rmi.server.codebase=file:/{Project-Path}/m2-pcr-rmi-etu/target/classes/
```
