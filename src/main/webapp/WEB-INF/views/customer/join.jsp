<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<!-- 스타일 참고  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.css"> 
<script>
	function validCheck() {
		const frm = document.frmReg;
		//1. 패스워드는 8글자 이상이어야 한다.
		if (frm.password.value.length < 8){
			alert('패스워드는 8글자 이상으로 하세요.');
			frm.password.focus();				// 포커스(커서) 이동 
			return false;						//함수가 종료.
		}
		//2. 나이 : 태그 min,max 무시 -> 20 ~ 70 값 범위로 검사합니다.
		if(frm.age.value < 20 || frm.age.value > 70 ){    //  return false 할 조건으로 작성. 이 위치는 위의 조건이 거짓일 때만 실행됩니다. 
			alert('나이는 20세 이상 70세 이하로만 가능합니다. ');
			frm.age.focus();
			return false;
		}
		
		for(let i=0 ;i<frm.hobby.length;i++){
			if(frm.hobby[i].checked){       //checked 속성은 선택되어 있을 떄 true 
				return true;
			}
		}

		//체크한것 이 한개도 없으면 여기와서 실행합니다.
		alert('취미를 1개라도 선택해 주세요.');
		return false;
	}

	
</script>
</head>
<body>
	<div style="width: 70%; margin: auto;">
		<h3>회원 등록</h3> <a href="/board">home</a>
		<form action="join" name="frmReg" method="post"
			onsubmit="return validCheck()">
			
			<input type="hidden" name="save">
			<table style="width: 100%">
				<tr>
					<td><label>이름</label></td>
					<!--  required 필수 입력  , readonly : 읽기만.-->
					<td><input type="text" name="name" placeholder="이름 이력(필수)"
						required></td>
				</tr>
				<tr>
					<td><label>패스워드</label></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><label>이메일</label></td>
					<td><input type="email" name="email" value="${join.email }" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label>나이</label></td>
					<td><input type="number" name="age" min="10" max="99"
						value="30"></td>
					<!-- value는 기본값. type="number" 일 때는 min,max 속성 설정 가능.-->
				</tr>
				<tr>
					<td><label>주소</label></td>
					<td><select name="addr" id="addr_select">
							<!-- value 속성은 서버에 전달시킬 값 -->
							<option value="서울">서울</option>
							<option value="인천" selected>인천</option>
							<!-- selected 는 기본 선택 값. -->
							<option value="대전">대전</option>
							<option value="광주">광주</option>
							<option value="기타">기타</option>
					</select> 
					<span id="addr_id">
					<!-- disabled="disabled" 는 파라미터 값 전달에서 제외됩니다. -->
					<input type="text" name="addr_etc" disabled="disabled" 
							placeholder="기타 지역을 입력하세요." >
					</span></td>
				</tr>
				<tr>
					<td><label>성별</label></td>
					<td><input type="radio" value="male" name="gender">남자
						<input type="radio" value="female" name="gender" checked>여자
						<!-- checked  는 기본 선택 --></td>
				</tr>
				<tr>
					<td><label>취미</label></td>
					<td>
						<!--  checkbox 도  checked  는 기본 선택 --> <!--  --> <!-- 복수 선택이 가능 : hobby 이름이 같고 선택 여러개 하면 선택한 값은 배열로 저장됩니다. -->
						<input type="checkbox" name="hobby" value="축구">축구 <input
						type="checkbox" name="hobby" value="농구" checked>농구 <input
						type="checkbox" name="hobby" value="스키">스키 <input
						type="checkbox" name="hobby" value="달리기">달리기
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center"><input
						type="submit" value="가입하기"> <!-- submit : 서버에게 데이터를 제출 (form action 속성값에 설정된 url 로 데이터 전달.)
						action 이 .html 은 데이터 전송확인은 못합니다. url 이동만 확인!!--> <input
						type="reset" value="다시쓰기"></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	document.getElementById("addr_select").addEventListener("change",function(){
		if(this.value=="기타"){  //this는 getElementById("addr_select") 요소
//			document.getElementById("addr_id").style.display="inline-block";   //화면에 보임
			document.frmReg.addr_etc.disabled=false;
		}else {
//			document.getElementById("addr_id").style.display="none";		//화면에  안보임.
			document.frmReg.addr_etc.disabled=true;
		}
		
	});
	</script>
</body>
</html>