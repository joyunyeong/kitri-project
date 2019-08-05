<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp" %>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<script type="text/javascript">
var backgroundColorArray = [
	'rgba(255, 99, 132, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(255, 206, 86, 0.2)',
    'rgba(75, 192, 192, 0.2)',
    'rgba(153, 102, 255, 0.2)',
    'rgba(255, 159, 64, 0.2)'
];
var borderColorArray =  [
    'rgba(255, 99, 132, 1)',
    'rgba(54, 162, 235, 1)',
    'rgba(255, 206, 86, 1)',
    'rgba(75, 192, 192, 1)',
    'rgba(153, 102, 255, 1)',
    'rgba(255, 159, 64, 1)'
];
$(function() {
	
	
	//selectAnimal();
	
	animalBreedRanking();

});

function animalBreedRanking() {
	$.ajax({
		url : "/plzdaengs/chart"
		, data : {
			data : "animalBreedRanking"
			, maxranking : 6
		}
		, success : function(result) {
			//console.log(json);
			makeBreedRankingChart(result);
		}
	});
}

function makeBreedRankingChart(result) {
	var labels = [];
	var data = [];
	var dataJSONAraary = JSON.parse(result);
	var length = dataJSONAraary.length;
	if(length == 0){
		return;
	}
	for(var i=0 ; i<length ; i++){
		var dataJSON = dataJSONAraary[i];
		labels.push(dataJSON.breed_name);
		data.push(dataJSON.count);
	}
	var donutCanvas = $("#donutChart");
	var myChart = new Chart(donutCanvas, {
		type : "doughnut"
		, data : {
			labels: labels
			, datasets :[{
				backgroundColor: backgroundColorArray
				, borderWidth: 2
		        , borderColor: borderColorArray
				, data : data
			}]
		}
		, options : {
			animateRotate : true
			/* , scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        } */
	        , onClick : breedClick
	        /* , title: {
	            display: true,
	            text: ''
	        } */
		}
	});
}
function breedClick(e, item){
	//alert($(this));
	//console.log(item);
	//console.log(item[0]);
	//console.log(item.0);
	//console.log(item[0]._model.label);
	$.ajax({
		url : "/plzdaengs/chart"
		, data : {
			data : "genderAvgAgeForBreed"
			, breedname : item[0]._model.label
		}
		, success : function(result) {
			//alert(result);
			makeBreedAvgChart(result);
			$(".breedChart .card-header h2").text(item[0]._model.label + "의 상세 정보");
			$(".breedChart").css("display", "block");
		}
	});
}
var breedAvgChart=null;

function makeBreedAvgChart(result) {
	var JSONArray = JSON.parse(result);
	var length = JSONArray.length;
	
	var labels = ["반려동물 수", "평균나이"];
	var datasets = [];
	
	if(length == 0){
		return;
	}
	
	for(var i=0 ; i<length ; i++){
		var json = JSONArray[i];
		var data = [];
		var dataset = {};
		dataset.backgroundColor = backgroundColorArray[i];
		dataset.borderColor = borderColorArray[i];
		dataset.borderWidth = 2;
		data.push(json.count);
		data.push(json.avgAge);
		dataset.data = data;
		dataset.label = json.gender;
		
		datasets.push(dataset);
	}
	
	var breedAvgCanvas = $("#breedAvgChart");
	if(breedAvgChart != null){
		breedAvgChart.destroy();
	}
	
	breedAvgChart = new Chart(breedAvgCanvas, {
		type : "bar"
		, data : {
			labels: labels
			, datasets : datasets
		}
		, options : {
			animateRotate : true
			, scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	        , onClick : breedClick
		}
	});

}
</script>
</head>
<body>
	<!-- navbar-->	
	<%@ include file="/template/header.jsp" %>
	<div class="d-flex align-items-stretch" id ="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp" %>
		<!-- 사이드 sidebar -->
		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid" id="contents">
				<!-- 로그인 시 뜨는 경고창 -->
				<%@ include file="/template/alert_danger.jsp" %>
				<!-- section 1 -->
				<section class="py-5">
					<div class="col-lg-10 mb-4 mb-lg-0">
						<div class="card">
							<div class="card-header">
								<h2 class="h6 text-uppercase mb-0">가장 많이 기르는 강아지는 누구일까요</h2>
							</div>
							<div class="card-body">
								<div class="chart-holder mt-5 mb-5">
									<canvas id="donutChart"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-10 mb-4 mb-lg-0 breedChart" style="margin-top: 5rem; display: none">
						<div class="card">
							<div class="card-header">
								<h2 class="h6 text-uppercase mb-0"></h2>
							</div>
							<div class="card-body">
								<div class="chart-holder mt-5 mb-5">
									<canvas id="breedAvgChart"></canvas>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<%@ include file="/template/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/template/default_js_link.jsp" %>
</body>
</html>