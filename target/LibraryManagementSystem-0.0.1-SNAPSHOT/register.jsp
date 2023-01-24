<%--
  Created by IntelliJ IDEA.
  User: shami
  Date: 30/04/2022
  Time: 8:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
	<title>Register</title>
</head>

<body>
<div class="row">
	<div class="col-lg-6">
		<div class="text-center">
			<img src="./m_logo.png" style="max-width: 100%" class="rounded" alt="...">
		</div>
	</div>
	
	<div class="col-lg-6">
		<form style="margin-top: 20%;" method="post" action="register">
			<div class="form-group">
				<label for="name" class="form-label mt-4">Name</label>
				<input type="text" class="form-control" name="name" id="name" placeholder="Enter Name" style="width: 50%;">
			</div>
			<div class="form-group">
				<label for="username" class="form-label mt-4">Username</label>
				<input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" style="width: 50%;">
			</div>
			<div class="form-group">
				<label for="phone" class="form-label mt-4">Phone</label>
				<input type="text" class="form-control" id="phone" name="phone" placeholder="Enter Phone" style="width: 50%;">
			</div>
			<div class="form-group">
				<label for="address" class="form-label mt-4">Address</label>
				<input type="text" class="form-control" id="address" name="address" placeholder="Enter Address" style="width: 50%;">
			</div>
			<div class="form-group">
				<label for="email" class="form-label mt-4">Email</label>
				<input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" style="width: 50%;">
			</div>
			<div class="form-group">
				<label for="password" class="form-label mt-4">Password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" style="width: 50%;">
			</div>
			<input type="submit" class="btn btn-primary" style="margin-top:10%; margin-left: 40%;" value="Register"/>
		</form>
		
	</div>
</div>
</body>
</html>
