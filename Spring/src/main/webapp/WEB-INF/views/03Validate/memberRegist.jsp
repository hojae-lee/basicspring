<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>memberRegist.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h2>Validator 인터페이스를 구현한 폼값 검증</h2>
	
	<span style="color: red; font-size: 1.5em;">${formError }</span>
	
	<script>
	function registCheck(){
		//스프링에서 제공하는 클래스를 통해 검증하기 때문에 구색만 갖춤.,..
	}
	</script>
	<form action="./registProc" method="post" name="registFrm" onsubmit="return registCheck();">
		<table class="table table-bordered" style="width:500px;">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="${mInfo.id }"/></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="pw" value="${mInfo.pw }" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${mInfo.name }" /></td>
			</tr>
			<tr>
				<td class="text-center" colspan="2">
					<button type="submit">앙가입띠!</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>