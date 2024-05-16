


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
To define a static ip in this ip range I used the following steps: <p></p>

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
