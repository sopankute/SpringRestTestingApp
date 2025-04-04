# Step 1: Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file to the container
# (Make sure to build the project first: mvn clean package)
COPY target/SpringRestTestingApp-0.0.1-SNAPSHOT.jar  /var/lib/jenkins/workspace/ashokIt-Job-1/target/SpringRestTestingApp-0.0.1-SNAPSHOT.jar

# Step 4: Expose port (default Spring Boot port)
EXPOSE 8282

# Step 5: Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
