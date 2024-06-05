<strong>Repository:</strong> devops-23-24-JPE-PSM-1231831
 <p></p>

# Class Assignment 4 - Part 2

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
In the DevOps class, we were challenged to work with Docker and leverage it to establish a containerized environment capable of executing our customized iteration of the Gradle version of the Spring Basic Tutorial application from Class Assignment 2. This task not only reinforces our understanding of Docker but also expands our skill set by incorporating Docker and Docker Compose for efficient container creation and management.
<p></p>
<p></p>

## Tutorial:
<p></p>


### 1. Creation of Dockerfiles

<p></p>

### 1.1. Web Dockerfile:
<p></p>

```bash
FROM ubuntu:22.04
LABEL authors="inesc"

RUN apt-get update -y

RUN apt-get install -y openjdk-17-jdk
RUN apt-get install wget -y

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app/

RUN wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar

EXPOSE 8082
EXPOSE 9092

CMD java -cp ./h2-2.2.224.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists
```

<p></p>
<p></p>

#### Explanation
<p></p>

<b>FROM ubuntu:22.04:</b>  This specifies the base image to use for the Docker container, in this case, Ubuntu version 22.04. <p></p>


<b>LABEL authors="inesc":</b>  This adds metadata to the Docker image, specifying the author(s) of the image. <p></p>


<b>RUN apt-get update -y:</b>  This updates the package lists for upgrades and installs. <p></p>


<b>RUN apt-get install -y openjdk-17-jdk:</b>  This installs the OpenJDK 17 JDK package, which includes the Java Development Kit necessary for running Java applications. <p></p>


<b>RUN apt-get install wget -y:</b>  This installs the wget package, a command-line tool for downloading files. <p></p>


<b>RUN mkdir -p /usr/src/app:</b>  This creates a directory named /usr/src/app in the Docker container where the application files will be stored. <p></p>


<b>WORKDIR /usr/src/app/:</b>  This sets the working directory inside the Docker container to /usr/src/app/, so subsequent commands will be executed in this directory. <p></p>


<b>RUN wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar:</b>  This downloads the H2 database JAR file from Maven Central repository and saves it to the current working directory (/usr/src/app/). <p></p>


<b>EXPOSE 8082 and EXPOSE 9092:</b>  These instructions expose ports 8082 and 9092 from the Docker container. However, these ports will not be accessible from outside the container unless they are explicitly mapped to host ports when running the container. <p></p>


<b>CMD java -cp ./h2-2.2.224.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists:</b> This command is executed when the Docker container starts. It starts the H2 database server with options to enable the web and TCP connections, allowing connections from other hosts, and creating the database if it doesn't exist. <p></p>


<p></p>

### 1.2. H2 Database Dockerfile:

 <p></p>

```bash
FROM ubuntu:22.04
LABEL authors="inesc"

RUN apt-get update -y

RUN apt-get install -y openjdk-17-jdk
RUN apt-get install wget -y

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app/

RUN wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar

EXPOSE 8082
EXPOSE 9092

CMD java -cp ./h2-2.2.224.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists
```

<p></p>
<p></p>

#### Explanation
<p></p>

<b>FROM ubuntu:22.04:</b> This sets the base image as Ubuntu version 22.04. 
<p></p>
<b>LABEL authors="inesc":</b>  Adds a label to the image with the author's name.  
<p></p>
<b>RUN apt-get update -y:</b> Updates the package lists for upgrades and installs. 
<p></p>
<b>RUN apt-get install -y openjdk-17-jdk:</b> Installs OpenJDK 17 JDK. 
<p></p>
<b>RUN apt-get install wget -y:</b> Installs wget, a command-line tool for downloading files. 
<p></p>
<b>RUN mkdir -p /usr/src/app:</b> Creates a directory at /usr/src/app. 
<p></p>
<b>WORKDIR /usr/src/app/:</b> Sets the working directory to /usr/src/app/. 
<p></p>
<b>RUN wget https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar:</b> Downloads the H2 database JAR file. 
<p></p>
<b>EXPOSE 8082:</b> Exposes port 8082 for H2 database web console access. 
<p></p>
<b>EXPOSE 9092:</b> Exposes port 9092 for H2 database TCP connections. 
<p></p>
<b>CMD java -cp ./h2-2.2.224.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists:</b> Specifies the command to run when the container starts. It starts the H2 database server with options to enable web and TCP connections, allowing connections from other hosts, and creating the database if it doesn't exist.
<p></p>
<p></p>

### 1.3. Docker-Compose.yml file:
<p></p>
<p></p>

```bash
version: '3.8'
services:
  web:
    build: web
    ports:
      - "8080:8080"
    networks:
      default:
        ipv4_address: 192.168.56.10
    depends_on:
      - "db"
  db:
    build: db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - ./data:/usr/src/data-backup
    networks:
      default:
        ipv4_address: 192.168.56.11
networks:
  default:
    ipam:
      driver: default
      config:
        - subnet: 192.168.56.0/24

```

<p></p>
<p></p>

#### Explanation
<p></p>

This is a Docker Compose file written in version 3.8 syntax. It defines two services, web and db, along with their configurations: <p></p>

<b>Service web: </b>
<p></p>
<b>build: web:</b> Specifies that the Dockerfile for building this service is located in the web directory. <p></p>
<b>ports: - "8080:8080":</b> Maps port 8080 on the host machine to port 8080 in the container. <p></p>
<b>networks: default: ipv4_address: 192.168.56.10:</b> Assigns a static IPv4 address 192.168.56.10 to this service within the default network. <p></p>
<b>depends_on: - "db":</b> Specifies that this service depends on the db service, meaning that Docker Compose will start the db service before starting the web service. <p></p>

<p></p>
<b>Service db: </b>
<p></p> 
<b>build: db:</b> Specifies that the Dockerfile for building this service is located in the db directory. <p></p>
<b>ports: - "8082:8082" - "9092:9092":</b> Maps ports 8082 and 9092 on the host machine to ports 8082 and 9092 in the container. <p></p>
<b>volumes: - ./data:/usr/src/data-backup:</b> Mounts the ./data directory from the host machine to /usr/src/data-backup directory in the container. <p></p>
<b>networks: default: ipv4_address: 192.168.56.11:</b> Assigns a static IPv4 address 192.168.56.11 to this service within the default network. <p></p>

<p></p>
<b>Networks: </b>
<p></p> 
<b>default:</b> Defines a network named default. <p></p> 
<b>ipam: driver: default:</b> Specifies the default IP address management driver for this network. <p></p> 
<b>config: - subnet: 192.168.56.0/24:</b> Configures the subnet 192.168.56.0/24 for this network, allowing containers to communicate with each other within this subnet. <p></p> 

<p></p> 
<p></p> 

### 1.4. Building/Running of Containers
<p></p>
To build and run the Docker containers with the docker files created, I used the following commands: <p></p>


```bash
* docker compose build
* docker compose up

```

<p></p>
The <b> docker compose build </b> builds the Docker images for the services defined in the "docker-compose.yml file". It reads the instructions from the Dockerfiles specified for each service and creates the corresponding Docker images. <p></p>
The <b> docker compose up </b> starts the containers defined in the docker-compose.yml file. It creates and starts containers for all the services defined in the file, using the built images. If the images are not built yet, it will build them before starting the containers. This command also orchestrates the networking between the containers, volumes, and any other configurations specified in the docker-compose.yml file.

<p></p>
<p></p>

## 2. Publish the images in Docker Hub
<p></p>

Afterwards, I tagged an imagem and then after login in, pushed it to the repository: <p></p>

```bash
* docker tag dockerweb inesc/devops:web
* docker tag dockerdb inesc/devops:db
* docker login
* docker push inesc/devops:web
* docker push inesc/devops:db

```

<p></p>
<p></p>

## 3. Volumes: Copying database file
<p></p>
Firstly, I used Docker exec to access the database container: <p></p>

```bash
* docker-compose exec db bash

```

<p></p>
Then, I copied the database file to the volume type: <p></p>

```bash
* cp /usr/src/app/h2-2.2.224.jar /usr/src/data-backup exit

```

<p></p>
Once the cp command completes its execution, with the <b>"exit"</b> command it exits the session, terminating the current shell process.

<p></p>
<p></p>

## 4. Commiting the tag
<p></p>

```bash
* git tag ca4-part1
* git push origin ca4-part1

```

<p></p>
<p></p>

## Kubernetes:
<p></p>
While Docker provides containerization features, Kubernetes offers more advanced capabilities for orchestration, networking, resource management, and high availability. Kubernetes can effectively address the goals presented in the assignment by providing a robust platform for deploying, managing, and scaling containerized applications.
<p></p>
For example, in terms of: <p></p>
<b>Container Runtime: </b> Kubernetes can work with various container runtimes, including Docker, containerd, and others. <p></p>
<b>Orchestration and Management: </b> It excels in container orchestration and management, offering advanced features like auto-scaling, rolling updates, service discovery, and load balancing. <p></p>
<b>Networking</b>: Offers advanced networking capabilities, including service discovery, load balancing, and network policies, facilitating communication between pods and services within a cluster. <p></p>
<b>Resource Management:</b> Kubernetes provides cluster-level resource management, allowing users to define resource requests and limits for pods, ensuring efficient resource utilization across the cluster. <p></p> 
<b>High Availability:</b> Is designed for high availability, with built-in features like pod replication, automatic node failover, and self-healing mechanisms. <p></p>

<p></p>

## Heroku:
<p></p>
Heroku is a PaaS that simplifies application deployment and scaling. Unlike Docker and Kubernetes, it abstracts away containerization complexities. While it may lack the control of Docker/Kubernetes, Heroku offers simplicity and ease of use for rapid application deployment.

<p></p>

As it was done in this assigment, to use Heroku as an alternative I could've used the following commands:
<p></p>

```bash
* choco install heroku-cli - to install heroku
* heroku --version - to verify the version installed
* heroku login - It opens a browser window where you can enter my Heroku credentials.
* heroku create CA4Heroku - Creates a new Heroku application with the specified name (CA4Heroku).
* heroku container:login - logs you into the Heroku Container Registry, which allows you to push Docker container images to Heroku.
* heroku container:push web --app CA4Heroku - builds and pushes a Docker container image to the Heroku Container Registry for the specified Heroku application (CA4Heroku). The web argument specifies the process type of the application.
* heroku container:release web db --app CA4Heroku - releases the previously pushed Docker container image to the specified Heroku application (CA4Heroku). It specifies the process type (web) that the container image should run as. If multiple process types are specified, they are separated by spaces (db in this case).

```

<p></p>
<p></p>
