

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

## 1. Tutorial: Creation of the Virtual Machine

<p></p>
Firstly, I downloaded the VirtualBox from the Oracle website (https://www.virtualbox.org/). Afterwards, I created my virtual machine and through its setup I selected "Linux" as the type and "Ubuntu 64bit" (Ubuntu 22.04.3 version) as the version with atleast 2048 MB of RAM and 20 GB of disk space. Later on I downloaded the Ubuntu Server ISO file (https://ubuntu.com/download/server) and on the option to choose a disk file in Optical Drive I alocated the downloaded ISO file. Then, I proceeded with the Ubuntu instalation with the standard options.

<p></p>
<p></p>

## 2. Tutorial: Configuring the Virtual Machine
<p></p>
Considering that the VirtualBox automatically sets up a network adapter in NAT mode and establishes an interface for a host-only network, on the "Host Network Manager" I created a new host-only network and on "Network Adapter 2" I turned it to be a "Host-Only Adapter". Then, I assigned a static IP within the range of the host-only network (192.168.56.1/24), ensuring direct communication between the host and the guest without needing to set up port forwarding.

<p></p>
<p></p>
** Setting up the Virtual Machine - IP:

<p></p>
to define a static ip in this ip range I used the following steps: <p></p>

```bash
*	 sudo apt update - updates the package repositories
* sudo apt install net-tools
*	sudo nano /etc/netplan/01-netcfg.yaml
<p></p>
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
<p></p>
* sudo netplan apply - to apply the changes
```
<p></p>

<p></p>

## 2. CA1 Step-by-Step:

### 1st Part of CA1:
<p></p>
Using PowerShell I wrote the following commands:<p></p>

<ol>
  <li> Copy the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1:
<ul>
 <p></p>
  </ul>

 ```bash

  *	On the “Devops” folder I created CA1 folder - mkdir CA1
  * Copied the tut basic folder to CA1 – cp -r tut-react-and-spring-data-rest/basic CA1
  *	Copied the tut pom file – cp tut-react-and-spring-data-rest/pom.xml CA1

 ```

   <p></p>
</li>
<li>.	Commit the Tutorial React.js and Spring Data REST Application changes and push them:
 <ul>

  ```bash
  * Entered CA1 folder - cd CA1
  * Initilialized empty git repository: git init
  * Added the files – git add basic // git add pom.xml
  * Escrevi mensagem de commit: git commit -m “1 first commit”
  * Fiz push dos ficheiros para o main branch – git push origin main 

 ```

</ul>
</li>
 <p></p>
<li>	Tag the initial version as v1.1.0 and push it to the server:
<ul>

   ```bash
 
  * git tag v1.1.0	 
  * git push origin v1.1.0

  ```

</ul>
</li>
 <p></p>
<li> Add new JobYears field to the company employee: <p></p>

* For this, I manually added the basic folder as an Intellij IDEA project and implemented the attribute “int jobYears” to the Employee class, changed its constructor and added a getter, setter and validations (jobYears <0). Subsequently I tested the added code with success and insuccess tests for the validations along with constructor and toString testing.
 </li>
<p></p>	
<li>Commit the code, push it and create a new tag v1.2.0 (I associated the commit to the issues created on GitHub repository):
 <ul>

  ```bash

  * git add basic/src/main/java/com/greglturnquist/payroll/DatabaseLoader.java
  * git add basic/src/main/java/com/greglturnquist/payroll/Employee.java
  * git add basic/src/test/java/EmployeeTest.java
  * git commit -m “#2 Commit 2: JobYears Implementation + Testing”
  * git push origin main
  * git tag v.1.2.0
  * push origin v.1.2.0
  * git status (to confirm if the process went well)

  ```

 </ul>
</li>
<p></p>
<li>	At the end of the 1st part of the assignment, mark the repository with the tag ca1-part1:
 <ul>

  ```bash

* git tag ca1-part1
* git push origin ca1-part1
* git tag (to confirm if the process went well)

```

</ul>
 </li>
 </ol>
  <p></p>
  <p></p>

### 2nd Part of CA1:
<p></p>
Using PowerShell I wrote the following commands:<p></p>
<ol>

 <li>Create a branch named “email-field”:
 <ul>

  ```bash

    * git checkout main
    * git merge main (to check if everything was up to date)
    * git checkout -b email-field

 ```

  </ul>
</li>
 <p></p>
<li>	Add a new email field to the application:<p></p>

* Implemented the attribute “String email” to the Employee class, changed its constructor and added a getter, setter and validations (email == null || !email.matches (“[A-Za-z0-9+_.-]+@(.+$)). Subsequently I tested the added code with success and insuccess tests for the validations along with constructor and toString testing.

</li>
 <p></p>
<li>	Commit the code, push it and create a new tag v1.3.0 (I associated the commit to the issues created on GitHub repository):
 <ul>

  ```bash

   * git add basic/src/main/java/com/greglturnquist/payroll/DatabaseLoader.java
   * git add basic/src/main/java/com/greglturnquist/payroll/Employee.java
   * git add basic/src/test/java/EmployeeTest.java
   * git commit -m “#3 Email Implementation”
   * git push origin main
   * git tag v.1.3.0
   * push origin v.1.3.0
   * git status (to confirm if the process went well)

  ```

</ul>
</li>
 <p></p>
<li>	Create branches for fixing bugs, merge it into master and create a new tag v1.3.1
 <ul>
