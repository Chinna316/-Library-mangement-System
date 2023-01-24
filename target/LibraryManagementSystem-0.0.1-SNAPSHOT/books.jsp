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
				<a href="orders" class="btn btn-primary btn-light" id="orderControllers"
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
	<table class="table">
		<thead class="thead-dark">
		<tr>
			<th scope="col">ISBN</th>
			<th scope="col">title</th>
			<th scope="col">stock</th>
			<th scope="col">authors</th>
			<th scope="col">editors</th>
			<th scope="col">publisher</th>
			<th scope="col">category</th>
			<th scope="col"></th>
		</tr>
		</thead>
		<tbody id="booksTb">
		</tbody>
	</table>
</div>
<script>
    let books = ${books}
    console.log(books)
    let user =  ${user}
	console.log(user)
    const booksTb = document.getElementById("booksTb")
    books.forEach(book => {
        console.log(book)
        let tr = document.createElement("tr")
        let isbnTd = document.createElement("td")
        isbnTd.innerText = book.isbn
        let titleTd = document.createElement("td")
        titleTd.innerText = book.title
        let stockTd = document.createElement("td")
        stockTd.innerText = book.stock
        let authorsTd = document.createElement("td")
        authorsTd.innerText = book.author.name
        let editorsTd = document.createElement("td")
        editorsTd.innerText = book.edition
        let publisherTd = document.createElement("td")
        publisherTd.innerText = book.publisher.name
        let categoryTd = document.createElement("td")
        categoryTd.innerText = book.category
        let optionsTd = document.createElement("td")
	    console.log(user.role==="librarian")
	    console.log(user.role, "librarian")
        if (user.role==="librarian"){
            let button = document.createElement("a")
            button.classList.add("mx-2")
            button.innerText = "Edit"
            button.classList.add("btn")
            button.classList.add("btn-primary")
            button.href = "modify?request=edit&isbn=" + book.isbn
            optionsTd.appendChild(button)
            let deleteBtn = document.createElement("a")
            deleteBtn.classList.add("mx-2")
            deleteBtn.innerText = "Delete"
            deleteBtn.classList.add("btn")
            deleteBtn.classList.add("btn-primary")
            deleteBtn.href = "modify?request=delete&isbn=" + book.isbn
            optionsTd.appendChild(deleteBtn)
        }else{
            if (book.stock < 1) {
                optionsTd.innerText = "Out of stock"
            } else {
                let button = document.createElement("a")
                button.innerText = "Lend"
                button.classList.add("btn")
                button.classList.add("btn-primary")
                button.href = "lend?isbn=" + book.isbn
                optionsTd.appendChild(button)
            }
        }
        tr.appendChild(isbnTd)
        tr.appendChild(titleTd)
        tr.appendChild(stockTd)
        tr.appendChild(authorsTd)
        tr.appendChild(editorsTd)
        tr.appendChild(publisherTd)
        tr.appendChild(categoryTd)
        tr.appendChild(optionsTd)
        booksTb.append(tr)
    })

</script>

</body>
</html>
