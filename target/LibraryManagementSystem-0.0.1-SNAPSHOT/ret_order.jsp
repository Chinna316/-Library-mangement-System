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

<style>

    body {
        margin-top: 20px;
    }

    .panel-title {
        display: inline;
        font-weight: bold;
    }

    .checkbox.pull-right {
        margin: 0;
    }

    .pl-ziro {
        padding-left: 0px;
    }

</style>

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

<h1 style="text-align: center">Return Book</h1>
<div>
	
	
	<div style="margin: auto; width: 50%; min-width: 400px" class="card p-4">
		<form method="post" action="ret_book">
			<input name="id" hidden value=${order.id}>
			<div class="form-group row">
				<label for="isbn" class="col-sm-2 col-form-label">ISBN</label>
				<div class="col-sm-10">
					<input name="isbn" readonly required type="text" class="form-control" id="isbn" value=${order.bookISBN}>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="cost" class="col-sm-2 col-form-label">Cost</label>
				<div class="col-sm-10">
					<input name="cost" readonly required type="text" class="form-control" id="cost" value=${order.cost}>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								Payment Details</h3>
						</div>
						<div class="panel-body">
							<form role="form">
								<div class="form-group">
									<label for="cardNumber">CARD NUMBER</label>
									<div class="input-group">
										<input name="card_number" type="text" class="form-control" id="cardNumber"
										       placeholder="Valid Card Number"
										       required autofocus/>
									</div>
								</div>
								
								<div class="row">
									<div class="col-xs-7 col-md-7">
										<div class="form-group">
											<label for="expityMonth">EXPIRY DATE</label>
											<div class="col-xs-6 col-lg-6 pl-ziro">
												<input name="expiry_month" type="text" class="form-control" id="expityMonth" placeholder="MM" required/>
											</div>
											<div class="col-xs-6 col-lg-6 pl-ziro">
												<input name="expiry_year" type="text" class="form-control" id="expityYear" placeholder="YY" required/></div>
										</div>
									</div>
									<div class="col-xs-5 col-md-5 pull-right">
										<div class="form-group">
											<label for="cvCode">
												CV CODE</label>
											<input name="cvv" type="password" class="form-control" id="cvCode" placeholder="CV" required/>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<ul class="nav nav-pills nav-stacked">
						<li class="active"><a href="#"><span class="badge pull-right"><span
								class="glyphicon glyphicon-usd" id="totalPayment"></span>0</span> Final Payment</a>
						</li>
					</ul>
					<br/>
					<button type="submit" class="btn btn-success btn-lg btn-block" role="button">Pay</button>
				</div>
			</div>
		</form>
	</div>


</div>
<script>

    let order = ${order}
    console.log(order)
    
    let totalPaymentBox = document.getElementById("totalPayment")
    totalPaymentBox.innerText+=order.cost;
    


</script>

</body>
</html>
