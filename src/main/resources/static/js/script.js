$(document).ready(function() {
    $("#name").val("");
    $("#salary").val("");
    $("#email1").val("");
    $("#password").val("");

    $("#form1").on("submit", function(event) {
        event.preventDefault(); // Prevent default form submission
        ajaxPost();
    });

    function ajaxPost() {
        var formData = {
            name: $("#name").val(),
            salary: $("#salary").val(),
            email: $("#email1").val(),
            password: $("#password").val()
        };

        $.ajax({
            type: "POST",
            url: "/save",
            data: formData,
            success: function(response) {
                alert(response);
                // Clear form fields
                $("#name").val("");
                $("#salary").val("");
                $("#email1").val("");
                $("#password").val("");
                // Fetch updated data after successful addition
                ajaxGet();
            },
            error: function(xhr, status, error) {
                alert("Failed to add employee: " + error);
            }
        });
    }

    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: "/existing_employees",
            dataType: 'json',
            success: function(data) {
                alert("Data retrieved successfully");
                var d = '';

                for (var i = 0; i < data.length; i++) {
                    d += '<tr>' +
                        '<td>' + data[i].id + '</td>' +
                        '<td>' + data[i].name + '</td>' +
                        '<td>' + data[i].salary + '</td>' +
                        '<td>' + data[i].email + '</td>' +
                        '<td>' + data[i].password + '</td>' +
                        '<td><button data-bs-toggle="modal" data-bs-target="#editData" data-employee-id="' + data[i].id + '" id="editBtn-' + data[i].id + '" class="btn btn-info">Edit</button> ' +
                        '<button data-employee-id="' + data[i].id + '" id="deleteBtn-' + data[i].id + '" class="btn btn-danger">Delete</button></td>' +
                        '</tr>';
                }
                $('#table').html(d);
            },
            error: function(e) {
                alert("Failed to retrieve data");
            }
        });
    }

    ajaxGet(); // Load existing employees on page load
});
/* ------------------------------------------------------------------------------------------------------------ */
/*
$(document).ready(function() {
	alert("loading...");

	$("#loginForm").on("submit", function(event) {
		event.preventDefault(); // Prevent default form submission
		ajaxPost();
	});

	function ajaxPost() {
		var formData = {
			email: $("#email").val(),
			password: $("#password").val()
		};

		$.ajax({
			type: "POST",
			url: "/login",
			data: formData,
			success: function(response) {
				alert(response);
				if (response === "Login successful") {
					window.location.href = "/employeeList"; // Redirect to employee list on successful login
				}
			},
			error: function(xhr, status, error) {
				$("#loginAlert").removeClass("d-none").text("Invalid email or password");
			}
		});
	}

	// Function to fetch and display existing employees
	function ajaxGet() {
		$.ajax({
			type: "GET",
			url: "/existing_employees",
			dataType: 'json',
			success: function(data) {
				alert("Data retrieved successfully");
				var d = '';

				for (var i = 0; i < data.length; i++) {
					d += '<tr>' +
						'<td>' + data[i].id + '</td>' +
						'<td>' + data[i].name + '</td>' +
						'<td>' + data[i].salary + '</td>' +
						'<td>' + data[i].email + '</td>' +
						'<td>' + data[i].password + '</td>' +
						'<td><button data-bs-toggle="modal" data-bs-target="#editData" data-employee-id="' + data[i].id + '" id="editBtn-' + data[i].id + '" class="btn btn-info">Edit</button> ' +
						'<button data-employee-id="' + data[i].id + '" id="deleteBtn-' + data[i].id + '" class="btn btn-danger">Delete</button></td>' +
						'</tr>';
				}
				$('#table').html(d);
			},
			error: function(e) {
				alert("Failed to retrieve data");
			}
		});
	}

	ajaxGet(); // Load existing employees on page load
});*/


/* ------------------------------------------------------------------------------------------------------------ */

//edit button


$(document).on('click', '[id^="editBtn-"]', function() {

	alert("Do you want to change data ?")

	let id = $(this).data('employee-id');

	console.log("ID is : " + id);



	$.ajax({

		type: "GET",

		contentType: "application/json",

		url: "/get_employee/" + id,

		dataType: 'json',

		success: function(data) {
			console.log("Data received:",data);
			
			alert("loading");

			if (data) {

				console.log("Employee id is : " + data.id);
				console.log("Employee name is : " + data.name);
				console.log("Employee salary is : " + data.salary);
				console.log("Employee email is : " + data.email);
				console.log("Employee password is : " + data.password);
				


				$("#id").val(data.id);
				$("#name1").val(data.name);
				$("#salary1").val(data.salary);
				$("#email1").val(data.email);
				$("#password1").val(data.password);
				

			}
			console.log(data);

		},

		error: function(e) {

			console.log("Error in feching data for Id....");

		}
	});

});
/* ------------------------------------------------------------------------------------------------------------ */
//update data
    
$("#saveForm").on('click', function(e){

	alert("Updatingg data ...");

	e.preventDefault();

	

	let id = $("#id").val();
	let name = $("#name1").val();
	let salary = $("#salary1").val();
	let email = $("#email1").val();
	let password= $("#password1").val();

	
	let updatedData = {

		id : id,
		name : name,
		salary : salary,
		email : email,
		password : password
	};

	console.log(updatedData);

	

		$.ajax({

				type: "PUT",

				contentType: "application/json",

				url: "/editData",

				data: JSON.stringify(updatedData),

				dataType: 'json',

				success: function (response) {

					alert("Updated Sucessfully...");

					ajaxGet();

				},

				error: function (e) {

					alert("Not Working..");

				}

			});

});



/* ------------------------------------------------------------------------------------------------------------ */
/*$(document).on('click', '[id^="deleteBtn-"]', function() {
    let id = $(this).data('product-id');
    
    if (confirm("Do you want to delete this record?")) {
        $.ajax({
            type: "DELETE",
            url: "deleteData/" + id,
            success: function(response) {
                alert(response);  // This will alert the response message from the server
                console.log(response);

                // Optionally refresh the table or remove the deleted row
                ajaxGet();  // Assuming ajaxGet() is a function that refreshes the table
            },
            error: function(e) {
                alert("Details not deleted...");
                console.log("ERROR: ", e);
            }
        });
    }
});*/

 $(document).on('click', '[id^="deleteBtn-"]', function() {
        let id = $(this).data('employee-id');
        
        if (confirm("Do you want to delete this record?")) {
            $.ajax({
                type: "DELETE",
                url: "/deleteData/" + id,
                success: function(response) {
                    alert(response);
                    console.log(response);
                    ajaxGet(); // Refresh the table
                },
                error: function(e) {
                    alert("Details not deleted: " + e.responseText);
                    console.log("ERROR: ", e);
                }
            });
        }
    });

/*
$(document).on('click', '[id^="deleteBtn-"]', function() {

	

	alert("Do you want to delete record.....");

	let id = $(this).data('product-id');

	console.log("Id is : " + id);

		

		$.ajax({

			type: "DELETE",

			contentType: "application/json",

			url:   "/deleteData/"+id,

			success: function (response){

				alert("Deleted Successfully...");

				ajaxGet();

			},

			error: function(e){

				alert("Details not deleted...");

			}

		});

	

	});

*/


/* ------------------------------------------------------------------------------------------------------------ */
