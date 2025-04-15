FROM eclipse-temurin:17-jdk

# Install Chrome & ChromeDriver dependencies
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    unzip \
    gnupg \
    ca-certificates \
    fonts-noto \
    chromium-driver \
    chromium \
    --no-install-recommends && \
    rm -rf /var/lib/apt/lists/*

# Set Chrome env vars
ENV CHROME_BIN=/usr/bin/chromium
ENV CHROMEDRIVER_PATH=/usr/bin/chromedriver
ENV PATH=$PATH:/usr/bin

# Create and set working directory
WORKDIR /app

# Copy source
COPY . .

# Build application
RUN ./gradlew build --no-daemon

# Run application
CMD ["java", "-jar", "build/libs/Jacquline-Van-Gogh-Parser.jar"]
