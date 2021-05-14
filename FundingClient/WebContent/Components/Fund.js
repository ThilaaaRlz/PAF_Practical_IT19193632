$(document).on("click", "#btnSave", function(event){ 
	
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
 
	 
	// Form validation-------------------
	var status = validateFundForm(); 
	if (status != true) 
	 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 
 return; 
} 


// If valid------------------------
var type = ($("#hidFund_idSave").val() == "") ? "POST" : "PUT"; 
	$.ajax( 
	{ 
	 url : "FundAPI", 
	 type : type, 
	 data : $("#fundmanagement").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) { 
		 
		 onFundSaveComplete(response.responseText, status); 
	 } 
	}); 
});

function onFundSaveComplete(response, status){ 
	if (status == "success") {
		
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divFundGrid").html(resultSet.data); 
		 } 
		 else if (resultSet.status.trim() == "error") {
			 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	} 
	else if (status == "error") { 
		
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	} else{ 
		
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
		}
	$("#hidFund_idSave").val(""); 
	$("#fundmanagement")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event){ 
		
		 $("#hidFund_idSave").val($(this).data("Fund_id")); 
		 $("#Funders_name").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#Project_name").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#Amount").val($(this).closest("tr").find('td:eq(2)').text()); 
		// $("#").val($(this).closest("tr").find('td:eq(8)').text()); 
		 
});





$(document).on("click", ".btnRemove", function(event) { 
	 $.ajax( 
	 { 
	 	url : "FundAPI", 
	 	type : "DELETE", 
	 	data : "Fund_id=" + $(this).data("Fund_id"),
	 	dataType : "text", 
	 	complete : function(response, status) { 
	 		onFundDeleteComplete(response.responseText, status); 
	 	} 
	}); 
})
	


function onFundDeleteComplete(response, status){
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response); 
			if (resultSet.status.trim() == "success"){
			
				$("#alertSuccess").text("Successfully deleted."); 
				$("#alertSuccess").show(); 
				$("#divItemsGrid").html(resultSet.data); 
				
			} else if (resultSet.status.trim() == "error") { 
				
				$("#alertError").text(resultSet.data); 
				$("#alertError").show(); 
		} 
	} 
	else if (status == "error") { 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	} 
	else { 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	} 
}

// CLIENT-MODEL================================================================
function validateItemForm(){
	// CODE

	
// PRICE-------------------------------
if ($("#Amount").val().trim() == ""){
	
	return "Insert Fund for project.";
}
// is numerical value
var tmpAmount = $("#Amount").val().trim();
if (!$.isNumeric(tmpAmount)){
			
	return "Insert a numerical value for Amount.";
}
		
// convert to decimal price
$("#Amount").val(parseFloat(tmpAmount).toFixed(2));


	return true;
}