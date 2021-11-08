<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>이메일 인증</h3>
<script type="text/javascript">
	function check(){
		const obj = document.frmAuth;
		if(obj.auth.checked == true){
			obj.submit();
		}else {
			alert('이메일 인증이 필요합니다.');
		}
	}
</script>
<form action="join" method="post" name="frmAuth">
	<input type="hidden" name="step" value="1">
	<input type="email" placeholder="이메일을 입력하세요." name="email">
	<input type="checkbox" name="auth">
	<input type="button" value="다음" onclick="check()"> 
</form>
	
</body>
</html>