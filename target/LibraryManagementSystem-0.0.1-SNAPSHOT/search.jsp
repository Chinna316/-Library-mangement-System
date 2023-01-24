<%--
  Created by IntelliJ IDEA.
  User: shami
  Date: 01/05/2022
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
	
	<title>Mflex</title>
</head>


<body>
<div class="row">
	<div class="col-lg-6">
		<div>
			<img src="./m_logo.png" class="rounded img-fluid" style="width: 100px; height: 100px;" alt="...">
		</div>
	</div>
	<div class="col-lg-6">
		<div class="text-center">
			<a href="#" class="btn btn-primary btn-light" id="signOutBtn" style="margin-top:10%; color: red;">SIGN
				OUT</a>
		</div>
	</div>
</div>


<div class="row" style="margin-top: 5%; margin-left: 10%; margin-right: 20px;">
	<legend id="movieName">Your search results</legend>
	
	<div class="col-lg-12">
		<table class="table">
			<thead>
			<tr>
				<th scope="col">Title</th>
				<th scope="col">Release Year</th>
				<th scope="col">IMDB Rating</th>
				<th scope="col">Directors</th>
			</tr>
			</thead>
			<tbody id="movies">
			
			</tbody>
		</table>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<a href="#" class="btn btn-primary" id="backToMainPageBtn" style="margin-top:1%; margin-left: 10%;">Back to Main
			Page</a>
	</div>
</div>

<script>
    const movies = ${movies};
    const moviesField = document.getElementById("movies")
    movies.forEach(movie => {
        const tr = document.createElement("tr")
        const title = document.createElement("td")
        const titleRef = document.createElement("a")
        titleRef.innerText = movie.title
        titleRef.href = "details?id=" + movie.id
        title.appendChild(titleRef)
        const year = document.createElement("td")
        year.innerText = movie.year
        const rating = document.createElement("td")
        rating.innerText = movie.imdb.rating
        const director = document.createElement("td")
        director.innerText = movie.directors[0]

        tr.appendChild(title)
        tr.appendChild(year)
        tr.appendChild(rating)
        tr.appendChild(director)
        moviesField.appendChild(tr)
    })


</script>

</body>

</html>
