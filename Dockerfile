FROM eclipse-temurin:17-jdk

# Creează directorul de lucru
WORKDIR /app

# Copiază tot proiectul în container
COPY . .

# Compilează aplicația cu Maven instalat în container
RUN mvn clean package -DskipTests

# Expune portul (default Spring Boot)
EXPOSE 8080

# Rulează aplicația
CMD ["java", "-jar", "target/bookmanagement-0.0.1-SNAPSHOT.jar"]
