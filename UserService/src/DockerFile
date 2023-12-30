# Utiliser une image JDK 17
FROM adoptopenjdk:17-jdk-hotspot

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR de l'application dans le conteneur
COPY target/your-application.jar /app

# Commande pour exécuter l'application Spring Boot
CMD ["java", "-jar", "your-application.jar"]
