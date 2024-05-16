

<strong>Repository:</strong> devops-23-24-JPE-PSM-1231831
 <p></p>

# Class Assignment 3 - Part 1

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
The main goal of this assignment is to implement virtualization methods for transferring and executing previous project work within a virtualized Ubuntu environment. This configuration will replicate the development setup consistently across various platforms, guaranteeing that all features and requirements are handled uniformly and separately from the host platforms.

<p></p>

## Tutorial: 
<p></p>
<p></p>

### 1. Creation of the Virtual Machine

<p></p>
Firstly, I downloaded the VirtualBox from the Oracle website (https://www.virtualbox.org/). Afterwards, I created my virtual machine and through its setup I selected "Linux" as the type and "Ubuntu 64bit" (Ubuntu 22.04.3 version) as the version with atleast 2048 MB of RAM and 20 GB of disk space. Later on I downloaded the Ubuntu Server ISO file (https://ubuntu.com/download/server) and on the option to choose a disk file in Optical Drive I alocated the downloaded ISO file. Then, I proceeded with the Ubuntu instalation with the standard options.

<p></p>
<p></p>

### 2. Configuring the Virtual Machine
<p></p>
Considering that the VirtualBox automatically sets up a network adapter in NAT mode and establishes an interface for a host-only network, on the "Host Network Manager" I created a new host-only network and on "Network Adapter 2" I turned it to be a "Host-Only Adapter". Then, I assigned a static IP within the range of the host-only network (192.168.56.1/24), ensuring direct communication between the host and the guest without needing to set up port forwarding.

<p></p>
<p></p>

#### Setting up the IP:

<p></p>
to define a static ip in this ip range I used the following steps: <p></p>

```bash
*	 sudo apt update - updates the package repositories
* sudo apt install net-tools
*	sudo nano /etc/netplan/01-netcfg.yaml
Adding to the file:
* network:
        version: 2
        renderer: networkd
        ethernets:
            enp0s3:
                dhcp4: yes
            enp0s8:
                addresses:
                    - 192.168.56.5/24
* sudo netplan apply - to apply the changes
```

<p></p>
<p></p>
Afterwards I installed the openssh-server and enabled password authentication:  <p></p>

```bash
* sudo apt install openssh-server
* Enable password authentication in the SSH configuration and restart SSH service
* sudo systemctl restart ssh
```

<p></p>


#### Accessing the Virtual Machine:
<p></p>
I accessed the Virtual Machine session via SSH: <p></p>

```bash
* ssh ines@192.168.64.5
```
 <p></p>
After the connection, it shows the system status information such as memory usage, system load, and available updates. Then, I procced to install the other tools.
 <p></p>
 <p></p>

#### Instalation of Virtual Machine tools:

<p></p>
** Installing Java:
<p></p>

```bash
* sudo apt update - To ensure everything is in order
* sudo apt install openjdk-8-jdk-headless
```
<p></p>
** Installing Git:
<p></p>

```bash
* sudo apt install git 
```
<p></p>

** Installing Maven/Gradle:
<p></p>

```bash
* sudo apt install maven
* sudo apt install gradle
```
<p></p>
<p></p>

#### Cloning the Repository:
<p></p>
I procceded to clone the repository containing my project.: <p></p>

```bash
* git clone git@github.com:Departamento-de-Engenharia-Informatica/devops-23-24-JPE-1231831.git
```
<p></p>
<p></p>
Afterwards I built and executed the CA1 (spring-boot tutorial basic) project: <p></p>

```bash
* cd CA1/basic
* chmod +x mvnw - configures maven wrapper to grant it execution permissions
* -/mvnw compile - compiles the project running
* ./mvnw spring-boot:run - starts the SpringBoot application
```
<p></p>
<p></p>
To check if the app was running correctly, I accessed it through the host machine's web browser by using the virtual machines IP address and the port configured in the project. 
It started with the port 8080 (default Tomcat server port).<p></p>

```bash
* ip addr
```
<p></p>
<p></p>
Then I built and executed the CA2_Part1 (chat app) project: <p></p>

```bash
* cd CA2/Part1/gradle_basic_demo
* chmod +x gradlew - give execute permmitions to gradle wrapper script
* ./gradlew build - to ensure all Gradle dependencies are set up properly
* ./gradlew runServer
* gradlew runClient --args="192.168.56.5 59001" - executes runClient" task with the port 59001
  ```
<p></p>
<p></p>
Then I built and executed the CA2_Part2 project: <p></p>
   
```bash
* cd CA2/Part2/react-and-spring-data-rest-basic
* chmod +x gradlew - configures gradle wrapper to grant it execution permissions
* ./gradlew build
* ./gradlew bootRun
```
<p></p>
<p></p>
To check again if the app was running correctly through the web browser, on the search bar I typed:

```bash
*  192.168.56.5:8080
```
<p></p>
<p></p>

#### Creating CA3 folder:
<p></p>
To create the new CA3 project folder, I executed the following steps: <p></p>

```bash
* cd ~/Devops-23-24-JPE-1231831
* mkdir CA3
* cd CA3
* touch README.md
```
<p></p>
And then I needed to create a tag ca3-part1: <p></p>
```bash
* git tag ca3-part1
* git push origin ca3-part1
```
 <p></p>
  <p></p>
   <p></p>
    <p></p>
    
