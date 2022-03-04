<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit TV Show</title>
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
			<h1>Edit TV Show</h1>
			<form:form action="/shows/${show.id}/update" method="post" modelAttribute="show">
				<input type="hidden" name="_method" value="put">
				<div class="form-group">
					<form:label path="title">Title:</form:label>
					<form:errors path="title" class="error"/>
					<form:input path="title" type="text" class="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="network">Network:</form:label>
					<form:errors path="network" class="error"/>
					<form:input path="network" type="text" class="form-control"/>
				</div>
				<div class="form-group">
					<form:label path="description">Description:</form:label>
					<form:errors path="description" class="error"/>
					<form:input path="description" id="floatingTextarea" class="form-control"/>
				</div>
				<div class="mt-3">
					<input type="submit" value="Update" class="btn btn-primary"/>
					<a href="/shows/${show.id}/delete" class="btn btn-info">Delete</a>		
					<a href="/shows" class="btn btn-primary">Cancel</a>
				</div>
			</form:form>
			</div>
		</div>
	</body>
</html>