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
	<h3>1 Film record created.</h3>
	<c:choose>
		<c:when test="${! empty newFilm}">
			<!-- if object "film" added to mv, from class FilmController, is not empty, print film details  -->
			<ul>
				<li>New film ID: ${newFilm.id}</li>
				<li>Title: ${newFilm.title}</li>
				<li>Description: ${newFilm.description}</li>
				<li>Release Year: ${newFilm.releaseYear}</li>
				<li>Language: ${newFilm.language}</li>
				<li>Rental Duration: ${newFilm.rentalDuration}</li>
				<li>Rental Rate: ${newFilm.rentalRate}</li>
				<li>Length: ${newFilm.length}</li>
				<li>Replacement Cost: ${newFilm.replacementCost}</li>
				<li>Release Year: ${newFilm.rating}</li>
				<li>Special Features: ${newFilm.specialFeatures}</li>
			</ul>			
		</c:when>
		<c:otherwise>
			<p>No film found</p>
			<!-- Otherwise, print "No film found" -->
		</c:otherwise>
	</c:choose>
</body>
</html>