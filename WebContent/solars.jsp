<%@page import="model.solars" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Solar Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h1>Solar Management</h1>
				<form id="formItem" name="formItem">
					Account Number: <input id="accountNum"
						name="accountNum" type="text" class="form-control form-control-sm"> <br>
					Full name: <input id="fullName" name="fullName"
						type="text" class="form-control form-control-sm"> <br> NIC number: 
						<input id="userNic" name="userNic"
						type="text" class="form-control form-control-sm"> <br> Address:
					<input id="address" name="address" type="text"
						class="form-control form-control-sm"> <br> Contact Number: <input
						id="contactNum" name="contactNum" type="text"
						class="form-control form-control-sm"> <br>  Email address: <input
						id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> Bank account number: <input
						id="bankAccNum" name="bankAccNum" type="text"
						class="form-control form-control-sm"> <br>  name of the branch: <input
						id="bankBranch" name="bankBranch" type="text"
						class="form-control form-control-sm"> <br> <input id="btnSave"
						name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidSolarIDSave" name="hidSolarIDSave"
						value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
				<%
				solars solarObj = new solars();
 					out.print(solarObj.readSolars());
 					%>
				
	
				</div>
			</div>
		</div>
	</div>
</body>
<script src="Components/jquery.min.js" type="text/javascript"></script>
<script src="Components/solars.js" type="text/javascript"></script>
</html>