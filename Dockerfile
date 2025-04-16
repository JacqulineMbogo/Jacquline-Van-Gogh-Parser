FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper
COPY gradlew .
COPY gradle ./gradle

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Copy only the build files initially to leverage Docker layer caching
COPY build.gradle settings.gradle ./

# Resolve dependencies and download them (this can take time)
RUN ./gradlew dependencies --stacktrace

# Copy the application source code
COPY src ./src

# Build the application
RUN ./gradlew bootJar

# Specify the application to run
ENTRYPOINT ["java", "-jar", "/app/build/libs/van-gough-0.0.1-SNAPSHOT.jar"]

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080