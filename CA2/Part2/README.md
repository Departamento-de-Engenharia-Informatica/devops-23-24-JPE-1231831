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

## Tutorial
<p></p>
<p></p>

### 1. Application conversion to Gradle
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
	 
**Rest Repositories**  -> When the "Rest Repositories" dependency is added to the Spring Boot project, it essentially taps into the power of Spring Data REST. This module takes care of exposing the JPA repositories as RESTful endpoints without having to dive into the writing of controller code.
  <p><p>
	  
**Thymeleaf**  -> Thymeleaf is a contemporary Java-based template engine designed to simplify the creation of dynamic web pages.
  <p><p>
	  
**JPA**  -> The JPA (Java Persistence API) dependency is like the bridge between your Java application and your relational database. When included in the project, it essentially taps into a standardized way of managing relational data in the Java application.
  <p><p>
	  
**H2** -> H2 stands out as a swift, freely accessible, in-memory database entirely crafted in Java. It allows to build and experiment with applications without the having to set up a separate database server.
<p><p>
 <p><p>
3. Generate the dependencies and download the project, by extracting the files to the CA2 Part2 folder.
 <p><p>
 <p><p>
4. Open the project in IntelliJ in order to delete de src folder and copy the src folder fromCA1_Part 1 to the project folder, along with the webpack.config.js and the package.json files.

<p><p>
 <p><p>
 5. Delete the src/main/resources/static/built/ folder.
<p><p>
 <p><p>
 6. Change javax.persistence imports to jakarta.persistence in the Employee.java class.

<p><p>
 <p><p>

### 2. Gradle's Implementation Changes
  <p><p>
1. Add the gradle plugin org.siouan.frontend to the project so that gradle is also able to manage the frontend. Since the project was already the java 17 version, I added the correspondent:

<p><p>

```bash
*  id "org.siouan.frontend-jdk17" version "8.0.0"
```

<p><p><p><p>
  2. Add the package manager to the build.gradle file that allows to configure the package.json file, before the scripts section:<p><p>

```bash
*  "packageManager": "npm@9.6.7",
```

<p><p><p><p>
3. Add also the following code in build.gradle to configure the previous plug-in: <p><p>
 
```bash
 frontend {
nodeVersion = "16.20.2"
assembleScript = "run build"
cleanScript = "run clean"
checkScript = "run check"
}
```

 <p><p>
 <p><p>
 4. Update the scripts section/object in package.json to configure the execution of
webpack:  <p><p>

```bash
"scripts": {
"webpack": "webpack",
"build": "npm run webpack",
"check": "echo Checking frontend",
"clean": "echo Cleaning frontend",
"lint": "echo Linting frontend",
"test": "echo Testing frontend"
},
```

  <p><p><p><p>
5. Compile the project in the terminal:<p><p>

```bash
* ./gradlew build
* ./gradlew bootRun
```

<p><p><p><p>
6. Copy the generated jar to a folder named ”dist” located a the project root folder level, compile it and then commit/push it to the repository: <p><p>


```bash
task copyJar(type: Copy, dependsOn: build) {
	from 'build/libs/'
	into 'dist'
	include '*.jar'
}
* ./gradlew build
* git add .
* git commit -m "#15 Added copyJar task"
* git push
```

<p><p><p><p>
7. Add a task to gradle to delete all the files generated by webpack(src/resources/main/static/built/).  It is executed as part of the clean task to ensure the project directory is clean before building. Afterwards, compile it and commit/push it to the repository: <p><p>

```bash
task deleteWebpackFiles(type: Delete) {
	delete 'src/main/resources/static/built'
}
clean.dependsOn(deleteWebpackFiles)
* ./gradlew build
* git add .
* git commit -m "#16 Added copyJar task"
* git push
```

<p><p><p><p>
8. Merge with the master branch: <p><p>

```bash
* git checkout master
* git merge --no-ff tut-basic-gradle
* git push
```

<p><p><p><p>
9. Tag the assignment with ca2-part2:  <p><p>

```bash
* git tag ca2-part2
* git push origin ca2-part2
```

<p><p><p><p>

## Alternative Implementation - Maven
<p><p><p><p>

A good alternative to Gradle for the project's build automation would be Maven since it can be a viable option. Maven, like Gradle, is a widely used tool for managing Java projects, but it operates using a different approach. Instead of the Gradle DSL, Maven employs an XML file called pom.xml to organize the project's structure and dependencies.<p><p>
Here's how I can implement Maven to the project:

<p><p>
<p><p>
1. To begin, I should start a new Maven SpringBoot project following the steps that have already been done by done through the tutorial, using the link (https://start.spring.io) with the following dependencies: Rest Repositories; Thymeleaf; JPA; H2.
<p><p><p><p>

2. Add the frontend-maven-plugin to the project by adding this code to the pom.xml file: <p><p>

```bash
<plugin>
    <groupId>com.github.eirslett</groupId>
    <artifactId>frontend-maven-plugin</artifactId>
    <version>1.9.1</version>
    <configuration>
        <installDirectory>target</installDirectory>
    </configuration>
    <executions>
        <execution>
            <id>install node and npm</id>
            <goals>
                <goal>install-node-and-npm</goal>
            </goals>
            <configuration>
                <nodeVersion>v12.14.0</nodeVersion>
                <npmVersion>6.13.4</npmVersion>
            </configuration>
        </execution>
        <execution>
            <id>npm install</id>
            <goals>
                <goal>npm</goal>
            </goals>
            <configuration>
                <arguments>install</arguments>
            </configuration>
        </execution>
        <execution>
            <id>webpack build</id>
            <goals>
                <goal>webpack</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

<p><p><p></p>
3. Add copyJar task to the project's pom.xml file: <p></p>

```bash
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>3.2.0</version>
    <executions>
        <execution>
            <id>copy-jar</id>
            <phase>package</phase>
            <goals>
                <goal>copy-resources</goal>
            </goals>
            <configuration>
                <outputDirectory>${project.basedir}/dist</outputDirectory>
                <resources>
                    <resource>
                        <directory>${project.build.directory}</directory>
                        <includes>
                            <include>*.jar</include>
                        </includes>
                    </resource>
                </resources>
            </configuration>
        </execution>
    </executions>
</plugin>
```

<p><p><p></p>
4. Add deleteWebpackFiles task to the project's pom.xml file: <p></p>

```bash
<plugin>
    <artifactId>maven-clean-plugin</artifactId>
    <version>3.1.0</version>
    <configuration>
        <filesets>
            <fileset>
                <directory>${project.basedir}/src/main/resources/static/built</directory>
                <includes>
                    <include>**/*</include>
                </includes>
            </fileset>
        </filesets>
    </configuration>
</plugin>
```

<p><p><p></p>
5. Compile the Maven project: <p></p>

```bash
mvn clean install
git add .
git commit -m "#17 Added the Maven build automation tool"
git push
```
<p><p><p></p>
<p><p><p></p>

## Gradle VS Maven

<p><p><p></p>

### Maven:
<p></p><p></p>

* **Pros**

<p></p><p></p>
	
**Convention over Configuration**: Maven follows a convention-based approach, reducing the need for explicit configuration.<p><p>
**Widespread Adoption**: Maven is extensively used in the Java ecosystem, making it easier to find resources and support.<p><p>
**Robust Dependency Management**: Maven's dependency management system is mature and well-established.<p><p>
**Stable Build System**: Maven's build process is predictable and stable, which can be advantageous for large projects.<p><p>
**Rich Plugin Ecosystem**: Maven has a vast array of plugins available for extending its functionality.<p><p>

<p></p><p></p>
 
* **Cons**
<p></p><p></p>
	
**Limited Flexibility**: Maven's convention-based approach can be restrictive for complex or non-standard project setups.<p><p>
**XML Configuration**: Maven's configuration is done through XML, which some developers find verbose and less readable.<p><p>
**Slow Performance**: Maven's build process can be slower compared to Gradle, especially for large projects.<p><p>
**Limited Support for Incremental Builds**: Maven's incremental build capabilities are not as advanced as Gradle's, leading to longer build times for iterative development.<p><p>
**Less Concise Syntax**: Maven's XML configuration can result in more verbose build scripts compared to Gradle's Groovy DSL.<p><p>

<p></p><p></p>

### Gradle

<p></p><p></p>
	
* **Pros**

<p></p><p></p>
	
**Flexibility and Customization**: Gradle offers a highly flexible and customizable build system, allowing for complex project setups and custom workflows. <p><p>
**Groovy DSL**: Gradle's build scripts are written in Groovy, which provides a concise and expressive syntax. <p><p>
**Incremental Builds**: Gradle's advanced incremental build capabilities can significantly reduce build times, especially for iterative development.<p><p>
**Performance**: Gradle is known for its fast build times, particularly for large projects.<p><p>
**Modern Build System**: Gradle is designed with modern development practices in mind, making it suitable for a wide range of project types, including Android and Kotlin.<p><p>

<p></p><p></p>
	
* **Cons**
	
<p></p><p></p>
	
**Learning Curve** : Gradle has a steeper learning curve compared to Maven, particularly for developers unfamiliar with Groovy.<p><p>
**Complexity**: Gradle's flexibility can sometimes lead to complex build scripts, which may be challenging to maintain.<p><p>
**Less Widespread Adoption**: Although Gradle is gaining popularity, it's still not as widely adopted as Maven in the Java ecosystem.<p><p>
**Plugin Compatibility**: While Gradle has a rich plugin ecosystem, compatibility issues with certain plugins may arise due to its evolving nature.<p><p>
**Dynamic Dependency Resolution**: Gradle's dynamic dependency resolution can sometimes lead to unexpected behavior if not managed properly.<p><p>

<p><p><p><p><p><p><p><p>
	
In summary, Maven offers simplicity and stability, making it a suitable choice for straightforward projects with standard requirements. On the other hand, Gradle provides flexibility, performance, and modern features, making it ideal for complex projects and teams looking for more advanced build capabilities.
