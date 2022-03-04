<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
 
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Show Details</title>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/style.css">
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body class="p-5 mb-2 bg-secondary text-white">
		<nav class="d-flex justify-content-end col-xs-3">
			<a href="/shows" class="btn btn-info">Back</a>
			<a href="/logout" class="btn btn-primary">Logout</a>
		</nav>
		<div class="col-8 p-4 my-5 bg-dark text-light mx-auto">
			<div class="card-body">
				<h1><strong><c:out value="${thisShow.title}"/></strong></h1>
				<h4>Posted By: <c:out value="${thisShow.creator.firstName}"/> <c:out value="${thisShow.creator.lastName}"/></h4>
				<h4>Network: <c:out value="${thisShow.network}"/></h4>
				<h4>Description: <c:out value="${thisShow.description}"/></h4>
				<div class="mt-3">							
					<c:if test="${currentID == thisShow.creator.id}">
						<a href="/shows/${thisShow.id}/edit" class="btn btn-primary">Edit</a>
					</c:if>
				</div>
			</div>						
	
		<hr>
		<div>
			
		</div>
		<div class="col-8 p-2 my-5 mx-auto">
			<c:if test="${ratings.size() == 0}"><h5>There are currently no ratings.</h5></c:if>
			<c:if test="${ratings.size() > 0}"> </c:if>
			<table class="table table-striped table-dark">
				<thead class="thead-light">
					<tr>
	    				<th>Name:</th>
					    <th>Rating</th>
			  		</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${ratings}">
						<tr>
							<td><c:out value="${item.user.firstName}"/></td>
							<td><c:out value="${item.rating}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="mt-4">
				<h4>Leave a Rating:</h4>
					<form:form action="/shows/${thisShow.id}" method="post" modelAttribute="rating">
						
								<form:label path="rating">Rating:</form:label>
				            	<form:select path="rating">
					            	<form:option value="5">5</form:option>
					            	<form:option value="4">4</form:option>
					            	<form:option value="3">3</form:option>
					            	<form:option value="2">2</form:option>
					            	<form:option value="1">1</form:option>
				            	</form:select>
					
							<form:input type="hidden" path="user" value="${currentUser.id}"/>
							<form:input type="hidden" path="show" value="${thisShow.id}"/>
						<input type=submit value="Submit" class="btn btn-primary"/>
					</form:form>
			</div>
		</div>
		</div>
	</body>
</html>