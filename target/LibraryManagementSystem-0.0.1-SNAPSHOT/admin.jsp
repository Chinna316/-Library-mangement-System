<%--
  Created by IntelliJ IDEA.
  Date: 11/05/2022
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
			<a href="publisher" class="btn btn-primary btn-light" id="publisherBtn"
			   style="margin-top:10%; color: black;">Add Publisher</a>
		</div>
	</div>
	
	<div class="col-lg-3">
		<div class="text-center">
			<a href="author" class="btn btn-primary btn-light" id="authorBtn"
			   style="margin-top:10%; color: black;">Add Author</a>
		</div>
	</div>
	
	<div class="col-lg-3">
		<div class="text-center">
			<a href="category" class="btn btn-primary btn-light" id="categoryBtn"
			   style="margin-top:10%; color: black;">Add Category</a>
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
	<form method="post" action="books">
		<div class="form-group row">
			<label for="isbn" class="col-sm-2 col-form-label">ISBN</label>
			<div class="col-sm-10">
				<input name="isbn" required type="text" class="form-control" id="isbn" placeholder="12343212">
			</div>
		</div>
		<div class="form-group row">
			<label for="title" class="col-sm-2 col-form-label">Title</label>
			<div class="col-sm-10">
				<input name="title" required type="text" class="form-control" id="title" placeholder="My Book">
			</div>
		</div>
		<div class="form-group row">
			<label for="stock" class="col-sm-2 col-form-label">Stock</label>
			<div class="col-sm-10">
				<input name="stock" required type="number" class="form-control" id="stock" placeholder="1">
			</div>
		</div>
		<div class="form-group row">
			<label for="author" class="col-sm-2 col-form-label">Authors</label>
			<div class="col-sm-10">
				<select name="author" required class="form-control" id="author">
				
				</select>
			</div>
		</div>
		
		
		<div class="form-group row">
			<label for="edition" class="col-sm-2 col-form-label">Edition</label>
			<div class="col-sm-10">
				<input name="edition" required type="text" class="form-control" id="edition" placeholder="1">
			</div>
		</div>
		
		<div class="form-group row">
			<label for="publisher" class="col-sm-2 col-form-label">Publisher</label>
			<div class="col-sm-10">
				<select name="publisher" required type="text" class="form-control" id="publisher"
				        placeholder="Publisher">
				</select>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="category" class="col-sm-2 col-form-label">Category</label>
			<div class="col-sm-10">
				<select name="category" required class="form-control" id="category">
				</select>
			</div>
		</div>
		<button style="margin: auto" class="btn btn-primary" type="submit">Add</button>
	</form>
</div>
</body>
<script>
    const publishers = ${publishers}
    const authors = ${authors}
    const categories = ${categories}
    
    const dom = document.getElementById("publisher")
    publishers.forEach(publisher => {
        const option = document.createElement("option")
        option.setAttribute("value", publisher.id)
        option.innerText = publisher.name;
        dom.appendChild(option)
    })

    const authorsDom = document.getElementById("author")
    authors.forEach(author => {
        const option = document.createElement("option")
        option.setAttribute("value", author.id)
        option.innerText = author.name;
        authorsDom.appendChild(option)
    })
    
    const categoryDom = document.getElementById("category")
    categories.forEach(category => {
        const option = document.createElement("option")
        option.setAttribute("value", category)
        option.innerText = category;
        categoryDom.appendChild(option)
    })
    
</script>
</html>
