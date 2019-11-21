<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
	$(function(){
		$('#loginBtn').click(function(){
			var f = document.loginForm;
			if(f.id.value==""){
				alert("아이디를 입력하세요");
				f.id.focus();
				return;
			}
			if(f.pass.value==""){
				alert("파스워드를 입력하세요");
				f.pass.focus();
				return;
			}
			
			$.ajax({
				url : "./loginAction.do",
				type : "post",
				contentType : 
					"application/x-www-form-urlencoded;charset:utf-8;",
				data : {
					id : $('#id').val(),
					pass : $('#pass').val()
				},
				dataType : "json",
				success : function(d){
					if(d.loginResult==0){
						alert(d.loginMessage);
					}
					else{
						//로그인 성공시 메시지 출력 후 리스트로 이동.
						alert(d.loginMessage);
						location.href = 'board.do';
					}
				},
				err : function(e){
					alert("실패"+e);
				}
			});
		});
	});
</script>
</head>
<body>
<div class="container">
	<h3>방령록(로그인)</h3>
	<c:choose>
		<c:when test="${not empty sessionScope.siteUserInfo }">
			<div class = "row" style="border:2px solid" #cccccc; padding:10px;">
			<h4>아이디 : ${sessionScope.siteUserInfo.id }</h4>
			<h4>이름 : ${sessionScope.siteUserInfo.name }</h4>
			<br /><br />
			 	<button class="btn btn-danger" 
					onclick="location.href='logout.do';">
					로그아웃
				</button>
				&nbsp;&nbsp;
				<button class="btn btn-primary" onclick="location.href='board.do'">
					방명록리스트
				</button>
			</div>
		</c:when>
		<c:otherwise>
			<span style="font-size:1.5em; color:red;">${LoginNG }</span>
			<form action="" name="loginForm" method="post">
				<input type="hid-den" name="backUrl" value="${param.backUrl }"/>
				<table class="table-bordered" style="width:50%;">
					<tr>
						<td><input type="text" class="form-control" tabindex="1" name="id" id="id" placeholder="아이디"/></td>
						<td rowspan="2" style="width: 80px;">
							<button type="button" id="loginBtn" class="btn btn-primary" style="height: 77px; width: 77px;" tabindex="3">로그인</button>
						</td>
					</tr>
					<tr>
						<td>
							<input type="password" class="form-control" name="pass" id="pass" placeholder="패스워드" tabindex="2" />
						</td>
					</tr>
				</table>
			</form>
		</c:otherwise>	
	</c:choose>
</div>
</body>
</html>