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
	<h2>Web Notifiaction01</h2>
	<button onclick="calculate();">버튼을 누르면 1초후에 WebNotification이 뜹니다.</button>
	
	<script type="text/javascript">
		/*
		HTML문서가 생성될 때 notification 기능에 대한 허용 여부를 확인한다.
		*/
		window.onload = function(){
			if(window.Notification){
				Notification.requestPermission();
			}
			else{
				alert("웹노티를 지원하지 않습니다.");
			}
		}
		
		function calculate(){
			setTimeout(function(){
				notify();
			}, 1000);
		}
		
		/*
		notify() 함수를 호출하여 브라우저에 알림을 출력한다. calculate()에서는 3초 후에 알림을 출력한다.
		*/
		
		function notify(){
			if(Notification.permission != 'granted'){
				alert("웹노티를 지원하지 않습니다.");
			}
			else{
				var notification = new Notification(
						'Title입니다.',
						{
							icon:
								'http://cfile201.uf.daum.net/image/235BFD3F5937AC17164572',
								body : '여긴내용입니다. 클릭하면 KOSMO로 이동합니다.'
							
						}		
				);
				
				//noti에 핸들러를 사용한다.
				notification.onclick = function(){
					window.open('http://www.ikosmo.co.kr');
				};
			}
		}
	</script>

	<ul>
		<li>웹노티 Browser compatibility - > https://develpoer.mozilla.org/ko/docs/Web/API/notifiaction</li>
		<li>Chrome, Firefow지원됨. IE지원안됨.</li>
	</ul>
</div>




</body>
</html>