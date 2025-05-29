# Folosim imaginea oficială OpenJDK 17
FROM eclipse-temurin:17-jdk

# Setăm directorul de lucru
WORKDIR /app

# Instalăm Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

# Copiem codul sursă
COPY . .

# Compilăm aplicația Spring Boot
RUN mvn clean package -DskipTests

# Expunem portul pe care rulează Spring Boot
EXPOSE 8080

# Rulăm aplicația
CMD ["java", "-jar", "target/bookmanagement-0.0.1-SNAPSHOT.jar"]
