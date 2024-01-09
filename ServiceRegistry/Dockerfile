# Utiliser une image JDK 17
FROM amazoncorretto:17-alpine-jdk

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR de l'application dans le conteneur
COPY target/svc-registry.jar /app

# Commande pour exécuter l'application Spring Boot
CMD ["java", "-jar", "svc-registry.jar"]