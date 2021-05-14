
$(document).ready(function()
	 { 
	console.log("tets");
		 $("#alertSuccess").hide(); 
		 $("#alertError").hide(); 
	}); 
	

// SAVE ============================================

$(document).on("click", "#btnSave", function(event){ 
	
	console.log("tets");
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
 	 $("#alertError").text(""); 
 	 $("#alertError").hide();
 	 
 	 
 	 // Form validation-------------------
	var status = validateBuyerForm(); 
	console.log(status);
	if (status != true){ 
 		
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
		
		 return; 
	 } 
	
	// If valid-------------------------
 		
 		
		var type = ($("#hidBuyerIDSave").val() == "") ? "POST" : "PUT"; 
		console.log(type);
 		
		 $.ajax( 
	     { 
				 url : "BuyerAPI", 
 				 type : type, 
                 data : $("#formBuyer").serialize(), 
                 dataType : "text", 
                 complete : function(response, status) 
 				 {
 				 	onBuyerSaveComplete(response.responseText, status); 
 				 } 
 		}); 
  }); 


//Save Button View (Response Algorithm)--------------------------------------------------------------------------------------

function onBuyerSaveComplete(response, status){ 
 
 			if (status == "success"){ 
 	   		 	var resultSet = JSON.parse(response); 
 	   		 	console.log(resultSet);
			 	if (resultSet.status.trim() == "success"){ 
 
 					$("#alertSuccess").text("Successfully saved."); 
 					$("#alertSuccess").show(); 
 					$("#divItemsGrid").html(resultSet.data);
 	 	 	
 	 	 	} 
 	 	 	else if (resultSet.status.trim() == "error"){ 
 	  			
 				$("#alertError").text(resultSet.data); 
 				$("#alertError").show(); 
		    } 
	 
 	    } 
 	    else if (status == "error"){
   	     	
 			    $("#alertError").text("Error while saving."); 
 		        $("#alertError").show(); 
 		} 
 		else{ 
			    $("#alertError").text("Unknown error while saving.."); 
	 	 		$("#alertError").show(); 
 		} 
 		
	    $("#ID").val(""); 
	    $("#formBuyer")[0].reset();
 
   }
 		 

 	 
 	 // UPDATE==========================================
 	 
	$(document).on("click", ".btnupdate", function(event){ 
	
	$("#ID").val($(this).data("itemid"));
 	$("#FullName").var($(this).closest("tr").find('td:eq(0)').text()); 
	$("#PhoneNumber").var($(this).closest("tr").find('td:eq(1)').text()); 
	$("#Email").var($(this).closest("tr").find('td:eq(2)').text()); 
	$("#Address").var($(this).closest("tr").find('td:eq(3)').text());
	$("#Birthdate").var($(this).closest("tr").find('td:eq(4)').text()); 
});



// REMOVE Button handler(Request Algorithm)=======================================================================================

		$(document).on("click", ".btnRemove", function(event)
		{ 
			 $.ajax( 
 		 	{ 
				 url : "BuyerAPI", 
				 type : "DELETE", 
		   		 data : "ID=" + $(this).data("itemid"),
 		 		 dataType : "text", 
			 	complete : function(response, status) 
		 		{
		  			onBuyerDeleteComplete(response.responseText, status); 
 			
 	    		}
 	  
 		}); 
 
	});
	
	
	
	//Remove Button View (Response Algorithm)-----------------------------------------------------------------------------

		function onBuyerDeleteComplete(response, status){
		 
			if (status == "success")
			{ 
		 		var resultSet = JSON.parse(response); 
		 		
 				if (resultSet.status.trim() == "success")
 				{ 
		 			 $("#alertSuccess").text("Successfully deleted."); 
	 		 		 $("#alertSuccess").show(); 
 					 $("#divItemsGrid").html(resultSet.data); 
 	
	  			}else if (resultSet.status.trim() == "error")
	  			{ 
 					$("#alertError").text(resultSet.data); 
 					$("#alertError").show(); 
 	 		    } 
 		
	 	   } 
	 	   else if (status == "error"){ 
 	  
 				$("#alertError").text("Error while deleting."); 
 				$("#alertError").show(); 
 	  	   } 
 	  	   else{ 
 				$("#alertError").text("Unknown error while deleting.."); 
 				$("#alertError").show(); 
 	   	   }
 	  
	}


 	 
 	 
 	 // CLIENT-MODEL================================================================
 	 

 	 function validateBuyerForm(){ 
 		 
 		if ($("#FullName").val().trim() == "") 
 		 { 
 		 return "Full Name Required!"; 
 		 }

	// name
	if ($("#FullName").val().trim() == ""){ 
 	return "Insert Full Name."; 
 	}
	// phone number
	if ($("#PhoneNumber").val().trim() == ""){ 
 	return "Insert Phone Number."; 
 	}
 	 
	// email
	if ($("#Email").val().trim() == ""){ 
 	return "Insert Email."; 
 	} 

	// address
	if ($("#Address").val().trim() == ""){ 
 	return "Insert Address."; 
 	}
 	
 	// Birthday
	if ($("#Birthdate").val().trim() == ""){ 
 	return "Insert Birth date."; 
 	}
	
	return true;
}
 		 
 	
 	 