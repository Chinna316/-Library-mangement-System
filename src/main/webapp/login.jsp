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
	<title>Login</title>
</head>

<body>
<div class="row">
	<div class="col-lg-6">
		<div class="text-center">
			<img src="./m_logo.png" style="max-width: 100%" class="rounded" alt="..." >
		</div>
	</div>
	
	<div class="col-lg-6">
		<form style="margin-top: 20%;" method="post" action="login">
			<div class="form-group">
				<label for="username" class="form-label mt-4">Email</label>
				<input type="email" name="email" class="form-control" id="username" placeholder="Enter Email" style="width: 50%;">
			</div>
			<div class="form-group">
				<label for="password" class="form-label mt-4">Password</label>
				<input type="password" name="password" class="form-control" id="password" placeholder="Enter Password" style="width: 50%;">
			</div>
			<input type="submit" class="btn btn-primary" style="margin-top:10%; margin-left: 40%;" value="Login"/>
		</form>
		<p class="text-center">Don't have an account? <a href="register">Register</a></p>
	</div>
</div>
</body>
</html>
