<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>reply.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h2>비회원제 게시판 - 글수정 폼</h2>
	
	<form action="./replyAction.do" name="writeFrm" method="post" onsubmit="return checkValidate(this);">
		<input type="hid-den" name="idx" value="${replyRow.idx }" />
		<input type="hid-den" name="nowPage" value="${param.nowPage }" />
		<input type="hid-den" name="bgroup" value="${replyRow.bgroup }" />
		<input type="hid-den" name="bstep" value="${replyRow.bstep }" />
		<input type="hid-den" name="bindent" value="${replyRow.bindent }" />
		<table border="1" width=800>
		<colgroup>
			<col width="25%" />
			<col width="*" />
		</colgroup>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="name" style="width:50%" value=""/>
				</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td>
					<input type="password" name="pass" style = "width:30%;" />
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" style="width:90%" value="${replyRow.title }"/>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="contents" style="width:90%; height: 200px;">${replyRow.contents }</textarea>
				</td>
			</tr> 
			<tr>
				<td colspan="2" align = "center"> 
					<button type="submit">작성완료</button>
					<button type="reset">reset</button>
					<button type="button" onclick="location.href='./list.do';">리스트바로가기</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>