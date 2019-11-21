<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>modelAttribute.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h2>@RequestMapping어노테이션</h2>
	
	<h3>@ModelAttrubute 어노테이션을 사용하여 파라미터 일괄전송</h3>
	
	<ul>
		<li>이름 : ${si.name }</li>
		<li>나이 : ${si.age }</li>
		<li>학년 : ${si.gradeNum }</li>
		<li>학번 : ${si.classNum }</li>
	</ul>
</div>
</body>
</html>