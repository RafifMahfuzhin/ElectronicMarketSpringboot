function confirmDelete(button) {
	var userEmail = button.getAttribute("data-user-email");
	console.log(userEmail);
	console.log("a");
	var result = confirm("Are you sure you want to delete this user?");
	if (result) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				window.location.reload();
			}
		};
		xhttp.open("GET", '/users/' + userEmail + '/delete', true);
		xhttp.send();
	}
}

function loadUpdateForm(button) {
	var userId = button.getAttribute("data-user-id");
	var firstName = button.getAttribute("data-user-firstname");
	var lastName = button.getAttribute("data-user-lastname");
	var email = button.getAttribute("data-user-email");

	document.getElementById("userId").value = userId;
	document.getElementById("firstName").value = firstName;
	document.getElementById("lastName").value = lastName;
	document.getElementById("email").value = email;

	var updateForm = document.getElementById("updateForm");
    updateForm.style.display = "block";
    updateForm.scrollIntoView({ behavior: 'smooth' });
}

function updateUser() {
	var userId = document.getElementById("userId").value;
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;

    // Get the selected roles
    var rolesSelect = document.getElementById("roles");
    var selectedRoles = [];
	console.log(rolesSelect);
    for (var i = 0; i < rolesSelect.options.length; i++) {
        if (rolesSelect.options[i].selected) {
            selectedRoles.push(rolesSelect.options[i].value);
        }
    }

    // Send an AJAX request to update the user
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/users/" + email + "/update", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.reload();
        }
    };
    var params = "id=" + userId + "&firstName=" + encodeURIComponent(firstName) + "&lastName=" + encodeURIComponent(lastName) + "&roles=" + encodeURIComponent(selectedRoles.join(","));
    xhr.send(params);

    // Hide the update form
    document.getElementById("updateForm").style.display = "none";
}

function cancelUpdate() {
	// Clear the form values
	document.getElementById("userId").value = "";
	document.getElementById("firstName").value = "";
	document.getElementById("lastName").value = "";
	document.getElementById("email").value = "";

	// Hide the update form
	document.getElementById("updateForm").style.display = "none";
}

function confirmDelete(button) {
	var userEmail = button.getAttribute("data-user-email");
	console.log(userEmail);
	console.log("a");
	var result = confirm("Are you sure you want to delete this user?");
	if (result) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				window.location.reload();
			}
		};
		xhttp.open("GET", '/users/' + userEmail + '/delete', true);
		xhttp.send();
	}
}