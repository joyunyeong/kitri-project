<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<!-- 
				js/jquery-ui-custom-1.11.2.min.js : 전체 이벤트
		 -->
		 
		<title>Add Event</title>
		<%@ include file="/template/default_link.jsp"%>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui-custom-1.11.2.min.js"></script>
    	<link rel="stylesheet" type="text/css" href="css/jquery-ui-custom-1.11.2.min.css" />
    
    	<script type="text/javascript" src="js/bootstrap.diary.min.js"></script>
    	<link rel="stylesheet" type="text/css" href="css/bootstrap.diary.min.css" />
    
    	<script type="text/javascript" src="js/DateTimePicker.js"></script>
    	<link rel="stylesheet" type="text/css" href="css/DateTimePicker.css" />
    
		<link rel="stylesheet" type="text/css" href="css/calenstyle.css" />
		<link rel="stylesheet" type="text/css" href="css/calenstyle-jquery-ui-override.css" />
		<link rel="stylesheet" type="text/css" href="css/calenstyle-iconfont.css" />
		<script type="text/javascript" src="css/calenstyle.js"></script>
		
		<script type="text/javascript" src="js/CalJsonGenerator.js"></script>
	
<style type="text/css">

#div1 {
  width: 1000px;
  height: 100px;
  padding: 10px;
  border: 1px solid #aaaaaa;
}

.calendarContOuterParent {
	width: 1024px;
	height: 768px;
	margin: 70px auto;
	}
		
.calendarContOuter {			
	width: 1000px;
	height: 500px;
	margin: 0px auto;      
	font-size: 14px;
	}
	
.drop {
	height : 300px;
}
		
.calendarImageDrag {
    margin-top: -;
    border-top-width: 50px;
    padding-top: 50px;

	}		
		
#ipAlertStartEnd, #ipAlertTitle {
	display:none;
	}
	
#images > div, #boards > div {float:left; width:100px; height:100px; border:1px solid #000; margin:5px;}
</style>
	
	
<script type="text/javascript">
var oCal1, sInputTZOffset = "-00:00";
$(document).ready(function() {
	$(".calendarContOuter").CalenStyle({
		initialize: 
			function() {
				oCal1 = this;
			},

		//selectedDate: new Date(2014, 05, 12, 0, 0, 0, 0),
		displayWeekNumInMonthView: true,
		calculateDetailedMonthViewRowMinHeight: false,
					
		//fixedHeightOfDetailedMonthView: true,
		changeColorBasedOn: "Event",
		hideEventIcon: {Default: true},
		formatDates: {
			"hh:mm": function(iDate)
			{
				if(iDate.H < 5)
					return "Night " + oCal1.getNumberStringInFormat(iDate.h, 2, true) + ":" + oCal1.getNumberStringInFormat(iDate.m, 2, true);
				else if(iDate.H < 10)
					return "Morning " + oCal1.getNumberStringInFormat(iDate.h, 2, true) + ":" + oCal1.getNumberStringInFormat(iDate.m, 2, true);
				else if(iDate.H < 14)
					return "Afternoon " + oCal1.getNumberStringInFormat(iDate.h, 2, true) + ":" + oCal1.getNumberStringInFormat(iDate.m, 2, true);
				else if(iDate.H < 19)
					return "Evening " + oCal1.getNumberStringInFormat(iDate.h, 2, true) + ":" + oCal1.getNumberStringInFormat(iDate.m, 2, true);
				else
					return "Night " + oCal1.getNumberStringInFormat(iDate.h, 2, true) + ":" + oCal1.getNumberStringInFormat(iDate.m, 2, true);
			}
		},
					
		calDataSource: 
			[{
				sourceId: "s1",
				sourceFetchType: "DateRange",
				sourceType: "FUNCTION",
				config: 
					{
						inputTZOffset: sInputTZOffset,
					},
				source: function(fetchStartDate, fetchEndDate, durationStartDate, durationEndDate, oConfig, loadViewCallback) 
							{
								var calObj1 = this;
								calObj1.incrementDataLoadingCount(1);
										
								var oEventResponse = generateJsonEvents(fetchStartDate, fetchEndDate);
								if(oEventResponse != undefined) {
									if(oEventResponse[0]) {
										calObj1.parseDataSource("EventSource", oEventResponse[1], durationStartDate, durationEndDate, loadViewCallback, oConfig, false);
													}
												}
							}
			}],
					
		saveChangesOnEventDrop: 
			function(oDraggedEvent, startDateBeforeDrop, endDateBeforeDrop, startDateAfterDrop, endDateAfterDrop) {
				var calObj1 = this;
					
				console.log(oDraggedEvent);
				console.log(startDateAfterDrop);
				console.log(endDateAfterDrop);
						
				//calObj1.revertToOriginalEvent(oDraggedEvent, startDateBeforeDrop, endDateBeforeDrop);
			},
					
		cellClicked: // 셀별로 클릭했을 시 event
			function(sView, dSelectedDate, bIsAllDay, pClickedAt){
					console.log("Cell Click했슈 : " + dSelectedDate);
					showModal(true, dSelectedDate);						
			},
					
		eventsAddedInView: function(visibleView, eventClass){
			var thisObj = this;
					
			$(thisObj.elem).find(eventClass).popover({
				placement: "top",
				trigger: "hover",
				html: true,
				container: "body",
				content: function() {
					var oTooltipContent = $(this).data("tooltipcontent"),
					sTooltipText = "<div class='cTooltipTitle'>" + oTooltipContent.title + "</div><div class='cTooltipTime'>" + oTooltipContent.startDateTime + "<br/>" + oTooltipContent.endDateTime + "</div>";
					return sTooltipText;
				}});
		}
					
	});
				
	$('input[type="checkbox"]').bind('click',function(){
		validateAllDayChecked();
	});
				
	$('#modal-form').on('show.bs.modal', function (e){
		validateAllDayChecked();
	});
	
	
	//customizeInputs();
	//defineFormEvents();
	
	//드래그 fun
	//cmvDay cmvWeekNumber cmvWeekNumberBorderLeft cmvWeekNumberBorderBottom clickableLink ui-droppable cmvThinBorderRight
	//console.log($(".cmvDay"));
	$(".cmvDay").on("drop", function(e) {
		alert("놓음");
	});
	
	$(".cmvDay").on("dragenter", function (e) {
		alert("들어옴");
	});
	
	
}); // 1. $(document).ready(function()) 끝

function cmvDayDropDown() {
	alert("드래그 다운됨");
}
		
function showModal(bIsAllDay, dStartDateTime){
	console.log("showModal : " + bIsAllDay + " " + dStartDateTime);
	$("#modal-form").modal('show');
	$("#ipTitle, #ipDesc").val("");
	$("#ipAllDay").prop("checked", bIsAllDay);
			
	var dEndDateTime, sStartDateTime, sEndDateTime;
	//dStartDateTime : in modal
	if(bIsAllDay) {
		dEndDateTime = new Date(dStartDateTime);
		dEndDateTime.setDate(dStartDateTime.getDate() + (oCal1.setting.allDayEventDuration - 1));
				
		sStartDateTime = oCal1.getDateInFormat({"date": dStartDateTime}, "dd-MM-yyyy", false, false);
		sEndDateTime = oCal1.getDateInFormat({"date": dEndDateTime}, "dd-MM-yyyy", false, false);
	} else {
		dEndDateTime = new Date(dStartDateTime.getTime() + (oCal1.setting.eventDuration * 6E4));
				
		sStartDateTime = oCal1.getDateInFormat({"date": dStartDateTime}, "dd-MM-yyyy HH:mm", oCal1.setting.is24Hour, false);
		sEndDateTime = oCal1.getDateInFormat({"date": dEndDateTime}, "dd-MM-yyyy HH:mm", oCal1.setting.is24Hour, false);
	}
			
	console.log(sEndDateTime + "  " + sEndDateTime);
	$("#ipStart").val(sStartDateTime);
	$("#ipEnd").val(sEndDateTime);
	validateAllDayChecked();
} // 2. function showModal(bIsAllDay, dStartDateTime) 끝
	

function customizeInputs() {
	console.log("DateTimePicker : ");
	console.log($(".modal-dtpicker"));
	/*$(".modal-dtpicker").DateTimePicker( {
		dateSeparator: oCal1.setting.formatSeparatorDate,
		timeSeparator: oCal1.setting.formatSeparatorTime,
		dateTimeSeparator: oCal1.setting.formatSeparatorDateTime,
		dateFormat: "dd-MM-yyyy",
		dateTimeFormat: "dd-MM-yyyy HH:mm:ss"
				
	});*/
} // 3. function customizeInputs() 끝
		
		
function validateDateTimes(bIsAllDay, dStartDateTime, dEndDateTime) {
	if(bIsAllDay) {
		console.log("Date Comparison : " + oCal1.compareDates(dStartDateTime, dEndDateTime));
			if(oCal1.compareDates(dStartDateTime, dEndDateTime) > 0) {
				$("#ipAlertStartEnd").show();
				return false;
				}
	} else {
		console.log("DateTime Comparison : " + oCal1.compareDateTimes(dStartDateTime, dEndDateTime));
			if(oCal1.compareDateTimes(dStartDateTime, dEndDateTime) > 0){
				$("#ipAlertStartEnd").show();
				return false;
				}
	}
	
	$("#ipAlertStartEnd").hide();
	return true;
} // 4. function validateDateTimes(bIsAllDay, dStartDateTime, dEndDateTime) 끝
		
		
function validateAllDayChecked() {
	console.log("validateAllDayChecked " + ($("#ipAllDay").is(':checked')));
	if($("#ipAllDay").is(':checked')){
		$("#ipStart-group label").html("Start Date : ");
		$("#ipEnd-group label").html("End Date : ");
				
		$("#ipStart, #ipEnd").data("field", "date");
				
		var sDateTimeRegex = /^([0-3]{1}[0-9]{1})(-([0-1]{1}[0-9]{1}))(-([0-9]{4}))(\s)([0-2]{1}[0-9]{1}):([0-6]{1}[0-9]{1})/;
		var sDateTimeStart = $("#ipStart").val(),
		sArrDateTimeStart = sDateTimeStart.match(sDateTimeRegex),
		sDateTimeEnd = $("#ipEnd").val(),
		sArrDateTimeEnd = sDateTimeEnd.match(sDateTimeRegex);
		
		if(sArrDateTimeStart != null)
			$("#ipStart").val(sDateTimeStart.split(" ")[0]);
		if(sArrDateTimeEnd != null)
			$("#ipEnd").val(sDateTimeEnd.split(" ")[0]);
	} else {
		$("#ipStart-group label").html("Start Date Time : ");
		$("#ipEnd-group label").html("End Date Time : ");
				
		$("#ipStart, #ipEnd").data("field", "datetime");
				
		var sDateTimeRegex = /^([0-3]{1}[0-9]{1})(-([0-1]{1}[0-9]{1}))(-([0-9]{4}))(\s)([0-2]{1}[0-9]{1}):([0-6]{1}[0-9]{1})/;
		var sDateTimeStart = $("#ipStart").val(),
		sArrDateTimeStart = sDateTimeStart.match(sDateTimeRegex),
		sDateTimeEnd = $("#ipEnd").val(),
		sArrDateTimeEnd = sDateTimeEnd.match(sDateTimeRegex);
		console.log("Arrays : " + sDateTimeStart + " " + sDateTimeEnd);
		console.log(sArrDateTimeStart);
		console.log(sArrDateTimeEnd);
			if(sArrDateTimeStart == null)
				$("#ipStart").val(sDateTimeStart + " 00:00");
			if(sArrDateTimeEnd == null)
				$("#ipEnd").val(sDateTimeEnd + " 00:00");
			}
} // 5. function validateAllDayChecked() 끝
		
		
function parseDateInFormat(bIsAllDay, sDateTime){
	var dDateTime;
	if(bIsAllDay){
		var sArrDateTime = sDateTime.match(/^([0-3]{1}[0-9]{1})(-([0-1]{1}[0-9]{1}))(-([0-9]{4}))/);
		console.log(sArrDateTime);
		dDateTime = new Date(sArrDateTime[5], sArrDateTime[3] - 1, sArrDateTime[1], 0, 1, 0, 0);
	} else {
		var sArrDateTime = sDateTime.match(/^([0-3]{1}[0-9]{1})(-([0-1]{1}[0-9]{1}))(-([0-9]{4}))(\s)([0-2]{1}[0-9]{1}):([0-6]{1}[0-9]{1})/);
		console.log(sArrDateTime);
		dDateTime = new Date(sArrDateTime[5], sArrDateTime[3] - 1, sArrDateTime[1], sArrDateTime[7], sArrDateTime[8], 0, 0);
	}
	
	return dDateTime;
} // 6. function parseDateInFormat(bIsAllDay, sDateTime) 끝
		
		
function defineFormEvents() {
	$("#ipStart, #ipEnd").change(function() {
		var bIsAllDay = $("#ipAllDay").is(':checked'),
		dStartDateTime = parseDateInFormat(bIsAllDay, $("#ipStart").val()),
		dEndDateTime = parseDateInFormat(bIsAllDay, $("#ipEnd").val());
		validateDateTimes(bIsAllDay, dStartDateTime, dEndDateTime);
	});
			
	$("#submit").click(function(){
		var bIsAllDay = $("#ipAllDay").is(':checked'),
		dStartDateTime = parseDateInFormat(bIsAllDay, $("#ipStart").val()),
		dEndDateTime = parseDateInFormat(bIsAllDay, $("#ipEnd").val()),
		sTitle = $("#ipTitle").val(),
		sDesc= $("#ipDesc").val();
				
		if(sTitle != "")
			$("#ipAlertTitle").hide();
		else
			$("#ipAlertTitle").show();
				
		if(sTitle != "" && validateDateTimes(bIsAllDay, dStartDateTime, dEndDateTime)) {
			if(!bIsAllDay) {
				dStartDateTime = oCal1.normalizeDateTimeWithOffset(dStartDateTime, oCal1.setting.outputTZOffset, sInputTZOffset);
				dEndDateTime = oCal1.normalizeDateTimeWithOffset(dEndDateTime, oCal1.setting.outputTZOffset, sInputTZOffset);
						}
					
			var oEvent = new CalEvent("AddedOnUI", bIsAllDay, dStartDateTime, dEndDateTime, "Dynamic", sTitle, sDesc, "https://www.google.com");
			var oArrEvent = new Array();
			oArrEvent.push(oEvent);
			console.log(oEvent);
			console.log(oArrEvent);
			oCal1.addEventsForSource(oArrEvent, "s1");
			oCal1.refreshView();
					
			$("#modal-form").modal("hide");
			
					}
	});
} // 7. function defineFormEvents() 끝
</script>

<!-- 이미지 DRAG & DROP -->
<script>
function allowDrop(ev) { ev.preventDefault();} 
function drag(ev) { ev.dataTransfer.setData("text", ev.target.id); }
function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	//console.log(data);
	var dataTemp = document.getElementById(data).cloneNode();
	$(dataTemp).css("width", "20px");
	$(dataTemp).css("height", "20px");
	$(dataTemp).click(function(e) {
		alert("거북이 클릭");
	});
	ev.target.appendChild(dataTemp);
}
</script>

</head>

<!-- BODY START : /template/default_js_link.jsp를 include 하지 않아서 모달 뜬거였음-->
<body>

<div class="d-flex align-items-stretch" id ="document">
	<%@ include file="/template/sidebar.jsp" %>
	<div class="calendarContOuterParent" >
		<div class="calendarContOuter" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
		<br><br>
		<img id="drag1" src="img/hospital.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
		<img id="drag2" src="img/bones.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
		<img id="drag3" src="img/dog.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
		<img id="drag4" src="img/bath.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
		<img id="drag5" src="img/facial-treatment.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
		<img id="drag6" src="img/school.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
		<img id="drag7" src="img/pet-house.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px">
		<br><br>
		<div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)">테스트용</div>
	</div>
</div>




<!-- Calendar 날짜 클릭했을시에 모달창 FE -->
<div id="modal-form" class="modal fade">
	<div class="modal-dtpicker"></div>
	<div class="modal-dialog">
		<div class="modal-content">
			<form>
				<div class="modal-header">다이어리 생성</div>
				<div class="modal-body">
					<div id="ipAlertTitle" class="alert alert-danger" role="alert">다이어리를 입력해주세요. </div>
					<div id="ipTitle-group" class="form-group">
						<label for="ipTitle">Title : </label>
						<input type="text" class="form-control" id="ipTitle" placeholder="Title">
					</div>
					<div id="ipAllDay-group" class="checkbox">
						<label>
							<input id="ipAllDay" type="checkbox" checked> All Day
						</label>
					</div>
					<div id="ipAlertStartEnd" class="alert alert-danger" role="alert">Start DateTime should be earlier than End DateTime</div>
			 		<div id="ipStart-group" class="form-group">
						<label for="ipStart">Start Date : </label>
						<input type="text" class="form-control" id="ipStart" data-field="date" placeholder="Start">
					</div>
					<div id="ipEnd-group" class="form-group">
						<label for="ipStart">End Date : </label>
						<input type="text" class="form-control" id="ipEnd" data-field="date" placeholder="End">
					</div>
					<div id="ipDesc-group" class="form-group">
						<label for="ipDesc">Description : </label>
						<textarea class="form-control" rows="3" id="ipDesc" placeholder="Description"></textarea><br>
						<img id="drag1" src="img/hospital.png" draggable="true" ondragstart="drag(event)" width="30px" height="30px"> &nbsp;&nbsp;&nbsp;&nbsp;
						<img id="drag2" src="img/bones.png" draggable="true" ondragstart="drag(event)" width="30px" height="30px"> &nbsp;&nbsp;&nbsp;&nbsp;
						<img id="drag3" src="img/dog.png" draggable="true" ondragstart="drag(event)" width="30px" height="30px"> &nbsp;&nbsp;&nbsp;&nbsp;
						<img id="drag4" src="img/bath.png" draggable="true" ondragstart="drag(event)" width="30px" height="30px"> &nbsp;&nbsp;&nbsp;&nbsp;
						<img id="drag5" src="img/facial-treatment.png" draggable="true" ondragstart="drag(event)" width="30px" height="30px"> &nbsp;&nbsp;&nbsp;&nbsp;
						<img id="drag6" src="img/school.png" draggable="true" ondragstart="drag(event)" width="30px" height="30px"> &nbsp;&nbsp;&nbsp;&nbsp;
						<img id="drag7" src="img/pet-house.png" draggable="true" ondragstart="drag(event)" width="30px" height="30px">
					</div>					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default form-btn" data-dismiss="modal">Close</button>
					<button id="submit" type="button" class="btn btn-default form-btn">Submit</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>