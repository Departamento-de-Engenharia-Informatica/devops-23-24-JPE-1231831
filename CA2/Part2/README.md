<strong>Repository:</strong> devops-23-24-JPE-PSM-1231831
 <p></p>

# Class Assignment 2 - Part 2
 <p></p>

<p></p>

<strong>Student: </strong>
Inês Guedes
 <p></p>

<strong>Number:</strong>
1231831
<p></p>
<p></p>
<p></p>

## Introduction
<p><p>
In the DevOps class we were challenged to work with Gradle. This repository is the result of the work developed in the context of the second assignment part 2 of the class.
The repository is organized as it follows:

<p></p>

## 1. Tutorial: Application conversion to Gradle
<p></p>
1. Create a new branch called "tut-basic-gradle" in the repository and switch to it:
<p>
 
```bash
* git branch tut-basic-gradle
* git checkout tut-basic-gradle
```
<p><p>
<p><p>
2. Generate a new gradle spring boot project on the recommended site (https://start.spring.io/) by choosing the dependencies which will be working on:
 <p><p>
     - Rest Repositories -> When the "Rest Repositories" dependency is added to the Spring Boot project, it essentially taps into the power of Spring Data REST. This module takes care of exposing the JPA repositories as RESTful endpoints without having to dive into the writing of controller code.
  <p><p>
     - Thymeleaf -> Thymeleaf is a contemporary Java-based template engine designed to simplify the creation of dynamic web pages.
  <p><p>  
   - JPA -> The JPA (Java Persistence API) dependency is like the bridge between your Java application and your relational database. When included in the project, it essentially taps into a standardized way of managing relational data in the Java application.
  <p><p>
     - H2 -> H2 stands out as a swift, freely accessible, in-memory database entirely crafted in Java. It allows to build and experiment with applications without the having to set up a separate database server.
<p><p>
 <p><p>
3. Generate the dependencies and download the project, by extracting the files to the CA2 Part2 folder.
 <p><p>
 <p><p>
4. Open the project in IntelliJ in order to delete de src folder.
 <p><p>
 <p><p>
5. Copy the src folder from Class Assignment 1 Part 1 to the project folder.
   <p><p>
 <p><p>
6. Copy the webpack.config.js and the package.json files from Class Assignment 1 Part 1 to the project folder.
