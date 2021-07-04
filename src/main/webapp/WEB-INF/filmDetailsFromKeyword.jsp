<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Results</title>
</head>
<body>
  <form action="goHome.do" method="GET">
    <input type="submit" value="Return Home" />
  </form> 
	<h3>Results</h3>
	<c:choose>
		<c:when test="${! empty films}">
			<!-- if object "film" added to mv, from class FilmController, is not empty, print film details  -->
			<c:forEach var="film" items="${films}">
				<form action="editFilm.do" method="GET">
					<ul>
						<li>Film ID: ${film.id}</li>
						<li>Title: ${film.title}</li>
						<!--         Add Link to the film details page -->
						<li><input type="hidden" name="filmID" value="${film.id}"></li>
						<li><input type="submit" value="Edit" /></li>
					</ul>
				</form>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>No films found</p>
			<!-- Otherwise, print "No film found" -->
		</c:otherwise>
	</c:choose>

</body>
</html>