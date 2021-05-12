<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.sliit.model.Buyer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gadget Badget</title>



<link rel="stylesheet" href="Views/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

<!-- <script src="Components/jquery-3.2.1.min.js"></script>
 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript"
	src="Components/buyers.js"></script>


</head>

<body>

	<div class="container container-fluid" >
		<div class="row" style="margin-top: 50px ;">
			
			<h1 class="text-center font-weight-bold" style="text-align:center">Buyers details</h1>

			<hr>

		</div>
		<div class="row" style="margin-top: 50px">

			<div class="col-4">

				<form class="row g-3" id="formBuyer" name="formBuyer">


					<!-- NAME -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Buyer Name: </span>
						</div>
						<input class="form-control" type="text" id="txtName" name="txtName">
					</div>

					<!-- GENDER -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Gender: </span>
						</div>
						&nbsp;&nbsp;Male <input type="radio" id="rdoGenderMale"
							name="rdoGender" value="Male"> &nbsp;&nbsp;Female <input 
							type="radio" id="rdoGenderFemale" name="rdoGender" value="Female">
					</div>

					<!-- CONTACT -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Contact Number
								: </span>
						</div>
						<input class="form-control" type="text" id="txtContact" name="txtContact">
					</div>

					<!-- EMAIL -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Email Address
								: </span>
						</div>
						<input class="form-control" type="text" id="txtEmail" name="txtEmail">
					</div>

					<!-- ADDRESS -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Address : </span>
						</div>
						<input  class="form-control" type="text" id="txtAddress" name="txtAddress">
					</div>

					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>

					<input type="button" id="btnSave" value="Save"
						class="btn btn-primary" > <input type="hidden"
						id="hidBuyerIDSave" name="hidBuyerIDSave" value="">


				</form>


			</div>
			<div class="col-8">

				<div id=colBuyers>

					<%
						Buyer buyerObj = new Buyer();
						out.print(buyerObj.readBuyers());
					%>

				</div>

			</div>
		</div>

		<br>

	</div>

</body>

</html>