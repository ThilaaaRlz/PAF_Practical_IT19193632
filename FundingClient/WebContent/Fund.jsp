<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Fund.js"></script>

<meta charset="ISO-8859-1">
<title>Fund Project Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Fund Project Management</h1>

	<form id="fundmanagement" name="fundmanagement">
		
		 Funder Name:
		<input id="Funders_name" name="Funders_name" type="text" class="form-control form-control-sm"><br> 
		Project Name:
		<input id="Project_name" name="Project_name" type="text" class="form-control form-control-sm"><br>
		 Amount:
		<input id="Amount" name="Amount" type="text" class="form-control form-control-sm"><br>

		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidFund_idSave" name="hidFund_idSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divFundGrid">
	<%
	Fund CartObj = new Fund(); 
	 out.print(CartObj.readfund()); 
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>