<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='css/main.css' rel='stylesheet' />
<link href='daygrid/main.css' rel='stylesheet' />
<link href='timegrid/main.css' rel='stylesheet' />
<script src='core/main.js'></script>
<script src='interaction/main.js'></script>
<script src='daygrid/main.js'></script>
<script src='timegrid/main.js'></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    console.log('1 '+calendarEl);

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      defaultDate: '2019-06-12',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Event Title:', 'oh no : ');
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
      editable: true,
      eventLimit: true, // allow "more" link when too many events
      events: [
        {
          title: 'All Day Event',
          start: '2019-06-01',
          color: "red"
        },
        {
          title: 'Long Event',
          start: '2019-06-07',
          end: '2019-06-10',
          color: "green"
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2019-06-09T16:00:00'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2019-06-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2019-06-11',
          end: '2019-06-13'
        },
        {
          title: 'Meeting',
          start: '2019-06-12T10:30:00',
          end: '2019-06-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2019-06-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2019-06-12T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2019-06-12T17:30:00'
        },
        {
          title: 'Dinner',
          start: '2019-06-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2019-06-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2019-06-28'
        }
      ]
    });

    calendar.render();
  });

</script>
<script type="text/javascript">
	// DRAG & DROP 오예!
	function allowDrop(ev) { ev.preventDefault();} 
	function drag(ev) { 
		ev.dataTransfer.setData("text", ev.target.id);
		//alert('drag 하였소');
	}
	function drop(ev) {
		ev.preventDefault();
		var data = ev.dataTransfer.getData("text");
		//alert('drop 하였소');
		console.log('1 ' + data);
		
		var dataTemp = document.getElementById(data).cloneNode();
		console.log('2 ' + dataTemp);
		//$(dataTemp).css("width", "40px");
		//$(dataTemp).css("height", "40px");
		//$(dataTemp).click(function(e) {
		//	alert("이모티콘 클릭해또");
		//});
		ev.target.appendChild(dataTemp); // 이모티콘 붙일 때 없어지지 않고 남아있기
	}
</script>

<style type="text/css">
	 #div1 {
	  width: 1000px;
	  height: 100px;
	  padding: 10px;
	  border: 1px solid #aaaaaa;
	  margin-left: 500px;
	}
	
	  body {
	    margin: 40px 10px;
	    padding: 0;
	    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	    font-size: 14px;
	  }
	
	  #calendar {
	    max-width: 900px;
	    margin: 0 auto;
	  }
</style>

</head>

<body>
	<div id='calendar' ondrop="drop(event)" ondragover="allowDrop(event)"></div>
	<div id="cal_image" style="margin-left: 200px;">
			<img id="drag1" src="img/hospital.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag2" src="img/bones.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag3" src="img/dog.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag4" src="img/bath.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag5" src="img/facial-treatment.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag6" src="img/school.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px"> &nbsp;&nbsp;&nbsp;&nbsp;
			<img id="drag7" src="img/pet-house.png" draggable="true" ondragstart="drag(event)" width="50px" height="50px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img id="bin" src="img/bin.png" width="50px" height="50px" ondrop="delete(event)" ondragover="allowDrop(event)">
	</div>
	<div id = "div1" ondrop="drop(event)" ondragover="allowDrop(event)">여기</div>
</body>
</html>
