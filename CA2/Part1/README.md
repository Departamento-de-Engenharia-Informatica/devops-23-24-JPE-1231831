<strong>Repository:</strong> devops-23-24-JPE-PSM-1231831
 <p></p>

# Class Assignment 2 - Part 1
 <p></p>

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
<p><p>
In the DevOps class we were challenged to work with Gradle. This repository is the result of the work developed in the context of the second assignment of the class.
The repository is organized as follows:

<p></p>

## 1. Tutorial: Repository Creation
<p></p>
Firstly, I started by cloning the repository and creating the folders for the assignment.
Then, I created a README.md draft file in each folder and pushed the changes to the repository by adding the files and committing them with a message attached to the respective issue.
<p>
 
```bash
*	  cd C:\Users\inesc\Documents\Class\devops-23-24-JPE-1231831
*   mkdir CA2 + cd CA2
*   mkdir Part1 + cd Part1
*	  git clone https://bitbucket.org/pssmatos/gradle_basic_demo/
*	  cd C:\Users\inesc\Documents\Class\devops-23-24-JPE-1231831
*	  git echo "devops-23-24-JPE-1231831" README.md
*	  mv README.md CA2 + cd CA2 + mv README.md Part1 + cd Part1
                  (did the same for Part2)
*	  git add . + git commit -m "#Issue Add xxx" + git push
```
<p><p>
 
## 2. Development: Experimenting with Gradle
<p></p>
Afterwards I started experimenting with Gradle according to the instructions of the README.md file in the repository.
I started by running the gradle wrapper command to check the version of Gradle. 
Then, I ran the gradle build command to build the project and checked the output, as well as the gradle runClient command to run the client and the gradle runServer command to run the server.
For that, I started the server on the same port as the client (59001) as it was indicated on the project, before running the client.
I also started another client with the same gradle task to check if the server was able to handle multiple clients.
<p>
 
```bash
To build a .jar file with the application:

*   ./gradlew build 

To open a terminal and execute the following command from the project's root directory:

* java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001

To start a client with the gradle task:

* ./gradlew runClient

````
<p></p>

I also added a task to the end of the build.gradle file to execute the server:
<p>

```bash
task runServer(type:JavaExec, dependsOn: classes){
      group = "DevOps"
    description = "Launches a chat server that connects to localhost:59001"
  
    classpath = sourceSets.main.runtimeClasspath


    mainClass = 'basic_demo.ChatServerApp'

    args '59001'
}
```
<p> </p>

The type is JavaExec, which means it will execute a Java class of typeJavaExec. It depends on the classes task, so it will only run after the classes task is complete so the task is executed before the runServer.
The classpath sets the runtime classpath for the server as the mainClass specifies the main class to be executed and the args sets the port to 59001.

<p>
After adding the task, I executed the server with the following command:

<p></p>

```bash
*   ./gradlew runServer
```
<p></p>

In the end, I added the changes to the repository and committed them with a message attached to the respective issue.

<p><p></p>

## 3. Unit Test

<p></p>
To begin, I created a new test folder and class in the src/test/java directory.
Then, I added a new test method to the ServerAppTest class to test the server's main method.
<p>

   ```bash
*   mkdir -p src/test/java/basic_demo (-p creates multiple directories at once)
*   touch src/test/java/basic_demo/ChatServerAppTest.java
   ```

<p></p>
After that, I set up the test class and method to test the server's main method.
<p>

 ```bash
package basic_demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
@Test
public void testAppHasAGreeting() {
App classUnderTest = new App();
assertNotNull("app should have a greeting", classUnderTest.getGreeting());
}
  ```
<p> </p>

I also ensured that the unit test would be executed with the junit 4.12 dependency by adding the following code to the build.gradle file:
<p>

  ```bash
   dependencies {
       testImplementation 'junit:junit:4.12'
   }
   ```
<p>
In the end, I compiled the project in the terminal and executed the test with the following command:
<p>

```bash
*   ./gradlew build
*   ./gradlew test
```
<p></p>

## 4. Backup of the Sources

<p></p>

For this part of the assignment, I created a new task in the build.gradle file to copy the sources of the application to a backup folder:
<p>

```bash
task copySources (type: Copy){
group = "DevOps"
description = "Backup of the sources of the application"

    from 'src/'
    into 'backup/'
}
```
<p>
Then I executed the task, commited the changes and pushed them to the repository.
<p>

```bash
* ./gradlew build
* ./gradlew copySources
```
<p></p>

## 5. ZIP File

<p></p>

For the last part of the assignment, I created a new task in the build.gradle file to create a ZIP file with the sources of the application:
<p>
The goal is to create a new task that will create a .zip backup of the source of the application and copy it into a
'backup' folder in the application root. The steps to do so are:

```bash
task zipArchive (type: Zip){
    group = "DevOps"
    description = "Zip archive of the sources of the application"

    from 'src'
    archiveFileName = 'zipArchive.zip'
    destinationDirectory = file('backup/')
}
```

<p>
Then I executed the task, commited the changes and pushed them to the repository.
<p>

```bash
*   ./gradlew build
*   ./gradlew zipArchive
```
<p></p>

## 6. Conclusion: Add Tag and Push
<p>
To conclude, I was able to successfully complete the assignment by following the instructions provided in the README.md file of the repository.
So, in the end, I marked the repository with the tag ca2-part1 and pushed the changes with a message attached to the respective issue.
<p>

```bash

*  git tag ca2-part1
*  git push origin ca2-part1
```
<p></p>


