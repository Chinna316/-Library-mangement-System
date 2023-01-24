<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%--
  Created by IntelliJ IDEA.
  Date: 01/05/2022
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
	<title>Books</title>
</head>
<body>
<div class="row">
	<div class="col-lg-3">
		<div class="text-center">
			<img src="./m_logo.png" class="rounded img-fluid" style="width: 100px; height: 100px;" alt="...">
		</div>
	</div>
	
	<c:choose>
		<c:when test="${user.role.equals('librarian')}">
			<div class="col-lg-3">
				<div class="text-center">
					<a href="orderControllers" class="btn btn-primary btn-light" id="orderControllers"
					   style="margin-top:10%; color: black;">Orders</a>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-lg-3">
				<div class="text-center">
					<a href="mybooks" class="btn btn-primary btn-light" id="mybooks"
					   style="margin-top:10%; color: black;">My Books</a>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
	
	<div class="col-lg-3">
		<div class="text-center">
			<a href="books" class="btn btn-primary btn-light" id="theatresBtn"
			   style="margin-top:10%; color: black;">All Books</a>
		</div>
	</div>
	<div class="col-lg-3">
		<div class="text-center">
			<form action="logout" method="get">
				<input type="submit" class="btn btn-primary btn-light" id="signOutBtn"
				       style="margin-top:10%; color: red;" value="SIGN OUT">
			</form>
		</div>
	</div>
</div>

<div class="row justify-content-md-center" id="movies" style="margin-top: 10%;">
</div>
<div>
	<form method="post" action="lend" style="width: 50%; margin: auto">
		<div class="form-group row">
			<label for="ISBN" class="col-sm-2 col-form-label">ISBN</label>
			<div class="col-sm-10">
				<input name="isbn" type="text" readonly class="form-control" id="ISBN" value=${book.isbn}>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="title" class="col-sm-2 col-form-label">Title</label>
			<div class="col-sm-10">
				<input name="title" type="text" disabled class="form-control" id="title" value=${book.title}>
			</div>
		</div>
		
		
		<div class="form-group row">
			<label for="stock" class="col-sm-2 col-form-label">Stock</label>
			<div class="col-sm-10">
				<input name="stock" type="text" disabled class="form-control" id="stock" value=${book.stock}>
			</div>
		</div>
		
		
		<div class="form-group row">
			<label for="authors" class="col-sm-2 col-form-label">Authors</label>
			<div class="col-sm-10">
				<input name="title" type="text" disabled class="form-control" id="authors" value=${book.author.name}>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="price" class="col-sm-2 col-form-label">Cost</label>
			<div class="col-sm-10">
				<input name="price" type="text" disabled class="form-control" id="price" value="$10/day">
			</div>
		</div>
		
		
		<div class="form-group row">
			<label for="days" class="col-sm-2 col-form-label">Due Date</label>
			<div class="col-sm-10">
				<input required name="days" type="date" class="form-control" id="days">
			</div>
		</div>
		
		
		<c:choose>
			<c:when test="${book.stock > 0}">
				<button type="post" class="btn btn-primary">Lend book</button>
			</c:when>
			<c:otherwise>
				<button disabled type="post" class="btn btn-primary"> Out of stock</button>
			</c:otherwise>
		</c:choose>
	</form>
</div>
<script>

    let today = new Date();
    let days = document.getElementById("days")
    days.setAttribute("min", today.toString());

    let book = ${book}
        console.log(orders)
    let user = ${user}
        console.log(user)


</script>

</body>
</html>
