<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>requestParam.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h2>Form값 받기</h2>
	
	<h3>@RequestParam어노테이션으로 파라미터 받기</h3>
	
	<ul>
		<li>아이디는 ${memberInfo.id }</li>
		<li>이름은 ${memberInfo.name }</li>
		<li>이메일은 ${memberInfo.email }</li>
		<li>비밀번호는 ${memberInfo.pw }</li>
	</ul>
</div>
</body>
</html>