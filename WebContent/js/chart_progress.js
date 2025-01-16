let chart_data_objectives = [
	{date:'20241028', percent:0}, 
	{date:'20241030', percent:100},
	{date:'20241122', percent:100}
];
let chart_data_records = [
	{date:'20241026', percent:0},
	{date:'20241027', percent:50},
	{date:'20241028', percent:0},
	{date:'20241106', percent:100},
	{date:'20241122', percent:100}
];
const dateLastMonthToday = new Date();
dateLastMonthToday.setMonth(dateLastMonthToday.getMonth() - 1);
const dates_for_chart = new Array(0);  		   // 가로축에 해당하는 날짜('YYYYMMDD' 형식)
const dates_for_chart_display = new Array(0);  // 가로축에 해당하는 날짜 - 출력용('M월D일' 형식)
/* const data2 = [1600,1700,1700,1900,2000,2700,4000,5000,null,7000];
for(let i=0; i<=data2.length-1; i++) {
	if(data2[i] == null) continue;
	data2[i] = data2[i] / 7000 * 100;
} */
/* 		const data3 = [300,700,2000,5000,6000,4000,2000,1000,null,100];
		for(let i=0; i<=data3.length-1; i++) {
			if(data3[i] == null) continue;
			data3[i] = data3[i] / 6000 * 100;
		} */
while(true) {
	dateLastMonthToday.setDate(dateLastMonthToday.getDate() + 1);
	// (OLD) dates_for_chart.push((dateLastMonthToday.getMonth()+1) + "월" + dateLastMonthToday.getDate() + "일");
	// (OLD) dates_for_chart.push(y + "년" + m + "월" + d + "일");
	let y = dateLastMonthToday.getFullYear();
	let m = dateLastMonthToday.getMonth()+1;
	let d = dateLastMonthToday.getDate();
	dates_for_chart_display.push(m + "월" + d + "일");
	if(m<=9) m = '0' + m;
	if(d<=9) d = '0' + d;
	dates_for_chart.push('' + y + m + d);
	
	if(dateLastMonthToday.getYear() == new Date().getYear() &&
		dateLastMonthToday.getMonth() == new Date().getMonth() &&
		dateLastMonthToday.getDate() == new Date().getDate()) {
		break;
	}
}
//alert(dates_for_chart);
//alert(dates_for_chart_display);

let idx_for_chart_data_objectives = 0;
const data3 = [];   // objective(목표) : 회색으로 채워서 표시하는 데이터.
for(let i=0; i<=dates_for_chart.length-1; i++) {
	if(idx_for_chart_data_objectives > chart_data_objectives.length-1) { 
		data3.push(null);
		continue;
	}

	let y = dates_for_chart[i].substring(0, 4);
	let m = dates_for_chart[i].substring(4, 6);
	let d = dates_for_chart[i].substring(6, 8);
	
	let objective_date = chart_data_objectives[idx_for_chart_data_objectives].date;
	let objective_percent = chart_data_objectives[idx_for_chart_data_objectives].percent;
	let objective_y = objective_date.substring(0, 4);
	let objective_m = objective_date.substring(4, 6);
	let objective_d = objective_date.substring(6, 8);
	
	if(y==objective_y && m==objective_m && d==objective_d) {
		data3.push(objective_percent)
		idx_for_chart_data_objectives++;
	} else {
		data3.push(null);
	}
}

let idx_for_chart_data_records = 0;
const data2 = []; 	// records(기록) : 회색 선으로 표시하는 데이터.
for(let i=0; i<=dates_for_chart.length-1; i++) {
	if(idx_for_chart_data_records > chart_data_records.length-1) { 
		data2.push(null);
		continue;
	}

	let y = dates_for_chart[i].substring(0, 4);
	let m = dates_for_chart[i].substring(4, 6);
	let d = dates_for_chart[i].substring(6, 8);
	
	let records_date = chart_data_records[idx_for_chart_data_records].date;			
	let records_percent = chart_data_records[idx_for_chart_data_records].percent;
	let records_y = records_date.substring(0, 4);
	let records_m = records_date.substring(4, 6);
	let records_d = records_date.substring(6, 8);
	
	if(y==records_y && m==records_m && d==records_d) {
		data2.push(records_percent);
		idx_for_chart_data_records++;
	} else {
		data2.push(null);
	}
}

function draw() {
	const yValues = [55, 49, 44, 24, 15];
	const barColors = ["red", "green","blue","orange","brown"];
/* 			const data1 = [860,1140,1060,1060,1070,1110,1330,2210,null,2478];
			for(let i=0; i<=data1.length-1; i++) {
				if(data1[i] == null) continue;
				data1[i] = data1[i] / 2478 * 100; 
			} */
	const myChart = new Chart('chart1', {
		type: "line",
		data: {
			labels: dates_for_chart_display,
			datasets: [
			/* {
			    data: data1,
			    borderColor: "red",
			    fill: true
			},*/
			{
			    data: data2,
			    borderColor: "rgb(182, 179, 179)",
			    backgroundColor: "white",
			    fill: false
			}, 
			{
			    data: data3,
			    borderColor: "rgb(235, 231, 231)",
			    backgroundColor: "rgb(235, 231, 231)",
			    fill: true
			}]
		},
		options: {
			elements: {
				point: {
					radius: 0
				}
			},
			hover: {
				mode: 'label'
			},
			scales: {
				x: {
					display: true
				},
				y: {
					ticks: {
						stepSize: 25,
						min: 0,
						max: 100,
						callback: function(value, index, values) {
							return value + '%';
						}
					}
				}						
			},
			spanGaps: true,
			plugins: {
				legend: {
					display: false
				}
			}
		}
	});
}
