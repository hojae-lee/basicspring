<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body> 
<h2>Mybatis + Android API</h2>
<li>
	<a href="./android/memberList.do" target="_blank">
		회원정보 JSON - 전체보기(파라미터없음)
	</a>
</li>
<li> 
	<a href="./android/memberList.do?name=낙자사발" target="_blank">
		회원정보JSON - 이름지정(파라미터 1개)
	</a>
</li>
<li> 
	<a href="./android/memberLogin.do?id=test1&pass=1234" target="_blank">
		로그인 JSON 보기 (아이디:test1, 패스워드:1234) > 로그인성공
	</a>
</li>
<li>
	<a href="./android/memberLogin.do?id=test24&pass=14111" target="_blank">
		로그인 JSON 보기 (아이디:test24, 패스워드:14111) > 로그인실패
	</a>
</li>
<li>
	<a href="./android/memberRegist.do?id=test24&pass=14111&name=해벌레" target="_blank">
		회원가입.
	</a>
</li>
</body>
</html>
