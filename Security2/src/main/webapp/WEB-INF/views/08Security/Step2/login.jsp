<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" 
	uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

	<h2>스프링 시큐리티 Step2</h2>
	
	<h3>로그인 화면</h3>
	
	<h4>누구나 권한없이 접근가능</h4>
	
	<c:url value="/login" var="loginUrl" />
	<form:form name="loginFrm" action="${loginUrl }" 
		method="post">		
		<c:if test="${param.error != null }">
			<p>아이디와 패스워드가 잘못되었습니다.</p>
		</c:if>
		<c:if test="${param.login != null }">
			<p>로그아웃 하였습니다.</p>
		</c:if>
		
		
		<!-- 아래 폼의 이름을 작성할때에는 
		security-context.xml의 <security:form-login 
		항목에 지정한대로 해야한다. -->
		<p>
			아이디: <input type="text" name="id" 
				value="kosmo_" />
		</p>
		<p>
			패스워드: <input type="password" name="pass" />
		</p>
		<button type="submit" class="btn btn-danger">
			로그인하기
		</button>
	</form:form>

	<jsp:include page="/resources/common_link.jsp" />
</div>














</body>
</html>