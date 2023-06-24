const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

allSideMenu.forEach(item=> {
	const li = item.parentElement;

	item.addEventListener('click', function () {
		allSideMenu.forEach(i=> {
			i.parentElement.classList.remove('active');
		})
		li.classList.add('active');
	})
});




// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
	sidebar.classList.toggle('hide');
})









if(window.innerWidth < 768) {
	sidebar.classList.add('hide');
} else if(window.innerWidth > 576) {
	searchButtonIcon.classList.replace('bx-x', 'bx-search');
	searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
	if(this.innerWidth > 576) {
		searchButtonIcon.classList.replace('bx-x', 'bx-search');
		searchForm.classList.remove('show');
	}
})





function loadContent(page) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			document.getElementById("dynamic-content").innerHTML = this.responseText;
			initializeChart();
		}
	};
	xhttp.open("GET", "/"+ page, true);
	xhttp.send();
}





// JAVASCRIPT USERS.HTML
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


// JAVASCRIPT ITEM.HTML
function loadUpdateFormItem(button) {
	var itemId = button.getAttribute("data-item-id");
	var name = button.getAttribute("data-item-name");
	var quantity = button.getAttribute("data-item-quantity");
	var amount = button.getAttribute("data-item-amount");
	var modal = button.getAttribute("data-item-modal");
	var salary = button.getAttribute("data-item-salary");

	document.getElementById("itemId").value = itemId;
	document.getElementById("name").value = name;
	document.getElementById("quantity").value = quantity;
	document.getElementById("amount").value = amount;
	document.getElementById("modal").value = modal;
	document.getElementById("salary").value = salary;

	var updateForm = document.getElementById("updateForm");
    updateForm.style.display = "block";
    updateForm.scrollIntoView({ behavior: 'smooth' });
	document.getElementById("btnNew").style.display = "none";
}

function updateItem() {
	var itemId = document.getElementById("itemId").value;
    var name = document.getElementById("name").value;
    var quantity = document.getElementById("quantity").value;
    var amount = document.getElementById("amount").value;
	var modal = document.getElementById("modal").value;
	var salary = document.getElementById("salary").value;

    // Send an AJAX request to update the user
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/items/" + itemId + "/update", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.reload();
        }
    };
    var params = "id=" + itemId + "&name=" + encodeURIComponent(name) + "&quantity=" + encodeURIComponent(quantity) + "&amount=" + encodeURIComponent(amount) + "&modal=" + encodeURIComponent(modal)+ "&salary=" + encodeURIComponent(salary) ;
    xhr.send(params);

    // Hide the update form
    document.getElementById("updateForm").style.display = "none";
	
}

function cancelUpdateItem() {
	document.getElementById("itemId").value = "";
	document.getElementById("name").value = "";
	document.getElementById("quantity").value = "";
	document.getElementById("amount").value = "";
	document.getElementById("modal").value = "";
	document.getElementById("salary").value="";
	document.getElementById("updateForm").style.display = "none";
	document.getElementById("btnNew").style.display = "";
}

function confirmDeleteItem(button) {
	var userId = button.getAttribute("data-item-id");
	var result = confirm("Are you sure you want to delete this Items?");
	if (result) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				window.location.reload();
			}
		};
		xhttp.open("GET", '/items/' + userId + '/delete', true);
		xhttp.send();
	}
}

function confirmNew() {
    var inputForm = document.getElementById("inputForm");
	var btnNew = document.getElementById("btnNew");
    inputForm.style.display = "block";
	btnNew.style.display="none";
}

function cancelSaveItem() {
    var inputForm = document.getElementById("inputForm");
	var btnNew = document.getElementById("btnNew");
    inputForm.style.display = "none";
	btnNew.style.display="";
}

function initializeChart() {
	var canvas = document.getElementById('lineChart');
	var ctx = canvas.getContext('2d');
	var avgView0 = document.getElementById("view0").textContent;
	var avgView1 = document.getElementById("view1").textContent;
	var avgView2 = document.getElementById("view2").textContent;
	var avgView3 = document.getElementById("view3").textContent;

	var avgSales0 = document.getElementById("sales0").textContent;
	var avgSales1 = document.getElementById("sales1").textContent;
	var avgSales2 = document.getElementById("sales2").textContent;
	var avgSales3 = document.getElementById("sales3").textContent;

	var avgSalary0 = document.getElementById("salary0").textContent /5000000;
	var avgSalary1 = document.getElementById("salary1").textContent/5000000;
	var avgSalary2 = document.getElementById("salary2").textContent/5000000;
	var avgSalary3 = document.getElementById("salary3").textContent/5000000;

	var avgQuantity0 = document.getElementById("quantity0").textContent;
	var avgQuantity1 = document.getElementById("quantity1").textContent;
	var avgQuantity2 = document.getElementById("quantity2").textContent;
	var avgQuantity3 = document.getElementById("quantity3").textContent;
  
	// Mendefinisikan data dan pengaturan grafik
	var clusterNames = ["Cluster 0", "Cluster 1", "Cluster 2","Cluster 3"];
	var totalView = [avgView0, avgView1, avgView2,avgView3];
	var totalSales = [avgSales0,avgSales1,avgSales2,avgSales3];
	var totalSalary = [avgSalary0,avgSalary1,avgSalary2,avgSalary3];
	var totalQuantity = [avgQuantity0,avgQuantity1,avgQuantity2,avgQuantity3];

	var lineChart = new Chart(ctx, {
	  type: 'line',
	  data: {
		labels: clusterNames,
		datasets: [{
		  label: 'Average View By Cluster',
		  data: totalView,
		  backgroundColor: 'rgba(255, 99, 132, 1)',
		  borderColor: 'rgba(255, 99, 132, 1)',
		  borderWidth: 1
		},
		{
			label: 'Average Sales By Cluster',
			data: totalSales,
			backgroundColor: 'rgba(54, 162, 235, 1)',
			borderColor: 'rgba(54, 162, 235, 1)',
			borderWidth: 1
		  },
		{
			label: 'Average Salary By Cluster (1:5x Juta)',
			data: totalSalary,
			backgroundColor: 'rgba(75, 192, 192, 1)',
			borderColor: 'rgba(75, 192, 192, 1)',
			borderWidth: 1
		  },
		{
			label: 'Average Quantity By Cluster',
			data: totalQuantity,
			backgroundColor: 'rgba(255, 206, 86, 1)',
			borderColor: 'rgba(255, 206, 86, 1)',
			borderWidth: 1
		  }
		]
	  },
	  options: {
		scales: {
		  y: {
			beginAtZero: true,
			stepSize: 1
		  }
		}
	  }
	});


	var table = document.getElementById('data-table4');
	var ctx2 = document.getElementById('lineChart2').getContext('2d');
	var rows = table.getElementsByTagName('tr');
	var labels =[];
	var allView = [];
	var allQuantity = [];
	var allSales = [];
	var allSalary = [];

	for (var i = 1; i < rows.length; i++) {
		var cells = rows[i].getElementsByTagName('td');
		labels.push(cells[0].textContent);
		allView.push(cells[1].textContent);
		allQuantity.push(cells[2].textContent);
		allSales.push(cells[3].textContent);
		allSalary.push(cells[4].textContent/100000);
	}

	var lineChart2 = new Chart(ctx2, {
		type: 'line',
		data: {
			labels: labels,
			datasets: [
				{
					label: 'View By Cluster 4',
					data: allView,
					backgroundColor: 'rgba(255, 99, 132, 10)',
					borderColor: 'rgba(255, 255, 255, 1)',
					borderWidth: 1
				},
				{
					label: 'Quantity By Cluster 4',
					data: allQuantity,
					backgroundColor: 'rgba(54, 162, 235, 1)',
					borderColor: 'rgba(54, 162, 235, 1)',
					borderWidth: 1
				},
				{
					label: 'Sales By Cluster 4',
					data: allSales,
					backgroundColor: 'rgba(75, 192, 192, 1)',
					borderColor: 'rgba(75, 192, 192, 1)',
					borderWidth: 1
				},
				{
					label: 'Salary By Cluster 4',
					data: allSalary,
					backgroundColor: 'rgba(255, 206, 86, 1)',
					borderColor: 'rgba(255, 206, 86, 1)',
					borderWidth: 1
				}
				
			]
		},
		options: {
			scales: {
				y: {
					beginAtZero: true,
					stepSize: 1
				}
			}
		}
	});
	
  
	lineChart.draw();
	lineChart2.draw();
  }
  