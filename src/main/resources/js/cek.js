document.addEventListener('DOMContentLoaded', function() {
    initializeChart();
	var clusterNames = ["Cluster 1", "Cluster 2", "Cluster 3"];
	console.log(clusterNames);
	var totalSales = [10, 15, 8];
	var canvas = document.getElementById('barChart');
	var ctx = canvas.getContext('2d');
	var barChart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: clusterNames,
			datasets: [{
				label: 'Total Sales',
				data: totalSales,
				backgroundColor: 'rgba(255, 99, 132, 0.5)',
				borderColor: 'rgba(255, 99, 132, 1)',
				borderWidth: 1
			}]
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
});
