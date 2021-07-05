<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film</title>
</head>
<body>
  <form action="goHome.do" method="GET">
    <input type="submit" value="Return Home" />
  </form> 
  <c:choose>
    <c:when test="${! empty film}">  <!-- if object "film" added to mv, from class FilmController, is not empty, print film details  -->
    	<h3>Film Info Updated!</h3>
      <ul>
        <li>Film ID: ${film.id}</li>
        <li>Title: ${film.title}</li>
        <li>Description: ${film.description}</li>
        <li>Release Year: ${film.releaseYear}</li>
        <li>Language: ${film.language}</li>
        <li>Rental Duration: ${film.rentalDuration}</li>
        <li>Rental Rate: ${film.rentalRate}</li>
        <li>Length: ${film.length}</li>
        <li>Replacement Cost: ${film.replacementCost}</li>
        <li>Rating: ${film.rating}</li>
        <li>Special Features: ${film.specialFeatures}</li>
      </ul>      
    </c:when>
    <c:otherwise>
      <p>Update failed, incorrect field entry. Try again.</p> <!-- Otherwise, print "No film found" -->
    </c:otherwise>
  </c:choose>
</body>
</html>