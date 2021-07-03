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
  <c:choose>
    <c:when test="${! empty film}">  <!-- if object "film" added to mv, from class FilmController, is not empty, print film details  -->
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
        <li>Category: ${film.category}</li>
        <li>Rating: ${film.rating}</li>
        <li>Actors: ${film.actors}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No film found</p> <!-- Otherwise, print "No film found" -->
    </c:otherwise>
  </c:choose>
</body>
</html>