<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<style type="text/css">
	table, td,th{
	padding: 10px;
	}

</style>
</head>
<body>
<div align="center">
	<h1>로그인</h1>
	<form method="post" action="login">
	<table>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"/></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="로그인">
				
				<input type="button" value="처음으로" 
onclick="location.href='${pageContext.request.contextPath}'"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>