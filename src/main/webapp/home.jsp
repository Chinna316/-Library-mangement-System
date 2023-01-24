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
	<title>My Library</title>
</head>
<body >
<div class="row">
	<div class="col-lg-3">
		<div class="text-center">
			<img src="https://th.bing.com/th/id/OIP.nQGaHBhbWOhsiBNR2jniyAHaEo?w=274&h=180&c=7&r=0&o=5&pid=1.7" class="rounded img-fluid" style="width: 300px; height: 300px;" alt="...">
		</div>
	</div>
	
	<div class="col-lg-3">
		<div class="text-center">
			<a href="mybooks" class="btn btn-primary btn-light" id="popMoviesBtn"
			   style="margin-top:10%; color: black;">My Books</a>
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

<ul class="justify-content-md-center nav " id="categories" style="margin-top: 10%;">

</ul>

<div class="row">
	<div class="col-lg-12">
		<form style="margin-top: -10%; margin-left: 35%;" action="books" method="get">
			<div class="form-group">
				<label for="search" class="form-label mt-4">Search Books</label>
				<input type="text" name="query" class="form-control" id="search" placeholder="Search"
				       style="width: 60%;">
			</div>
			<input value="Search" type="submit" href="#" class="btn btn-primary" id="searchBtn"/>
		</form>
	</div>
</div>
<script>
   // const categoryDom = document.getElementById("categories")
   // const categories = ${categories}
       /* categories.forEach(category => {
            const holder = document.createElement("li")
            holder.className = "nav-item"
            const a = document.createElement("a")
            a.href = "books?category=" + category
            a.innerText = category
	        a.className = "nav-link"
            holder.appendChild(a)
            categoryDom.appendChild(holder)
        }) */
</script>

<!--<a href="books?category=Physics" class="col-sm">
	<div class="card ">
		<div class="card-body">
			<h5 class="card-title" style="margin: auto; text-align: center;">Physics</h5>
		</div>
	</div>
</a-->>

</body>
</html>
