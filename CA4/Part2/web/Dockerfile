
# Use the Tomcat 10 image with JDK 17 based on Temurin
FROM tomcat:10.0.20-jdk17-temurin

# Update package lists and install necessary dependencies
RUN apt-get update -y && \
    apt-get install sudo nano git nodejs npm -f -y && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

RUN git clone https://github.com/Departamento-de-Engenharia-Informatica/devops-23-24-JPE-1231831.git


# Navigate to the React and Spring Data REST project directory
WORKDIR /tmp/build/devops-23-24-JPE-1231831/CA2/Part2/react-and-spring-data-rest-basic
# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the Spring Boot application with Gradle and copy the WAR file to Tomcat's webapps directory
RUN ./gradlew clean build && \
    cp build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ && \
    rm -Rf /tmp/build/

# Expose port 8080
EXPOSE 8080

