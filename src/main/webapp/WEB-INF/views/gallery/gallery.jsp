<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>day12 Gallery</title>
<style type="text/css">
input {  padding: 3px; border: 1px solid gray; border-radius:5px;  }
.thumbnail { padding-bottom: 10px; border:none; }
/* .thumbnail img{ width:100%;height: 300px; object-fit:cover; margin-bottom: 10px;} */
.container {width:30%;  float:left; padding:10px;border: none;}
form > input:nth-child(5) {
	margin-left: 30px;
}
</style>  <!-- 이미지 다룰때 thumbnail 라이브러리를 사용하기도 합니다. -->
<%-- <script src="<c:url value='/resources/css/jquery-3.2.1.min.js'/>"  ></script> --%>
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
</head>
<body>
	<form action="gallery" method="post" enctype="multipart/form-data">
		<input type="text" name="title" placeholder="제목 입력하세요. ">
		<!-- <input type="file" name="files" accept="image/*"  
		 placeholder="이미지 파일을 선택하세요."> --><br>  <!-- audio/*, video/* , 특정확장자 -->
		<input type="button" value="파일 추가" id="addFile">
		<table id="fileview">
		<tr><td><input name="files" type="file"  accept="image/*"  placeholder="이미지 파일을 선택하세요."></td></tr>
		</table>
		<input type="submit" value="전송">
	</form>

	<script type="text/javascript">
	$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = $('#fileview tr').length; 
        if (Number(fileIndex) >= '3') {
        	alert('FILE UPLOAD는 3개만 가능 합니다.');
        	return;
        }
        $('#fileview').append(
                '<tr><td>'+
                '   <input type="file" name="files" accept="image/*"  placeholder="이미지 파일을 선택하세요."> '+
                '</td></tr>');
    });     
});
</script>

<hr> <!-- 업로드 결과 확인 : c:\upload 폴더에 파일 있는지 , db table 에 insert  -->
<!-- 파일업로드 전송을 한 후에 view 를 gallery.jsp 로 하고 아래에 업로드 이미지가 나오도록 합니다. -->	
<c:forEach var="item" items="${list }">
<c:set var="fileArr" value="${fn:split(item.filename,',')}"/>
	<div class="container">
		<div class="thumbnail">
			<c:forEach var="img" items="${fileArr }">
			<img alt="gallery" width="100px" src="/upload/${img }">  
<%-- 			<img alt="gallery" width="100px" src="<c:url value='/upload/${img }'/>">   --%>
			</c:forEach>
			<!-- /img/는 url 경로, 이 경로와 c:\upload 매핑이 필요합니다. : server.xml-->
		</div>
		<div>
			<strong>${item.title }</strong>
		</div>
	</div>
</c:forEach>

</body>
<!-- 
1. 업로드 파일은 서버로컬컴퓨터의 지정폴더에 저장한다.
2. 업로드 파일의 파일명을 저장할 데이터베이스 테이블 컬럼이 필요하다.
-> 테이블 새로 생성
3. 파일업로드를 처리할 라이브러리를 추가한다.
4. form 태그의 enctype="multipart/form-data" 설정해야한다.
 -->
<!-- 
server.xml 에서 host 태그사이에 추가합니다.
   <Context docBase="c:\upload" path="/img" reloadable="true"/>
 -->

</html>