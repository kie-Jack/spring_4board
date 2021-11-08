<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<hr/>
<a href="community/list">커뮤니티</a>
<a href="community/list2">커뮤니티</a><br>
<c:if test="${sessionScope.customer != null }">	<!-- customer는 Session Attribute입니다. -->
	${sessionScope.customer.name } 님 (${sessionScope.customer.email}) 환영합니다. <br>
	<a href="customer/detail">나의 정보</a><br> ${sessionScope.customer.password }
	<a href="logout">로그아웃</a><br>
</c:if>
<c:if test="${sessionScope.customer == null }">
	<a href="login">로그인</a><br>
	<a href="customer/join?start=1">회원가입</a><br>
</c:if>
<!-- 애트리뷰트 저장 영역 : page, request, session, application
	 동일한 애트리뷰트가 여러 곳에 존재한다면
	 el로 표시했을 때 찾는 순서 = page -> request -> session -> application
	 동일한 이름의 애트리뷰트가 없다면 sessionScope 생략가능.
 -->
</body>
</html>
