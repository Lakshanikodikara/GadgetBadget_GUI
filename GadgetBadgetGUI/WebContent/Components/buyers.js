$(document).ready(function() {

	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateBuyerForm();
	
	// If not valid
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidBuyerIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "BuyersAPI",
		type : type,
		data : $("#formBuyer").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onBuyerSaveComplete(response.responseText, status);
		}
	});

});

function onBuyerSaveComplete(response, status) {
	$("#alertSuccess").text(+status );
	$("#alertSuccess").show();
	if (status == "success") {
	var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved." );
			$("#alertSuccess").show();
			$("#colBuyers").html(resultSet.data);
		} else {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidBuyerIDSave").val("");
	$("#formBuyer")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event) {
	$("#hidBuyerIDSave").val($(this).data("buyerid"));
	$("#txtName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#rdoGender").val($(this).closest("tr").find('td:eq(1)').text());
	$("#txtContact").val($(this).closest("tr").find('td:eq(2)').text());
	$("#txtEmail").val($(this).closest("tr").find('td:eq(3)').text());
	$("#txtAddress").val($(this).closest("tr").find('td:eq(4)').text());
});

function validateBuyerForm() {
	if ($("#txtName").val().trim() == "") {
		return "Insert student name.";
	}
	if ($('input[name="rdoGender"]:checked').length === 0) {
		return "Select gender.";
	}
	if ($("#txtContact").val().trim() == "") {
		return "Enter contact number";
	}
	if ($("#txtEmail").val().trim() == "") {
		return "Enter email address";
	}
	if ($("#txtAddress").val().trim() == "") {
		return "Enter  address";
	}
	return true;
}

function getBuyerCard(name, gender, contact) {
	var title = (gender == "Male") ? "Mr." : "Ms.";
	var buyer = "";
	buyer += "<div class=\"buyer card bg-light m-2\" style=\"max-width: 10rem; float: left;\">";
	buyer += "<div class=\"card-body\">";
	buyer += title + " " + name + ", <br>";
	buyer += "Contact " + contact + "";
	buyer += "</div>";
	buyer += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">";
	buyer += "</div>";
	return buyer;
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "BuyersAPI",
		type : "DELETE",
		data : "buyerID=" + $(this).data("buyerid"),
		dataType : "text",
		complete : function(response, status) {
			onBuyerDeleteComplete(response.responseText, status);
		}
	});
});

function onBuyerDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#colBuyers").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}
