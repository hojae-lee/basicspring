<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>pathVariable.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Form값받기</h2>
		
		<ul>
			<li>아이디: ${ memberid}</li>
			<li>이름: ${ memberpw}</li>
		</ul>
		
		<!-- 
			웹브라우저는 /로 구분되는 부분들을 디렉토리(경로)로 인식하므로
			현재 "./form/gildong/길동"의 경우에는 ../를 경로에 추가하여 상위
			디렉토리로 이동해야한다.
		 -->
		<div class="row" style="margin-top: 20px;">
			<h3>이미지 경로 확인</h3>
			<img src="../../images/4.png" alt="" />
		</div>
	</div>
</body>
</html>