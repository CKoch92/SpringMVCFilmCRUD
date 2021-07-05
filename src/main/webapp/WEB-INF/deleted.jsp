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
<h3>Film Deleted!</h3>
    </c:when>
    <c:otherwise>
    <h3>Film deletion failed due to film data existence in other database tables.(Child records can not be deleted)</h3>
    </c:otherwise>
  </c:choose>
</body>
</html>