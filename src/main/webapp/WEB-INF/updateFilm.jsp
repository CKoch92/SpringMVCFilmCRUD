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
	<h3>Update Film Info</h3>
	<c:choose>
		<c:when test="${! empty film}">
			<form action="updateFilm.do" method="GET">
		<b>Title</b> <br />
		<input type="text" name="title" value="${film.title}" > <br />
		<b>Description</b> <br />
		<input type="text" name="description" value="${film.description}" /> <br />
		<b>Release Year</b> <br />
		<input type="number" name="releaseYear" value="${film.releaseYear}" /> <br />
		<b>Language</b> 1:English, 2:Italian, 3:Japanese, 4:Mandarin, 5:French <br> 
		<input type="number" name="language" value="${film.language}" /> <br />
		<b>Rental Duration</b> <br />
		<input type="number" name="rentalDuration" value="${film.rentalDuration}" /> <br />
		<b>Rental Rate</b> <br />
		<input type="number" step=".01" name="rentalRate" value="${film.rentalRate}" /> <br />
		<b>Length</b> <br />
		<input type="number" name="length" value="${film.length}" /> <br />
		<b>Replacement Cost</b> <br />
		<input type="number" step=".01" name="replacementCost" value="${film.replacementCost}" /> <br />
		<b>Rating</b> G, PG, PG13, R, NC-17 <br> 
		<input type="text" name="rating" value="${film.rating}" /> <br />
		<b>Special Features</b> Trailers, Commentaries, Deleted Scenes, Behind the Scenes <br> 
		<input type="text" name="specialFeatures" value="${film.specialFeatures}" /> <br /> 
		<input type="hidden" name="filmID" value="${film.id}">
		<input type="submit" value="Submit" />
	</form>		
		</c:when>
		<c:otherwise>
			<p>No film found</p>
			<!-- Otherwise, print "No film found" -->
		</c:otherwise>
	</c:choose>

</body>
</html>