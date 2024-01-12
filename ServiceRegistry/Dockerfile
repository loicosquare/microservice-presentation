# Utiliser une image JDK 17
FROM amazoncorretto:17-alpine-jdk

# Définir le répertoire de travail.
#WORKDIR /app

# Copier le JAR de l'application dans le conteneur.
#COPY  /app

# Commande pour exécuter l'application Spring Boot.
#CMD ["java", "-jar", "svc-registry.jar"]

#On veut copier ler JAR de l'application dans le conteneur ou dans l'image.
    #et le démarrer lorsqu'on lance le conteneur.

# ARG permet de définir une variable d'environnement qui sera utilisée dans le Dockerfile;
    #et qui sera remplacée par la valeur passée en paramètre lors de la construction de l'image Docker.

#ARG JAR_FILE=target/svc-registry.jar.

ARG JAR_FILE=target/*.jar

#on copie le JAR dans le conteneur et on le renomme en svc-registry.jar (on peut le renommer comme on veut);
COPY ${JAR_FILE} svc-registry.jar

#ENTRYPOINT permet de définir la commande qui sera exécutée lorsqu'on lance le conteneur,
    #on utilise ENTRYPOINT pour exécuter l'application Spring Boot.
ENTRYPOINT ["java","-jar","/svc-registry.jar"]

#On expose le port 8761 pour que le conteneur soit accessible depuis l'extérieur du conteneur;
EXPOSE 8761

#On construit l'image Docker avec la commande suivante:
    #pour construire l'image Docker avec un tag, on utilise la commande suivante:
        #docker build -t svc-registry:1.0.0 . (mettre <hub>/svc-registry:1.0.0) si on veut pousser l'image sur Docker Hub.

    #docker build -t svc-registry .
        #svc-registry est le nom de l'image Docker. On peut le renommer comme on veut. Il faut juste que ce soit un nom unique.

    # . signifie que le Dockerfile se trouve dans le répertoire courant. Si le Dockerfile se trouve dans un autre répertoire,
        #il faut préciser le chemin du Dockerfile.

    #On peut vérifier que l'image est bien construite avec la commande suivante:
        #docker images

    #On peut supprimer l'image avec la commande suivante:
        #docker rmi <id de l'image>

#On lance le conteneur avec la commande suivante:
    #docker run -d -p 8761:8761 svc-registry --name svc-registry <id_image> (On voit l'id de l'image avec docker images).

#On peut vérifier que le conteneur est bien lancé avec la commande suivante:
    #docker ps

#On peut vérifier que l'application est bien lancée avec la commande suivante:
    #docker logs <id du conteneur>