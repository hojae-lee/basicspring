<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
/*
Geolocation : 위치정보서비스로서 브라우저가 현재 위도와 경도를 JS 코드에게 공급하는 서비스
  
  getCurrentPosition() : 현재위치얻기
  
  -사용법 : getCurrentPosition(
    현재위치가 파악되었을때 호출되는 콜백함수
	, 위치파악중 오류발생시 호출되는 콜백함수
	, 옵션(위치파악최대허용시간, 대기시간, 위치의정확도(생략가능));
  
  watchPosition() : 위치가 변경될때마다 알려주는 반복위치 서비스
  
  -사용법 : watchPosition(
	위치가 변경될때마다 호출되는 	콜백함수
	, 위치파악중 오류발생시 호출되는 콜백함수
	, 옵션(위와동일));
  
  clearWatch() : 반복위치 서비스 중단하기
  
  -사용법 : clearWatch(watchPosition의 참조변수);
  
  option항목에 대한 설명 : 3개의 값을 가진 전역객체. watchPosition의 세번째 매개변수로 전달되며, 생략가능함.
  
	-enableHighAccurcy : 정확도를 결정하는 Boolean타입의 값
	  true : 정확도가 높은 정보를 제공하지만 전력소비가 늘어나고 응답시간이 느려짐
	  false : 정확도는 낮아지지만 전력소비는 줄고 응답시간이 빨라짐
	
	-timeout : 위치값을 장치로부터 받을때까지의 대기시간(1000분의 1초)
	
	-maximumAge : 캐시된 위치값을 반환받을수 있는 대기시간. 0을 지정하면 캐시값을 사용하지 않고 항상 현재위치값을 수집함.
*/

	var span;
	window.onload = function() {
		span = document.getElementById("result");

		if (navigator.geolocation) {
			span.innerHTML = "Geolocation API를 지원합니다.";

			var options = {
				enableHighAccury : true,
				timeout : 5000,
				maximumAge : 3000
			};
			navigator.geolocation.getCurrentPosition(showPosition, showError,
					options);
		} else {
			span.innerHTML = "이 브라우저는 Geolocation API를 지원하지 않습니다.";
		}
	}

	var showPosition = function(position) {
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;
		span.innerHTML = "위도:" + latitude + ", 경도:" + longitude;

		document.getElementById("lat").value = latitude;
		document.getElementById("lng").value = longitude;
	}

	var showError = function(error) {
		switch (error.code) {
		case error.UNKNOWN_ERROR:
			span.innerHTML = "알수없는오류발생";
			break;

		case error.PERMISSION_DENIED:
			span.innerHTML = "권한이 없습니다.";
			break;
		case error.POSITION_UNAVAILABLE:
			span.innerHTML = "위치 확인불가";
			break;
		case error.TIMEOUT:
			span.innerHTML = "시간초과";
			break;
		}
	}
</script>
</head>
<body>

	<h2>Geolocation - 현재위치의 위도 경도 알아내기</h2>

	<fieldset>
		<legend>현재위치 - 위도 경도</legend>
		<span id="result"
			style="color: red; font-size: 1.5em; font-weight: bold;"></span>
	</fieldset>

	위도 :	<input type="text" id="lat" /> 
	경도 :	<input type="text" id="lng" />
</body>
</html>