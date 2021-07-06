# Spring MVC Film C.R.U.D.

## Developers
#### [Ray Space](https://github.com/ryspc) & [Caleb Koch](https://github.com/CKoch92)

## Overview
This is a full stack Java application and implements web-based C.R.U.D. functionality, utilizing Spring MVC and the DAO pattern. The DAO implementation uses JDBC to persist and retrieve data through MySQL.

## Functionality
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

## Technologies Used
- Java
- HTML & CSS
- Spring ToolSuite
- Gradle
- MySQL
- JDBC
- MAMP

## How to Run
This full stack Java application runs on an Apache Tomcat webserver. User can interact with the application through online navigation.
To host your own instance, you are going to need a similar webserver that implements Java servlet containers. We recommend importing the program to Eclipse IDE, and installing a Tomcat instance within Eclipse to run locally.

## Methodology
#### Database Connectivity
The functional foundation of this project is access to the sdvid database in mysql. This access is achieved in the setup of the MVCFilmSite project. The web.xml files begins this setup by establishing the welcome-file as home.jsp. Once directed to home.jsp, a html document is hyperlinked to each selection. All htmls then have "action =''" paths associated with each form that direct the program to the FilmController class. Under each of the paths, a DatabaseAccessorObject object, filmDao, is called to execute the methods associated with each action of the web application. No matter what method is called from the DatabaseAccessorObject class, each one is instantiates a Connection object = DriverManager.getConnection(), with the url, username, and password passed through as String parameters. Once created, the connection object (conn), can be used to pass through sql queries and create prepared statements. The instantiation of a Connection object with the sdvid url, username, and password, is the foundation of each method called upon in the project.

#### Search for Film by ID
When the user clicks on the Search for Film by ID link on the homepage, they are redirected to the filmDetailsFromID.html page, where they see a text input form. Once they submit an id, the form stores it as a String value with the name "id" and looks for a GetFilmFromID.do path in the FilmController. The @RequestMapping on line 29 matches the form's path (GetFilmFromID.do), parameters ("id"), and method (GET), so the getFilmFromID() method below is executed. That method takes in the "id" created by the user as a parameter and is then parsed into an int, called "userID". Now that we have an int value, the filmDao can run the findFilmById(userID) method. This method sends in a sql statement that retrieves all film columns where the id = userID. Then, a while assign all column values to the Film object (film)'s fields as it is being constructed (line 63). Once the film is constructed it is returned.

Upon being returned in the getFilmFromID method in FilmController, it is assigned to the Film object, f, and added to the ModelAndView object, mv, with the name "film". mv is then returned in the getFilmFromID method. The method also directs the app to the filmDetailsFromID.jsp page. Once on filmDetailsFromID.jsp, the film object is called, using "film" to retrieve its database column attributes, with each one being listed in an unordered list. If a film is not identified with a userID, mv sends a null object and the user is notified "No film found".

#### Search for Film by Keyword
When the user clicks on the Search for Films by Keyword link on the homepage, they are redirected to the search.html page, where they see a input form, requesting the keyword of what they would like to search. The user submits the keyword, which is stored as the parameter value "keyword" and sent to the path "GetFilmFromKeyword.do". In FilmController, the @RequestMapping's path, params, and method are matched and the getFilmFromKeyword() method is executed with the user's keyword sent in as a String parameter. The filmDao runs the findFilmByKeyword method, which inserts the keyword into a sql query that returns a list of films where the keyword is contained in the title or description. Thst list is then added to the ModelAndView object, mv, and the app is directed to the filmDetailsFromKeyword.jsp page. For each Film, the id and title are printed. If filmDao.findFilmByKeyword returns an empty list, it prints, "No films found".

#### Create Film
If the user clicks the "Create new film link" on the homepage, they are redirected to the createFilm.html page, where they are asked to input ten different types film details, such title, description, and rating. These ten input types are each given different parameter names and submitted in a single form to the path, createFilm.do. The FilmController has a RequestMapping that matches the path and ten parameters, which then enacts the createFilm method that takes in all user inputs as separate String parameters. A new Film object, film, is constructed with these parameters (id is autogenerated), is passed through the filmDao.createFilm() method, and assigned to the Film object, newFilm. newFilm is then added to the ModelAndView object, mv, with the name "newFilm" and the web app is directed to createFilm.jsp. Once on createFilm.jsp, newFilm is called to retrieve all its database column attributes, with each one being listed in an unordered list. If there is an error in constructing the new Film object (most likely empty releaseYear on non-valid rating/specialFeatures type), the user is notified and asked to try again.


#### Edit Film and Delete Film
When films are retrieved (either by keyword or id), two button, edit and delete, are printed beside each film. Both buttons are submit types, with the name action and different valiues. They both exist in the same form that looks for editFilm.do in the Film Controller class, if the user clicks on either one. Important to note in the form is a hidden FilmID parameter that is sent to editFilm.do, as well. Therefore, RequestingMapping, path="editFilm.do", takes in "filmID" AND "action" as parameters and proceeds to run the editFilm() method. In the editFilm(), an if/else statement is presented to determine if the user clicked the "edit" or "delete" button. 

If edit was clicked, the filmID is sent to the filmDao.findFilmbyId method, returning the film object of the film the user selected and redirecting to updateFilm.jsp. Here the user is presented with the column values of the film in the database and given the option to edit them. Once submitted, all values are sent as parameters in the RequestMapping path, updateFilm.do and used to construct a new Film object that replaces the database values using the filmDao.updateFilm() method and redirects to updatedFilm.jsp. From there the film attributes are all printed. If a film was not returned (likely an error in setting the correct values to certain parameters/attributes), the user is notified and asked to try again.

If delete was clicked, the filmID is sent to the filmDao.findFilmbyId method, returning the film object of the film the user selected. That film object is then sent as a parameter in the filmDao.deleteFilm() method that returns a boolean. If the film is deleted the method returns a true. If not, the method returns a false value. If a false value is returned, a null film object is added to the ModelAndView object mv. Regardless of the boolean value, the web app is direced to deleted.jsp. If the null film is sent through, the app notifies the use that the film failed to delete. This is likely because some of the film values exist in other tables as parent data.

## Lessons Learned
Java web applications consist of multiple systems communicating with each other, manipulating the data and forwarding it to other services or pages. The MVC design pattern is a very good framework for developing scalable web applications.

## EER Diagram
![EERDiagram](https://user-images.githubusercontent.com/83374176/123504132-bc31c380-d614-11eb-8844-aebbae35b5b1.png)
