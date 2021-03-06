<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				Nous avons actuellement
				<c:out value="${size}" default="default value of c:out" />
				ordinateurs dans la base de donn�es
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="POST" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" name="actionType" /> <input type="submit"
							id="searchsubmitCompany" value="Filter by company"
							class="btn btn-primary" name="actionType" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a> <a
						class="btn btn-warning" id="deleteCompany" href="deleteCompany">delete
						company</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="deleteSelected" method="POST">
			<input type="hidden" name="selection" value=""> 
		</form>
		<c:if test="${deleted !=null}">
			<div
				style="border-style: dashed; border-width: 1px; color: red; width: 30%; margin: auto">
				<c:out value="${deleted}" default="" />
			</div>
		</c:if>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>

						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${listComputer}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.computerId}"></td>
							<td><a href="editComputer?computerid=${computer.computerId}"
								onclick="">${computer.computerName}</a></td>
							<!-- Variable declarations for passing labels as parameters -->
							<!-- Table header for Computer Name -->
							<td>${computer.dateIntroduced}</td>
							<!-- Table header for Discontinued Date -->
							<td>${computer.dateDiscontinued}</td>
							<!-- Table header for Company -->
							<td>${computer.companyName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">

			<a class="btn-group btn-group-sm pull-left" type="button"
				href="?restart=ok" value="ok" class="btn btn-default"> Restart!
			</a>
			<ul class="pagination">
				<li><a href="?pageoperation=-1" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<c:if test="${page-1>=0}">
					<li><a href="?page=${page-1}"><c:out value="${page}"
								default="" /></a></li>
				</c:if>
				<li><a style="font-size: 20px" href="?page=${page}"><c:out
							value="${page+1}" default="" /></a></li>
				<c:if test="${nextPage>=1}">
					<li><a href="?page=${page+1}"><c:out value="${page+2}"
								default="" /></a></li>
				</c:if>
				<c:if test="${nextPage==2}">
					<li><a href="?page=${page+2}"><c:out value="${page+3}"
								default="" /></a></li>
				</c:if>

				<li>
				<li><a href="?pageoperation=1" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
			<div class="btn-group btn-group-sm pull-right" role="group">

				<a type="button" href="?numberdisplay=10" value="10"
					class="btn btn-default"> 10</a> <a type="button"
					href="?numberdisplay=50" name="button" value="50"
					class="btn btn-default">50</a> <a type="button"
					href="?numberdisplay=100" name="button" value="100"
					class="btn btn-default">100</a>

			</div>
	</footer>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>

</body>
</html>