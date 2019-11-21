<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<style>
ul {
	list-style-type:none;
}
ul li{
	float:left;
	border:1px solid #888888;
	padding-right:10px;
}
</style>
<ul>
	<li><a href="${pageContext.request.contextPath}
		/">홈 바로가기</a></li>
	<li><a href="${pageContext.request.contextPath}
		/security2/index.do">프론트 페이지 바로가기</a></li>
	<li><a href="${pageContext.request.contextPath}
		/security2/admin/main.do">관리자 모드 바로가기</a></li>
	<li><a href="${pageContext.request.contextPath}
		/security2/accessDenied.do">접근불가 페이지 바로가기</a></li>
</ul>   

