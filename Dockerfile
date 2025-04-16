FROM eclipse-temurin:17-jdk

# Install dependencies needed for Selenium (e.g., Chrome and ChromeDriver)
RUN apt-get update && apt-get install -y \
    chromium \
    chromium-driver \
    bash \
    curl \
    --no-install-recommends && \
    rm -rf /var/lib/apt/lists/*


ENV CHROME_BIN=/usr/bin/chromium-browser
ENV CHROMEDRIVER=/usr/bin/chromedriver

# Set working directory
WORKDIR /app
COPY . .

# Build the application
RUN ./gradlew build --no-daemon

# Run the application
CMD ["java", "-jar", "build/libs/ van-gough-0.0.1-SNAPSHOT.jar"]
