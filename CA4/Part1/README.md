<strong>Repository:</strong> devops-23-24-JPE-PSM-1231831
 <p></p>

# Class Assignment 4 - Part 1

<p></p>

<strong>Student: </strong>
Inês Guedes
 <p></p>

<strong>Number:</strong>
1231831
<p></p>

<p>
</p>
<p></p>

## Introduction
<p></p>
In the DevOps class we were challenged to work with Docker, by creating docker images and running containers using the chat application from the Class assignment 2.
Considering that Docker is a platform that simplifies the process of packaging, distributing, and running applications by using containers and Docker images, this is important since it provides a consistent environment for development and deployment, making it easier to build and maintain software across different platforms.

<p></p>
<p></p>

## Tutorial - Version 1 (Chat server inside the Dockerfile): 
<p></p>
<p></p>
* 1 - I installed Docker Desktop and created an account.
* 2 - Then, I created a repository on Docker Hub and named it devops.
* 3 - I started with the assignment, by making sure that the Docker Desktop was running in the background.

### 1. Creation of a Dockerfile

<p></p>
To begin with the assignment, I firstly created a Dockerfile in order to package and execute the chat server in the CA2_Part1 container:
<p></p>

```bash
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
```

<p></p>
<p></p>

#### Explanation
<p></p>

<b>Use an official Ubuntu base image:</b> This line specifies the base image for your Docker container. In this case, it's using Ubuntu 20.04 as the base. <p></p>

<b>LABEL author="inesc":</b> This adds metadata to the image. Labels are key-value pairs used to annotate containers or images with identifying information. <p></p>

<b>Install Java (OpenJDK 17) and Git:</b> This section uses apt-get to install OpenJDK 17 (headless version, meaning without GUI support) and Git. The -y flag automatically answers "yes" to prompts during the installation process. <p></p>

<b>Set JAVA_HOME environment variable:</b> This sets the environment variable JAVA_HOME to point to the directory where Java is installed. <p></p>

<b>Make the directory for the application:</b> This creates a directory named /opt/chat-app where the application will reside. <p></p>

<b>Clone the application from the repository:</b> This clones a Git repository containing the application code into the /opt/chat-app directory. <p></p>

<b>Build the application:</b> This step navigates into the /opt/chat-app/ directory and runs the gradlew script with the build task. This will compile and build the application. <p></p>

<b>Expose the application port:</b> This exposes port 59001 on the container. This doesn't publish the port to the host machine, but it indicates that the container will listen on this port at runtime. <p></p>

<b>Run the application:</b> This is the command that will be executed when the container starts. It runs the Java application, specifying the classpath (-cp), the location of the JAR file, the main class (basic_demo.ChatServerApp), and the port (59001) as arguments. <p></p>

<p></p>

Overall, this Dockerfile sets up an Ubuntu-based environment with Java and Git installed, clones a Git repository containing a Java application, builds the application, and then runs it, exposing port 59001 for communication.

<p></p>
<p></p>

### 2. Building the Docker Image

<p></p>
To build the docker image I used the following commands:
<p></p>

```bash
docker build -f CA4/Part1/CA2.Part1/gradle_basic_demo/Dockerfile -t inesc/chat-server:version1 .

```
<p></p>
This command is used to build a Docker image based on the instructions provided in a Dockerfile. <p></p>
To push the image into the repository, I first needed to login via terminal and then push the image:
<p></p>

```bash
* docker login
* docker push inesc/chat-server:version1

```

<p></p>
<p></p>

#### Explanation
<p></p>
<b>`docker build`:</b> This is the command to build a Docker image.<p></p>

<b>`-f CA4/Part1/CA2.Part1/gradle_basic_demo/Dockerfile`:</b> This flag specifies the path to the Dockerfile that contains the instructions for building the image. This signifies that the Dockerfile is located at CA4/Part1/CA2.Part1/gradle_basic_demo/Dockerfile.<p></p>

<b>`-t inesc/chat-server:version1`:</b> This flag tags the Docker image with a name and optionally a tag. Threfore, the image will be tagged as inesc/chat-server with the tag version1.<p></p>

<b>`.`:</b> This period denotes the build context, which is the path from where the build process will access files referenced in the Dockerfile. In this case, it's the current directory.
<p></p>
<p></p>

So, this command builds a Docker image named inesc/chat-server with the tag version1, using the Dockerfile located at CA4/Part1/CA2.Part1/gradle_basic_demo/Dockerfile.
<p></p>
<p></p>

### 3. Running the Docker Container

<p></p>
After verifying that the docker image with the name inesc/chat-server:version 1 was built on the container of the Docker Hub devops repository, I decided to run the container:
<p></p>

```bash
 docker run -p 59001:59001 inesc/chat-server:version1

```

<p></p>
Then, to execute the chat client to connect to the server I used the following command:
<p></p>


```bash
./gradlew runClient

```

<p></p>
<p></p>

### 4. Other Commands

<p></p>
In terms of checking if the containers are running, I used the following command:
<p></p>

```bash
docker ps

```
<p></p>
I also could've used this command to list all containers, including the stopped ones. This command is useful to search for my container id:
<p></p>

```bash
docker ps a-

```

<p></p>
If I wanted to stop the container I should run this command:
<p></p>

```bash
docker stop <container_id> - the container id that is running represented on my Docker Hub repository.

```

<p></p>
To remove the container or the image created, I could've also used the following commands:
<p></p>

```bash
docker rm <container_id> - removes the container
docker rmi inesc/chat-server:version1 - removes the image

```

<p></p>
<p></p>

## Tutorial - Version 2 (Chat server on the Host and copying the Jar file into the Dockerfile): 
<p></p>
<p></p>
For this version I created a new Dockerfile named Dockerfilev2.
For this version to work it is important that the app is created in my local machine and the .jar file copied to the container in the Dockerfile. <p></p>
So, I wrote the following commands on the new file:
<p></p>

```bash

# Use an official Ubuntu base image
FROM ubuntu:20.04
LABEL author="inesc"

# Install Java (OpenJDK 17 in this case) and Git
RUN apt-get update && \
    apt-get install -y openjdk-17-jre-headless git && \
    apt-get clean

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin

# Now we will make the directory for the application
WORKDIR /opt/chat-app

# Copy the built jar file from the host into the Docker image
COPY ./build/libs/basic_demo-0.1.0.jar /opt/chat-app/basic_demo-0.1.0.jar

# Expose the application port
EXPOSE 59001

# Run the application
CMD ["java", "-cp", "/opt/chat-app/basic_demo-0.1.0.jar", "basic_demo.ChatServerApp", "59001"]

```

<p></p>
<p></p>

## Commiting the tag
<p></p>

```bash
* git tag ca4-part1
* git push origin ca4-part1

```

<p></p>
<p></p>



