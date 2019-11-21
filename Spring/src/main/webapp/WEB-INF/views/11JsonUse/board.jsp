<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>board.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<script>
	$(function(){
		$('#boardHTML').html('');
		$('#boardHTML')
		.append('<div style="text-align:center;padding-top:50px;">')
		.append('<img src="../images/loading02.gif">')
		.append('</div>');
		
		//리스트가져오기
		
		$.ajax({
			url : "./aList.do", //요청경로(요청명)
			type : "get", //전송방식
			contentType : "text/html;charset:utf-8",
			data : {}, //파라미터
			dataType : "html" , //응답데이터형식
			success : function(d){
				//aList.do의 출력내용을 HTML형태로 아래 삽입
				$('#boardHTML').html(d);
			},
			error : function(e){ //실패시 골백메소드
				alert("실패"+e);
			}
		});
		
	});
</script>
<div class="container">
	<h3 class="text-center">방명록(한줄게시판)</h3>
		
	<!-- 방명록 반복 부분 s -->
	<div id="boardHTML">
		<h2 style="text-align:center;">
			여기에 게시판의 폼이 출력됩니다.
		</h2>
	</div>
	
</div>
</body>
</html>