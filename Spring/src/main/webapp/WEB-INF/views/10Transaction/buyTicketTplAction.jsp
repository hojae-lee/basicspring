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
</head>
<body>
<div class="container">
	<h2>트랜잭션</h2>
	<h3>트랜잭션템플릿 사용한 구매결과보기</h3>
	
	<span style="color: red; font-size: 1.5em;">
		${successOrFail }
	</span>
	<ul>
		<li>고객아이이디 : ${ticketInfo.customerId }</li>
		<li>티켓구매수 : ${ticketInfo.amount }</li>
	</ul>
	<p>
		<a href="./buyTicketTpl.do">
			티켓구매페이지 바로가기
		</a>
	</p>
</div>
</body>
</html>