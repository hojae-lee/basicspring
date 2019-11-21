<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUploadMain.jsp</title>
<script>
	function isValidate(f) {
		if(f.filename.value==""){
			alert("파일1을 선택하세요");
			return false;
		}
	}
</script>
</head>
<body>
	<h2>파일 업로드 폼Test</h2>
	
	<form action="FileUpload.jsp" name="fileFrm" method="post" enctype="multipart/form-data" onsubmit="return isValidate(this)">
		
		첨부파일1 :
			<input type="file" name='filename' />
			<br />
			<input type="submit" value="파일업로드GOGO"/>
	</form>
</body>
</html>