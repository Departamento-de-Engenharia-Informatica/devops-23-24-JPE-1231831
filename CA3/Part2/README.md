


<strong>Repository:</strong> devops-23-24-JPE-PSM-1231831
 <p></p>

# Class Assignment 3 - Part 2

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
In the DevOps class for the Class Assignment 3 (CA3), we will emphasize the usage of VirtualBox and Vagrant to leverage virtualization.
A Vagrantfile file serves as the cornerstone of Vagrant configuration and it's a Ruby-based configuration file that defines the settings and configurations for the virtual machines managed by Vagrant.
The main goal of this assignment is to explore Vagrant and Vagrant/VirtualBox management  chosen by the provider to setup a virtual environment to execute the tutorial spring boot application, gradle ”basic” version (developed in CA2, Part2). I'm aiming to produce a Vagrantfile file that creates two virtual machines:

* Web: This virtual machine will act as a web server, cloning an application from GitHub and serving it. It will be used to run tomcat and the spring boot basic application.
* Db: This virtual machine wil function as an external H2 database server.
<p></p>
<p></p>

## Tutorial: 
<p></p>
<p></p>

After installing Ubuntu 20.04.3 LTS on VirtualBox, I must ensure that the repository is public, such as the Vagrant and VirtualBox installation was successful.
Then, I must launch the VirtualBox and execute the "vagrant init" within the designated CA3_Part2 project folder.
The next step involves modifying the provided Vagrantfile in which I start to edit it from the CA3_Part2 repository and later on update the Ubuntu version to 20.04.3 by replacing "ubuntu/bionic64" with "generic/ubuntu2004" three times in the script.

<p></p>
<p></p>

### 1. Initial Setup
<p></p>
Firstly, after installing Ubuntu 20.04.3 LTS on VirtualBox, I must ensure that the repository is public, such as the Vagrant and VirtualBox installation was successful.
Then, I must take some changes into account in my repository after accessing it: <p></p>

```bash
*	 cd /Users/inesc/Documents/Class/devops-23-24-JPE-1231831/CA2/Part2
```
<p></p>
My intention is to modify the build.gradle and application.properties files as instructed: <p></p>

```bash
In the build.gradle configuration, I incorporated the war plugin for the creation of a WAR file used to deploy the app onto a Tomcat server with the "spring-boot-starter-tomcat" dependency. My goal is to override the database access configuration of a H2 database.

plugins {
	id "war"
}
dependencies {
	providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
}

bootRun {
	systemProperty 'spring.datasource.url', 'jdbc:h2:mem:jpadb' // Override the datasource URL
}
```

<p></p>
<p></p>

```bash
In the application.properties files:

server.servlet.context-path=/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT
spring.data.rest.base-path=/api
#spring.datasource.url=jdbc:h2:mem:jpadb
# In the following settings the h2 file is created in /home/vagrant folder
spring.datasource.url=jdbc:h2:tcp://192.168.50.3:9092/./jpadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# So that spring will no drop de database on every execution.
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true

```
<p></p>

My goal by changing the application.properties files was to specify the path where the servlet container will serve the application and the URL in which the app will use to connect to the H2 database, which is running in TCP server mode.

 <p></p>
Secondly I added the ServletInitializer class, updated the App.js file and ensured proper referencing in index.html. <p></p>

```bash
* ServletInitializer class:

package com.greglturnquist.payroll;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReactAndSpringDataRestApplication.class);
    }

}

```
<p></p>

```bash
* App.js file:

componentDidMount() { // <2>
		client({method: 'GET', path: '/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT/api/employees'}).done(response => {
			this.setState({employees: response.entity._embedded.employees});
		});
	}

```

<p></p>

```bash
* index.html file:

<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head lang="en">
    <meta charset="UTF-8"/>
    <title>ReactJS + Spring Data REST</title>
    <link rel="stylesheet" href="main.css" />
</head>
<body>

    <div id="react"></div>

    <script src="built/bundle.js"></script>

</body>
</html>

```

<p></p>
<p></p>

## 2. Creating Vagrant File

<p></p>

Firstly, I used "vagrant init" to generate a Vagrantfile and then configured the network settings for both virtual machines to enable communication and defined provisioning tasks for each virtual machine:
<p></p>

```bash
* $ vagrant init
* Host:
http://localhost:8080/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT/
http://192.168.50.2:8080/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT/

* H2 Console:
http://localhost:8080/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT/h2-console
http://192.168.56.10:8080/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT/h2-console

*Connection string used:
jdbc:h2:tcp://192.168.30.11:9092/./jpadb
```

<p></p>
<p></p>

## 3. Vagrant File Setup

<p></p>

For each of the database box setup and web box setup I configured the database virtual machine and its web server and assigned it an IP and enabled its port forwarding.
Then, I downloaded and set up the H2 Database server and Apache Tomcat version 10.1.23 from the Apache archives.
When downloading Apache Tomcat, its important to set up its permissions. The latest, it grants permissions recursively (-R) to the user (u) for all files and directories within the extracted Apache Tomcat directory using chmod.

<p></p>
```bash
#Shell provisioner downloads Apache Tomcat 10.1.23 and extracted into the current directory.

web.vm.provision "shell", inline: <<-SHELL

wget https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.23/bin/apache-tomcat-10.1.23.tar.gz

sudo tar xzvf apache-tomcat-10*tar.gz -C .

# Setting ownership of the Apache Tomcat directory and its contents to the user vagrant and granting execute permissions recursively.

sudo chown -R vagrant:vagrant apache-tomcat-10*

sudo chmod -R u+x apache-tomcat-10*

SHELL
```
<p></p>

```bash
# Start Tomcat server script:
web.vm.provision "shell", :run => 'always', inline: <<-SHELL
   ./apache-tomcat-10*/bin/startup.sh
SHELL
```
<p></p>

When its finished, cloning of the Git repository is necessary such as modifying configurations, building the project, and deploying the WAR file to Tomcat. <p></p>

```bash
# This block configures a shell provisioner for the web VM, executing a series of commands including cloning a Git repository,
# building a project with Gradle, and deploying the resulting WAR file to a Tomcat directory, all without elevated privileges.

web.vm.provision "shell", inline: <<-SHELL, privileged: false

 git clone https://github.com/Departamento-de-Engenharia-Informatica/devops-23-24-JPE-PMS-1231819.git

 cd devops-23-24-JPE-PMS-1231819/CA2/Part2/react-and-spring-data-rest-basic

 sudo chmod u+x gradlew

 ./gradlew clean build

# To deploy the war file to tomcat9 do the following command:

 sudo cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war ~/apache-tomcat-10*/webapps/
```
<p></p>
<p></p>

## 3. Running Vagrant File
<p></p>
Now that all is set up and configured, it is expected that Vagrant upstarts the virtual machines defined in the VagrantFile and runs its scripts involving the set up and configuration necessary for its functioning, thus allowing all sorts of communication between the host and the virtual machines.
<p></p>
<p></p>

## 4. Alternative to Vagrant
<p></p> 
As an alternative to VirtualBox, a Vagrantfile can also be creating by using Qemu: <p></p>

```bash
# Define db machine configuration
config.vm.define "db" do |db|
    db.vm.box = "generic/ubuntu2004"
    db.vm.hostname = "db"

    # Qemu provider settings
    db.vm.provider "qemu" do |qe|
      qe.arch = "x86_64"
      qe.machine = "q35"
      qe.cpu = "host"
      qe.memory = "512"
    end
```
 <p></p>

With Qemu, Vagrant doesn't automatically download the machine image so instead, a script was added to download the image before running the vagrant up command.
The rest of the steps to run it remain identical to those for VirtualBox already described:
 <p></p>
```bash
 config.vm.provision "shell", run: 'once', inline: <<-SHELL
    if [ ! -f ubuntu2004.img ]; then
      wget https://cloud-images.ubuntu.com/focal/current/focal-server-cloudimg-amd64.img -O ubuntu2004.img
    fi
  SHELL
```
 <p></p>

