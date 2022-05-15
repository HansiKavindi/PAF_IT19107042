//Hide the alters on page load
$(document).ready(function()
{

	$("#alertSuccess").hide();

 	$("#alertError").hide();

}); 

$(document).on("click", "#btnSave", function(event)
{
	console.log($("#hidSolarIDSave").val());
	
	// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();

// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidSolarIDSave").val() == "") ? "POST" : "PUT";
console.log(type); 
 $.ajax(
 {
 url : "SolarsAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onSolarSaveComplete(response.responseText, status);
 }
 });
 
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
// Account Num
if ($("#accountNum").val().trim() == "")
 {
 return "Insert Account Num.";
 }
// Account name
if ($("#fullName").val().trim() == "")
 {
 return "Insert full name.";
 }
// Nic  number
if ($("#userNic").val().trim() == "")
 {
 return "Insert NIC number.";
 }

// Address
if ($("#address").val().trim() == "")
 {
 return "Insert Address.";
 }
// Contact  number
if ($("#contactNum").val().trim() == "")
 {
 return "Insert Contact number.";
 }
// is numerical value
var tmpContact = $("#contactNum").val().trim();
if (!$.isNumeric(tmpContact))
 {
 return "Insert a numerical value for Contact number.";
 }
// Email
if ($("#email").val().trim() == "")
 {
 return "Insert email.";
 }
// Bank Account number
if ($("#bankAccNum").val().trim() == "")
 {
 return "Insert Bank account number.";
 }
// Bank branch
if ($("#bankBranch").val().trim() == "")
 {
 return "Insert name of the branch.";
 }
return true;
}

$(document).on("click", ".btnUpdate", function()
{
 $("#hidSolarIDSave").val($(this).closest("tr").find('#hidSolarIDUpdate').val());
 $("#accountNum").val($(this).closest("tr").find('td:eq(1)').text());
 $("#fullName").val($(this).closest("tr").find('td:eq(2)').text());
 $("#userNic").val($(this).closest("tr").find('td:eq(3)').text());
 $("#address").val($(this).closest("tr").find('td:eq(4)').text());
 $("#contactNum").val($(this).closest("tr").find('td:eq(5)').text());
 $("#email").val($(this).closest("tr").find('td:eq(6)').text());
 $("#bankAccNum").val($(this).closest("tr").find('td:eq(7)').text());
 $("#bankBranch").val($(this).closest("tr").find('td:eq(8)').text());
});

function onSolarSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
			
 			$("#divItemsGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 			{
			 	$("#alertError").text(resultSet.data);
 			 	$("#alertError").show();
 			}	
 	} else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 			} 
		
 $("#hidSolarIDSave").val("");
 $("#formItem")[0].reset();
}


$(document).on("click", ".btnRemove", function()
{
	var id = $(this).data("solarid");
	console.log("id is :"+id)
 $.ajax(
 {
 url : "SolarsAPI",
 type : "DELETE",
 data : "solarID=" + id,
 dataType : "text",
 complete : function(response, status)
 {
	console.log(id)
 onSolarDeleteComplete(response.responseText, status);
 }
 });
});

function onSolarDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
	$("#hidItemIDSave").val(""); 
	$("#formItem")[0].reset(); 
}









