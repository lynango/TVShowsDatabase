<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TV Shows</title>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body class="p-5 mb-2 bg-secondary text-white">
		<h1 class="text-center my-3">Welcome, <c:out value="${currentUser.firstName}"/> <c:out value="${currentUser.lastName}"/>!</h1>
		<nav class="d-flex justify-content-end col-xs-3">
				<a href="/shows/new" class="btn btn-info">Add New Show</a>
			<a href="/logout" class="btn btn-primary">Logout</a>
		</nav>

		<div class="col-8 p-4 my-5 mx-auto">
			<h1>TV Shows</h1>
			<c:if test="${shows.size() == 0}"><h5>There are currently no TV shows.</h5></c:if>
			<c:if test="${shows.size() > 0}"> </c:if>  
			<table class="table table-striped table-dark">
				<thead class="thead-light">
					<tr>
	    				<th>Show</th>
					    <th>Network</th>
		
			  		</tr>
				</thead>
				<tbody>
					<c:forEach var="show" items="${shows}">
						<tr>
							<td><a href="/shows/${show.id}"><c:out value="${show.title}"/></a></td>
							<td><c:out value="${show.network}"/></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>