# Use a stable OpenJDK image with Debian base
FROM openjdk:17-slim

# Set working directory inside the container
WORKDIR /usr/src/app

# Install Maven, Chrome, ChromeDriver, and additional dependencies
RUN apt-get update && apt-get install -y \
    maven \
    chromium \
    chromium-driver \
    curl \
    unzip \
    fonts-liberation \
    libappindicator3-1 \
    libasound2 \
    libatk-bridge2.0-0 \
    libatk1.0-0 \
    libcups2 \
    libdbus-1-3 \
    libdrm2 \
    libgbm1 \
    libnspr4 \
    libnss3 \
    libxcomposite1 \
    libxdamage1 \
    libxrandr2 \
    xdg-utils \
    && rm -rf /var/lib/apt/lists/*

# Set Chrome path for Selenium
ENV CHROME_BIN=/usr/bin/chromium \
    CHROME_DRIVER=/usr/bin/chromedriver

# Copy your Maven project files into the container
COPY . .

# Ensure Maven dependencies are downloaded
RUN mvn clean install -DskipTests

# Command to run tests and generate Allure report
CMD ["sh", "-c", "mvn test -Denv=qa -Dbrowser=headless -DsuiteXml=suites/testng.xml && allure generate /usr/src/app/target/allure-results --clean && cp -r /usr/src/app/target/allure-report /usr/src/app/allure-report && python3 -m http.server 8080 --directory /usr/src/app/allure-report"]

# Volume for Allure results
#VOLUME ["/usr/src/app/target/allure-results"]

# Expose port for the Allure report
#EXPOSE 4040
