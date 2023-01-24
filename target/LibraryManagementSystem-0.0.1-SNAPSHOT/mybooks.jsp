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
	<table class="table">
		<thead class="thead-dark">
		<tr>
			<th scope="col">ISBN</th>
			<th scope="col">status</th>
			<th scope="col">Due date</th>
			<th scope="col">Cost</th>
			<th scope="col">Issue Date</th>
			<th scope="col">options</th>
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

        let costTd = document.createElement("td")
        costTd.innerText = order.cost
	    
	    let issueTd = document.createElement("td")
	    issueTd.innerText = order.issueDate

		let retOnTd = document.createElement("td")
		retOnTd.innerText = order.retOn


        tr.appendChild(isbnTd)
        tr.appendChild(statusTd)
        tr.appendChild(retDateTd)

        tr.appendChild(costTd)
		tr.appendChild(issueTd)

	    
        if (order.status==="accepted") {
            let optionsTd = document.createElement("td")
            let retAndPayBtn = document.createElement("a")
            if(order.cost > 0){
                retAndPayBtn.innerText = "Return And Pay"
            }else{
                retAndPayBtn.classList.add("mx-2", "btn", "btn-primary")
                retAndPayBtn.innerText = "Return"
            }
            retAndPayBtn.classList.add("mx-2", "btn", "btn-primary")
            retAndPayBtn.href = "ret_book?id="+order.id
            optionsTd.append(retAndPayBtn)
            tr.appendChild(optionsTd)
        }else{
			tr.appendChild(document.createElement("td"))
		}
		tr.appendChild(retOnTd)
        booksTb.append(tr)
    })
</script>

</body>
</html>
