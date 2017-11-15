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
					<div class="label label-default pull-right">
						id :
						<c:out value="${computer.id}" default="default value of c:out" />
					</div>
					<h1>Edit Computer</h1>


					<form action="editComputer" method="POST">
						<input type="hidden"
							value="<c:out value="${computer.id}" default="No actual value" />"
							id="id" name="computerid" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>

							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName"
									placeholder="<c:out value="${computer.computerName}" default="No actual value" />"
									value="<c:out value="${computer.computerName}" default=""/> ">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced"
									placeholder="<c:out value="${computer.dateIntroduced}" default="yyyy-MM-dd OR dd/MM/yyyy" />"
									value="<c:out value="${computer.dateIntroduced}" default=""/>" >
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued"
									placeholder="<c:out value="${computer.dateDiscontinued}" default="yyyy-MM-dd OR dd/MM/yyyy" />"
									value="<c:out value="${computer.dateDiscontinued}" default=""/>">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach items="${listcompanies}" var="company">
									
										<c:if test="${company.id == computer.companyId }"><option selected value="${company.id}">${company.name}</option></c:if>
										<c:if test="${company.id != computer.companyId }"><option value="${company.id}">${company.name}</option></c:if>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>