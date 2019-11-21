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
	<h2>상품입력</h2>
	<form action="insertAction.do" name="goodsFrm" method="post" >
		<table class="table table-bordered " style="width: 500px;">
			<colgroup>
				<col width="20%"/>
				<col width="*"/>
			</colgroup>
			<tr>
				<th>상품코드</th>
				<td>
					<select name="p_code">
						<option value="1">가전제품</option>
						<option value="2">도서</option>
						<option value="3">컴퓨터</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" name="goods_name" />
				</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>
					<input type="text" name="goods_price" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<button type="submit" class="btn btn-danger">등록하기</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>