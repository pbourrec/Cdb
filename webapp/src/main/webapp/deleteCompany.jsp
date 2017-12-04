<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
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
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Delete Company</h1>
					<form action="deleteCompany" method="POST">
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyId">
								<c:forEach items="${listcompanies}" var="company">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select>
						</div>
							<div class="actions pull-right">
								<input type="submit" value="Delete !" class="btn btn-primary">
								or <a href="dashboard" class="btn btn-default">Cancel</a>
							</div>
					</form>

					<c:if test="${listComputer !=null}">
						<div class="container" style="width: 104%;margin: auto;margin-left: -15px;">
							<table class="table table-striped table-bordered">

								<thead>
									<tr>
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
											<td>${computer.computerName}</td>
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
						<form action="deleteCompany" method="POST">
							<div class="actions " style="width:50%; margin:auto; align-content:center">
								<p>Are you sure to delete these computer and this company ?</p>
								<input type="submit" value="Delete!" name="confirmation"
									class="btn btn-danger"> or <a href="dashboard"
									class="btn btn-default">Cancel</a>
							</div>
						</form>
					</c:if>

				</div>
			</div>
		</div>
	</section>
	<c:if test="${errorMessage!=null}">
		<div class="alert alert-warning" style="width:40%; margin:auto; align:center">
			<c:out value="${errorMessage}"></c:out>
		</div>
	</c:if>
</body>
</html>