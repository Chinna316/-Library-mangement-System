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

<div class="row justify-content-md-center" id="movies" style="margin-top: 10%;">
</div>
<div>
	<table class="table">
		<thead class="thead-dark">
		<tr>
			<th scope="col">ISBN</th>
			<th scope="col">status</th>
			<th scope="col">Due date</th>
			<th scope="col">Cost</th>
			<th scope="col">IssueDate</th>
			<th scope="col">Options</th>
			<th scope="col">returned Date</th>
		</tr>
		</thead>
		<tbody id="booksTb">
		</tbody>
	</table>
</div>
<script>

    let orders = ${orders}
        console.log(orders)
    let user = ${user}
        console.log(user)
    const booksTb = document.getElementById("booksTb")
    orders.forEach(order => {
        console.log(order)
        let tr = document.createElement("tr")
        let isbnTd = document.createElement("td")
        isbnTd.innerText = order.bookISBN
        let statusTd = document.createElement("td")
        statusTd.innerText = order.status
        let retDateTd = document.createElement("td")
        retDateTd.innerText = order.retDate

        let duesTd = document.createElement("td")
        duesTd.innerText = order.cost

		let retOnTd = document.createElement("td")
		retOnTd.innerText = order.retOn

	    
	    
	    const optionsTd = document.createElement("td")

        let viewTd = document.createElement("a")
        viewTd.classList.add("mx-2")
        viewTd.innerText = "View"
        viewTd.classList.add("btn")
        viewTd.classList.add("btn-primary")
        viewTd.href = "books?isbn=" + order.bookISBN
        optionsTd.appendChild(viewTd)

        let issueTd = document.createElement("td")
        issueTd.innerText = order.issueDate
	    
	    

		if (order.status === "pending"){
            let declineTD = document.createElement("a")
            declineTD.classList.add("mx-2")
            declineTD.innerText = "Decline"
            declineTD.classList.add("btn")
            declineTD.classList.add("btn-primary")
            declineTD.href = "orders?method=post&status=declined&id=" + order.id
            optionsTd.appendChild(declineTD)

            let acceptTD = document.createElement("a")
            acceptTD.classList.add("mx-2")
            acceptTD.innerText = "Accept"
            acceptTD.classList.add("btn")
            acceptTD.classList.add("btn-primary")
            acceptTD.href = "orders?method=post&status=accepted&id=" + order.id
            optionsTd.appendChild(acceptTD)
        }


	    
        tr.appendChild(isbnTd)
        tr.appendChild(statusTd)
        tr.appendChild(retDateTd)
	    tr.append(duesTd)
        tr.appendChild(issueTd)
        tr.appendChild(optionsTd)
        booksTb.append(tr)

		tr.appendChild(retOnTd)
		booksTb.append(tr)
    })
</script>

</body>
</html>
