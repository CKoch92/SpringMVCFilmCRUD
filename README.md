# Spring MVC Film C.R.U.D.

### Developers
#### [Ray Space](https://github.com/ryspc) & [Caleb Koch](https://github.com/CKoch92)

### Overview
This is a full stack Java application and implements web-based C.R.U.D. functionality, utilizing Spring MVC and the DAO pattern. The DAO implementation uses JDBC to persist and retrieve data through MySQL.

### Functionality
#### Retrieve Film Data
A user can enter a Film's ID and see the details of the film in a web page. If the film is not found, they see an appropriate message. When a film's details are displayed, it's actors and categories are also listed.
#### Add Film
A user can choose to add a new film. They can enter all the properties of the film. Their input will be used to create Film object, which the DAO implementation will save in the database. If the insert fails, the user is informed of this.
#### Delete Film
When a user retrieves a film, they have the option of deleting it. If they delete the film, it is removed from the database. If the delete fails (such as, due to child records), the user is informed of this.
#### Edit Film
When a user retrieves a film, they have the option of editing it. If they choose this, all the film's current properties are displayed in a form, allowing them to change any property except the film's ID. When they submit the form, that film's record is updated in the database. If the update fails, the user is informed of this.
#### Search and List Films
A user can search for films by keyword/pattern in title or description. From the resulting list of films, the user can choose to update or delete a record.

### Technologies Used
- Java
- HTML & CSS
- Spring ToolSuite
- Gradle
- MySQL
- JDBC
- MAMP

### How to Run
This full stack Java application runs on an Apache Tomcat webserver. User can interact with the application through online navigation.
To host your own instance, you are going to need a similar webserver that implements Java servlet containers. We recommend importing the program to Eclipse IDE, and installing a Tomcat instance within Eclipse to run locally.

### Lessons Learned
Java web applications consist of multiple systems communicating with each other, manipulating the data and forwarding it to other services or pages. The MVC design pattern is a very good framework for developing scalable web applications.

### UML Diagram
![ERDiagram](https://user-images.githubusercontent.com/83374176/123504132-bc31c380-d614-11eb-8844-aebbae35b5b1.png)
