<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Day3[]- db 회원 등록</title>
<style>
	ul{
		list-style: none;
		padding-inline-start:0px;  /* 목록 들여쓰기 없애기 */
	}
	ul #name{
		padding : 5px  20px;
		margin : 5px 0px;
		background-color: tomato;
		color:white;
	}
	li {
		padding : 5px  20px;
	}
</style>
</head>
<body>
<h3>환영합니다. WELCOME!!!</h3>
<ul>
	<li id="name">${customer.name} 님</li>
	<li>${customer.email}</li>
	<li>${customer.age}세 (${customer.gender=='male'?'남':'여'}) </li>
	<li>지역 : ${customer.addr}</li>
	<li>취미 : ${customer.hobby}</li>
</ul>
<a href="/board">home</a>
<a href="update">정보수정</a>

</body>
</html>