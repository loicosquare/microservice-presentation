# Utiliser une image JDK 17
FROM amazoncorretto:17-alpine-jdk

#On veut copier ler JAR de l'application dans le conteneur et le démarrer lorsqu'on lance le conteneur.
ARG JAR_FILE=target/*.jar

#on copie le JAR dans le conteneur et on le renomme en svc-apigateway.jar (on peut le renommer comme on veut);
COPY ${JAR_FILE} svc-apigateway.jar

#ENTRYPOINT permet de définir la commande qui sera exécutée lorsqu'on lance le conteneur, on utilise ENTRYPOINT pour exécuter l'application Spring Boot.
ENTRYPOINT ["java","-jar","/svc-apigateway.jar"]

#On expose le port 8084 pour que le conteneur soit accessible depuis l'extérieur du conteneur;
EXPOSE 8084

#On construit l'image Docker avec la commande suivante:
    #pour construire l'image Docker avec un tag, on utilise la commande suivante:
        #docker build -t svc-apigateway:1.0.0 . (mettre <hub>/svc-apigateway:1.0.0) si on veut pousser l'image sur Docker Hub.

    #docker build -t svc-apigateway .
        #svc-apigateway est le nom de l'image Docker. On peut le renommer comme on veut. Il faut juste que ce soit un nom unique.

    # . signifie que le Dockerfile se trouve dans le répertoire courant. Si le Dockerfile se trouve dans un autre répertoire,
        #il faut préciser le chemin du Dockerfile.

    #On peut vérifier que l'image est bien construite avec la commande suivante:
        #docker images

    #On peut supprimer l'image avec la commande suivante:
        #docker rmi <id de l'image>

#On lance le conteneur avec la commande suivante:
    #docker run -d -p 8084:8084 svc-apigateway --name svc-apigateway <id_image> (On voit l'id de l'image avec docker images).

#On peut vérifier que le conteneur est bien lancé avec la commande suivante:
    #docker ps

#On peut vérifier que l'application est bien lancée avec la commande suivante:
    #docker logs <id du conteneur>

# Une fois que l'application est lancée on aura l'érreur suivante : Connect to http://localhost:8761 [localhost/127.0.0.1] failed: Connection refused
    # Pour résoudre ce problème on va utiliser le host interne de Docker lorsqu'on va lancer le conteneur,
        #et passer l'adresse du serveur Eureka comme variable d'environnement.

    # Pour cela on va utiliser la commande suivante:
        # docker run -d -p8084:8084 -e EUREKA_SERVER_ADDRESS=http://host.docker.internal:8761/eureka --name svc-apigateway <image-id>