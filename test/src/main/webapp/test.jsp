<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form action="Testcon">
<input type = "text" name = 'id'>
<input type = "text" name = "pw">
<input type = 'submit'>
</form>

<script>
$.ajax({
	url : "221.156.210.189:5050/test",
	type : "POST",
	data : {
		'id' : id,
		'pw' : pw
		},
		success : function(res){
		alert('요청 성공')
		out.print(id + pw)
		},
		error: function(){
		alert('실패')
		}
 })
</script>



</body>



</html>