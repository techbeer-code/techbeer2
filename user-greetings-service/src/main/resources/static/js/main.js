$(document).ready(function () {	
    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        getGreeting();
    });
});

function getGreeting() {
	$("#result").text('');
	var name = $('#txtName').val();

	if (name) {
	    $("#btnSubmit").prop("disabled", true);
	
	    $.ajax({
	        type: "GET",
	        url: "/user/" + name,
	        processData: false, 
	        contentType: false,
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
	            $("#result").text(data);
	            console.log("SUCCESS: ", data);
	            $("#btnSubmit").prop("disabled", false);
	        },
	        error: function (e) {
	            $("#result").text(e.responseText);
	            console.log("ERROR: ", e);
	            $("#btnSubmit").prop("disabled", false);
	        }
	    });
	}
}

