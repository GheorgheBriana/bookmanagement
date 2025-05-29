# Folosește o imagine oficială Java 17
FROM eclipse-temurin:17-jdk

# Setează directorul de lucru în container
WORKDIR /app

# Copiază tot codul în container
COPY . .

# Compilează aplicația (cu mvnw dacă îl ai)
RUN ./mvnw clean package -DskipTests

# Expune portul pe care rulează Spring Boot
EXPOSE 8080

# Rulează aplicația
CMD ["java", "-jar", "target/bookmanagement-0.0.1-SNAPSHOT.jar"]
