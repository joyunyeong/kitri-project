<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
  <title>Insert title here</title>
  <script src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript">
  $(function() {
    $("#call").click(function() {
      alert('Ŭ���ϼ̱���');
      var str = $("#form1").serialize();
      alert(str);

      $.ajax({
        type:"POST",
        url:"/plzdaengs/diary",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: str,
        datatype:"json",
        success: function(data) {
          alert(data.result);			
        },
        error: function(e) {
          alert("�����߻�");
        }			
      });
    });
  });
  </script>
</head>
<body>
  <form id="form1">
    <input type="text" name="val" value="ajax �׽�Ʈ" id="val"></input>
    <input type="text" name="num" value="12345" id="num"></input>
    <input type="button" value="ȣ��" id="call"></input>
  </form>
</body>
</html>