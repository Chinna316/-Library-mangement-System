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
	<title>Admin Panel</title>
</head>
<body>
<div class="row">
	<div class="col-lg-3">
		<div class="text-center">
			<img src="./m_logo.png" class="rounded img-fluid" style="width: 100px; height: 100px;" alt="...">
		</div>
	</div>
	
	<div class="col-lg-3">
		<div class="text-center">
			<a href="orders" class="btn btn-primary btn-light" id="popMoviesBtn"
			   style="margin-top:10%; color: black;">Orders</a>
		</div>
	</div>
	
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


<div style="margin: auto; width: 50%; min-width: 400px" class="card p-4">
	<form method="post" action="publisher">
		<div class="form-group row">
			<label for="name" class="col-sm-2 col-form-label">Name</label>
			<div class="col-sm-10">
				<input name="name" required type="text" class="form-control" id="name" placeholder="Producer">
			</div>
		</div>
		<div class="form-group row">
			<label for="address" class="col-sm-2 col-form-label">Address</label>
			<div class="col-sm-10">
				<input name="address" required type="text" class="form-control" id="address" placeholder="Address">
			</div>
		</div>
		<div class="form-group row">
			<label for="email" class="col-sm-2 col-form-label">Email</label>
			<div class="col-sm-10">
				<input name="email" required type="text" class="form-control" id="email" placeholder="Email">
			</div>
		</div>
		<div class="form-group row">
			<label for="mobile" class="col-sm-2 col-form-label">Mobile</label>
			<div class="col-sm-10">
				<input name="mobile" required type="text" class="form-control" id="mobile"
				       placeholder="+1321233233">
			</div>
		</div>
		
		<button style="margin: auto" class="btn btn-primary" type="submit">Add</button>
	</form>
</div>
</body>
</html>
