# Use an official Ubuntu base image
FROM ubuntu:20.04
LABEL author="inesc"

# Install Java (OpenJDK 17 in this case)
RUN apt-get update && \
    apt-get install -y openjdk-17-jre-headless  && \
    apt-get install -y git && \
    apt-get clean

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin

# Now we will make the directory for the application
RUN mkdir /opt/chat-app

# Clone the application from the repository
RUN git clone https://bitbucket.org/pssmatos/gradle_basic_demo.git /opt/chat-app

# Build the application
RUN cd /opt/chat-app/ && ./gradlew build

# Expose the application port
EXPOSE 59001

# Run the application
CMD ["java", "-cp", "/opt/chat-app/build/libs/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]