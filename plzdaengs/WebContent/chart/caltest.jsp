<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String title = request.getParameter("title");
	String description = request.getParameter("description");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Calendar</title>
	<%@ include file="/template/default_link.jsp"%>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.diary.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.bundle.js"></script>

<style type="text/css">
	#div1 {
	  width: 1000px;
	  height: 100px;
	  padding: 10px;
	  border: 1px solid #aaaaaa;
	  margin-left: 500px;
	}
	
	.cal-day{
	/*
		background-color: black;
	*/
	}
	.cal_top{
	    text-align: center;
	    font-size: 30px;
	}
	
	.cal{
	    text-align: center;    
	}
	
	table.calendar .cal-schedule{
	/*
		1. 이 부분이 일정등록 클릭할 부분
	*/
		min-height : 55px;
		font-size: medium;
	}
	
	table.calendar{
	/*
		1. 캘린더 위치설정
		2. 달력 칸칸별 넓혀놓고 > border: thin solid black; 지워놓기
	*/
	    margin-left: 500px; 
	    margin-top: 100px;
	    display: inline-table;
	    text-align: left;
	}
	
	table.calendar td{
	    vertical-align: top;
	    height : 50px;
	    width: 150px;
	}
	
	.schedule:hover{
		border: 1px solid black;
		cursor: pointer;
	}
</style>

<script type="text/javascript">
    var today = null;
    var year = null;
    var month = null;
    var firstDay = null;
    var lastDay = null;
    var $tdDay = null;
    var $tdSche = null;
    var cellText = null;
    var cell = null;
 
    $(document).ready(function() { // 아예 시작할 때

        drawCalendar();
        initDate(); // 날짜 초기화할것
        drawDays();
        
        var a = $("#attr").html();
    	console.log(a);
    	//$(".cal-schedule").html('<div>'+ a + '</div>');
    	//alert('가져온 값 : ' + a);
    	
        $("#movePrevMonth").on("click", function(){movePrevMonth();});
        $("#moveNextMonth").on("click", function(){moveNextMonth();});
        $('table tbody td').mouseover(function() {
        	//alert('올려놨슈');
            $(this).children().css({
                'backgroundColor' : '#DCDCDC',
                'cursor' : 'pointer'
            });
        }).mouseout(function() { // 그 순간 움직일 때
        	//alert('내려놨슈');
            $(this).children().css({
                'backgroundColor' : '#F8F9FB',
                'cursor' : 'default'
            }); // $(this).children().css끝
        });
        
		$('table tbody td div.cal-day').click(function(e) { // table td 클릭할 때
			
			// 1. 클릭하면 모달나오게 해야함 > 일정 등록하는거
			cellText = $(this).text().trim(); // ★★★★★★★★★ 몇번째 꺼인지 
			cell = $(this);
			console.log(cell);
			console.log(cellText);
			
			alert(cellText + '일 click!'); // 위에 .html하면 관련 식 나오고 text해야 date나옴
			//request.setAttribute("cellText", cellText);
			
			// 보내야함.. https://codeday.me/ko/qa/20190321/115067.html
			//e.originalEvent.dataTransfer.setData("cellText", cellText);
			
			$("#enroll").modal();
        }) // td클릭시 function 끝
        
        $('table tbody td div.cal-schedule').click(function() {
        	// 2. 밑에 스케줄쪽 클릭하면 편집할 수 있어야
			//alert('div.cal-schedule 클릭 > modal 연결');
			//$("#enroll").modal(); // 근데 이거 submit하기 전이얌 그니까 당연히 null이뜸
			//console.log('[1] modal창 넘어감');
        })
        
        
    });
    
    //calendar 그리기
    function drawCalendar(){
    	
        var setTableHTML = "";
        
        //var a = $("#attr").html();
    	//console.log('drawCalendar()에서 받아옴 : '+ a);
    	//var insert = '<div>'+ a +'</div>'
    	//console.log(insert);
    	//console.log(cellText);
    	
        setTableHTML+='<table class="calendar" id = "calendar">';
        setTableHTML+='<tr><th>SUN</th><th>MON</th><th>TUE</th><th>WED</th><th>THU</th><th>FRI</th><th>SAT</th></tr>';
        for(var i=0;i<6;i++){
            setTableHTML+='<tr height="100">';
            for(var j=0;j<7;j++){
                setTableHTML+='<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
                setTableHTML+='    <div class="cal-day" id = "cal-day" ondrop="drop(event)" ondragover="allowDrop(event)"></div>'; // day 써있는곳에 놓기
                setTableHTML+='    <div class="cal-schedule"></div>';
                setTableHTML+='</td>';
            }
            setTableHTML+='</tr>';
        }
        setTableHTML+='</table>';
        $("#cal_tab").html(setTableHTML);
        
    }
 
	//날짜 초기화
    function initDate(){
        $tdDay = $("td div.cal-day")
        $tdSche = $("td div.cal-schedule")
        dayCount = 0;
        today = new Date();
        year = today.getFullYear();
        month = today.getMonth()+1;
        firstDay = new Date(year,month-1,1);
        lastDay = new Date(year,month,0);
        
        if(month<10){
            month=String("0"+month);
        }
        
    }
    
	//calendar 날짜표시
    function drawDays(){
        $("#cal_top_year").text(year);
        $("#cal_top_month").text(month);
        for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
            $tdDay.eq(i).text(++dayCount);
        }
        for(var i=0;i<42;i+=7){
            $tdDay.eq(i).css("color","red");
            //$tdDay.eq(i).css("background","red");
        }
        for(var i=6;i<42;i+=7){
            $tdDay.eq(i).css("color","blue");
        }
        
        initData();
    }
 
    //calendar 월 이동
    function movePrevMonth(){
        month--;
        if(month<=0){
            month=12;
            year--;
        }
        
        if(month<10){
            month=String("0"+month);
        }
        
        getNewInfo();
        $("#movePrevMonth").css("color", "#4680ff");
        return false;
    }
    
    function moveNextMonth(){
        month++;
        if(month>12){
            month=1;
            year++;
        }
        
        if(month<10){
            month=String("0"+month);
        }
        
        getNewInfo();
        $("#moveNextMonth").css("color", "#4680ff");
		return false;
    }
    
    function initData() {
        //console.log("year :" + year);
        //console.log("month :" + month);
        var date = year + "/" + month;
    	$.ajax({
        	url : "/plzdaengs/diaryinit"
        	, data : {
        		date : date
        	}
        	, success : function(result) {
				//초기화
				$(".cal-schedule").html("");
				var resultJSON = JSON.parse(result);
				if(resultJSON.length == 0){
					return;
				}
				for(var i=0 ; i<resultJSON.length ; i++){
					var diaryDate = new Date(resultJSON[i].diary_date);	
					var diaryDay = diaryDate.getDate();
					var diaryNumber = resultJSON[i].diary_number;
					var diarySubject = resultJSON[i].diary_subject;
					var diaryContents = resultJSON[i].diary_contents;
					var diaryImg = resultJSON[i].diary_img;	
					makeSchedule(diaryDay, diaryNumber, diarySubject, diaryContents, diaryImg);
				}
			}
        }); 
	}
    function makeSchedule(diaryDay, diaryNumber, diarySubject, diaryContents, diaryImg) {
    	var dayDivs = $(".cal-day");
    	var dayDiv;
    	for(var i=0 ; i<dayDivs.length ; i++){
    		var text = $(dayDivs[i]).text();
    		if(text.trim() == diaryDay){
    			dayDiv = $(dayDivs[i]);
    			break;
    		}
    	}

    	var schedule = dayDiv.siblings(".cal-schedule");
    	//기본 div 생성
    	var element = $("<div/>", {
    		class : "schedule"
    		, text : diarySubject
    	});
    	var hidden = $("<input/>", {
    		type : "hidden"
    		, name : "diarynumber"
    		, value : diaryNumber
    	});
    	element.append(hidden);
    	element.click(scheduleClick);
    	schedule.append(element);
    	
	}

    
    function getNewInfo(){
        for(var i=0;i<42;i++){
            $tdDay.eq(i).text("");
        }
        dayCount=0;
        firstDay = new Date(year,month-1,1);
        lastDay = new Date(year,month,0);
        drawDays();
        
    }
</script>

<!-- DRAG & DROP -->
<script>
	function allowDrop(ev) { 
		ev.preventDefault();
	} 
	
	function drag(ev) { 
		//var a = $('#attr').text();
		//console.log(a);
		ev.dataTransfer.setData("text", ev.target.id); // img용
		
	}
	
	function drop(ev) {
		var a = $('#attr').text();
		console.log('1!!!' + a);
		
		ev.preventDefault();
		var data = ev.dataTransfer.getData("text"); // img의 id
		console.log(data);
		
		var dataTemp = document.getElementById(data).cloneNode();
		console.log(dataTemp);
		$(dataTemp).css("width", "40px");
		$(dataTemp).css("height", "40px");
		$(dataTemp).css("font-size", "small");
		$(dataTemp).click(function(e) {
			alert("이모티콘 클릭해또");
		});
		ev.target.appendChild(dataTemp); // 이모티콘 붙일 때 없어지지 않고 남아있기
	}
	
	function bin(ev) { // 쓰레기통 오예
		ev.preventDefault();
		var data = ev.dataTransfer.getData("text");
		ev.target.appendChild(document.getElementById(data));
	}
	

</script>

</head>

<body>
<div class="d-flex align-items-stretch" id ="document">
<%@ include file="/template/sidebar.jsp" %>
<section>
    <div class="cal_top">
    	<!-- 모달모달 -->
    	<%@ include file="/diary/modal.jsp"%>
        <a href="#" id="movePrevMonth"><span id="prevMonth" class="cal_tit">&lt;</span></a>
        <span id="cal_top_year"></span>
        <span id="cal_top_month"></span>
        <a href="#" id="moveNextMonth">
        <span id="nextMonth" class="cal_tit">&gt;</span>
        </a>
		<div id="cal_tab" class="cal" ></div>
		<div id="cal_image" style="margin-left: 200px;" >
			<img id="drag1" src="img/hospital.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag2" src="img/bones.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag3" src="img/dog.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag4" src="img/bath.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag5" src="img/facial-treatment.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag6" src="img/school.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag7" src="img/pet-house.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img id="bin" src="img/bin.png" width="50px" height="50px" ondrop="bin(event)" ondragover="allowDrop(event)">
		</div>
    </div>
</section>
 </div>

 
</body>
</html>