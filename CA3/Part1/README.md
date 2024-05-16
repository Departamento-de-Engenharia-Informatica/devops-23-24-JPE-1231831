

<strong>Repository:</strong> devops-23-24-JPE-PSM-1231831
 <p></p>

# Class Assignment 1

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
In the DevOps class we were challenged to work with Git for version control with and without branches. Additionally, it was proposed that we should document a detailed workflow and the issues encountered at it during the development process.
Thus, with the goal of following on this approach, I intend to summarize all the actions taken, step-by-step, and the recommendations for resolving the consequent encountered errors.

<p></p>

## 1. Tutorial: Analysis, Design and Implementation
<p></p>
Repository Creation: With the aim of starting the 1st part of the assignment CA1, a repository had to be created. <p></p>
Therefore, I manually created a new one on GitHub named “devops-23-24-JPE-1231831” and classified it as private. Afterwards, I accessed my Documents using “cd ~/Documents” and, with the “mkdir Devops” command, I created the Devops folder on my Documents.<p></p>
Upon entering the folder Devops with “cd Devops” command, I cloned the repository on GiHub named “tut-react-and-spring-data-rest”. On this stage, I could’ve have used “winget install -e –id GNU Nano” to install the GNU Nano text editor using the Windows Package Manager (winget) and then “touch .gitignore” and “nano .gitignore” so as to create a file name .gitignore on the current directory and then add the files for the Git repositor to ignore on Intellij IDEA tool (https://www.toptal.com/developers/gitignore/api/intellij) when tracking changes, although, upon the manual creation of the repository on GitHub, the .gitignore was already created. The .gitignore file is important to avoid inclusion of any unnecessary files in the repository.<p></p>
Later on, a README file was created for the work documentation using “echo “# devops-23-24-JPE-1231831” >>README.md”.<p></p>
Then, I used the “git init” command to initialize a new empty repository that when was run it created a .git hidden folder that stored all the information that Git needed to track for the projects versions. To add the readme file to the GitHub “devops-23-24-JPE-1231831” repository I implemented the following steps:<p></p>

```bash
*	git add README.md
*	git commit -m “Commit 1: Teste”
*	git branch -M main
*	git remote ad origin git@github.com: Departamento-de-Engenharia-Informatica/devops-23-24-JPE-1231831.git
*	git push -u origin main (-u automatically associates a local branch with its remote counterpart)>
```
<p></p>
Since the manual repository created had the VisualStudio Code as the pre-defined tool to use, I also used the “git config –global core.editor “idea –wait” command to change it to the Intellij IDEA tool.
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
