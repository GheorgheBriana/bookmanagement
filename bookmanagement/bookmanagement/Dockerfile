# Imagine de bază cu Maven + JDK 17 deja instalate
FROM maven:3.9.6-eclipse-temurin-17

# Setăm directorul de lucru
WORKDIR /app

# Copiem toate fișierele în container
COPY . .

# Compilăm aplicația Spring Boot
RUN mvn clean package -DskipTests

# Expunem portul pe care rulează aplicația (poate fi 8080)
EXPOSE 8080

# Rulăm aplicația Spring Boot
CMD ["java", "-jar", "target/bookmanagement-0.0.1-SNAPSHOT.jar"]
