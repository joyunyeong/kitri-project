<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
<meta http-equiv="X-UA Compatible" control="IE=edge,chrome=1" />


 

<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script>
$(function(){
    // tr 태그에 마우스를 올릴때
    $('table tbody tr').mouseover(function() {
        $(this).children().css({
            'backgroundColor' : '#DCDCDC',
            'cursor' : 'pointer'
        });
    }).mouseout(function() {
        $(this).children().css({
            'backgroundColor' : '#FFFFFF',
            'cursor' : 'default'
        });
    });

    // 전체 선택, 전체 해제
    $("#checkall").change(function() {
        $("input:checkbox").prop('checked', $(this).prop("checked"));
    });

    $('#select_chkbox').click(function(i) {
        var rowData = new Array();
        var chkData = new Array();
        var checkbox = $('input:checkbox[name="uid[]"]:checked');
        checkbox.each(function(i){ // 반복되는 태그 단위를 찾아서 each() 함수 사용
           var select_value = $(this).val();
           chkData.push(select_value);

           // checkbox.parent() : checkbox의 부모는 <td>
           // checkbox.parent().parent() : <td>의 부모이므로 <tr>
           var tr = checkbox.parent().parent().eq(i);
           var td = tr.children();
           rowData.push(tr.text()+"<br />");
        });
        if (rowData.length == 0) {
           $("#chk_result").html("선택항 항목이 없습니다").css("color", "blue");
           $("#rawData_result").html("");

        } else {
           $("#chk_result").html("선택된 체크박스 값 : "+chkData).css("color", "blue");
           $("#rawData_result").html("체크된 Row의 모든 데이터 : <br /> "+rowData);
        }
    });

    var sum = 0;
    $('table tbody tr').each(function(){ //테이블 모든 tr에 순차 접근
       if($.isNumeric($("td:eq(4)",this).text())){ //해당 tr의 다섯번째 td에 접근해서 숫자인지 확인
           sum += parseInt($("td:eq(4)",this).text());//해당하는 값을 숫자로 변환해서 누적합산 처리
       }
    });
    $("#sum").text("수량 합계 : "+sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));// sum을 출력

});
</script>
</head>
<body>
<div class="container">
    <div class="row">
        <table class="table table-hover">
        <thead>
        <tr>
        <th class="header" width="30"><input type="checkbox" id="checkall" /></th>
        <th class="header" width="100">No</th>
        <th class="header" width="250">품목</th>
        <th class="header" width="250">가격</th>
        <th class="header" width="200">수량</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox" class="chkbox" name="uid[]" value="7" /></td>
            <td>7</td>
            <td>사과</td>
            <td>5</td>
            <td>5</td>
        </tr>
        <tr>
            <td><input type="checkbox" class="chkbox" name="uid[]" value="6" /></td>
            <td>6</td>
            <td>수박</td>
            <td>4</td>
            <td>500</td>
        </tr>
        <tr>
            <td><input type="checkbox" class="chkbox" name="uid[]" value="5" /></td>
            <td>5</td>
            <td>사과</td>
            <td>3</td>
            <td>3</td>
        </tr>
        <tr>
            <td><input type="checkbox" class="chkbox" name="uid[]" value="4" /></td>
            <td>4</td>
            <td>수박</td>
            <td>2</td>
            <td>500</td>
        </tr>
        <tr>
            <td>
            <input type="checkbox" class="chkbox" name="uid[]" value="3" />
            </td>
            <td>3</td>
            <td>복숭아</td>
            <td>7</td>
            <td>50</td>
        </tr>
        <tr>
            <td>
            <input type="checkbox" class="chkbox" name="uid[]" value="2" />
            </td>
            <td>2</td>
            <td>사과</td>
            <td>8</td>
            <td>90</td>
        </tr>
        <tr>
            <td>
            <input type="checkbox" class="chkbox" name="uid[]" value="1" />
            </td>
            <td>1</td>
            <td>망고</td>
            <td>555</td>
            <td>40</td>
        </tr>
        </tbody>
        <td colspan="5" style="text-align:left;">
        <button type="button" class="btn btn-success btn-sm" id="select_chkbox">선택</button>
        </table>
    </div>
    <div id="chk_result"></div>
    <div id="rawData_result"></div>
    <div id="sum"></div>
</div>

</body>
</html> 

