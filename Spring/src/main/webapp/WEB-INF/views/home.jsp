<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Spring Framework Start</title>
</head>
<body>
	<h2>스프링 MVC 시작하기</h2>
	
	<h3>리소스 폴더 사용하기</h3>
	<!-- Spring MVC에서는 이미지와 같은 리소스를 사용하기 위해 별도로 resources폴더를
	제공한다. 해당 폴더의 설정은 servlet-context.xml에서 한다. -->
	<ul>
		<li>
			<img src="./resources/1.png" width="150" alt="스펀지밥" />
		</li>
		<li>
			<img src="./images/2.png" width="150" alt="징징이" />
		</li>
		<li>
			<img src="./resources/3.png" alt="뚱이" />
		</li>
		<li>
			<img src="./resources/4.png" width="150" alt="개두마리" />
		</li>
		
		<!-- 
		리소스 사용을 위한 resources폴더의 기본 매핑명은 resources이지만 엘리먼트
		추가로 별칭을 변경하거나 추가할 수 있다.
		
		<resoruces mapping="/images/**" location="/resoures/" />
		이와같이 해당 폴더에 images라는 별칭을 부여함.
		 -->
	</ul>
	
	<h3>First 컨트롤러 만들기</h3>
	<ul>
		<li>
			<a href="./home/helloSpring" target="_blank">
				첫번째 컨트롤러 바로가기
			</a>
		</li>
	</ul>
	
	<!-- FormController.java -->
	<h3>Form값 처리하기</h3>
	<li>
		<a href="./form/servletRequest?id=kosmo&pw=1234" target="_blank">
			/form/servletRquest폼값 받기
		</a>
	</li>
	<li>
		<a href="./form/requestParam.do?name=홍길동&id=hong&email=hong@gildong.com&pw=1234" target="_blank">
			@RequestParam 어노테이션으로 폼값받기		
		</a>
	</li>
	
	<li>
		<a href="form/commandObjGet.do?name=홍길동&id=hong&email=hong@gildong.com&pw=1234" target="_blank">
			Command(커맨드) 객체로 한번에 폼값받기		
		</a>
	</li>
	<li>
		<a href="form/gildong99/홍길동" target="_blank">
			pathVariable 어노테이션으로 폼값받기.
		</a>
	</li>
	
	<!-- 컨트롤러 : RequestMappingController.java -->
	<h3>@RequestMapping 어노테이션 활용</h3>
	
	<li>
		<a href="./requestMapping/index.do" target="_blank">
			requestMapping 시작페이지 바로가기
		</a>	
	</li>
	
	<!-- 컨트롤러 : ValidateController.java -->
	<h2>폼데이터 검증하기 - Validator</h2>
	<li>
		<a href="validate/memberRegist.do" target="_blank">
			회원가입바로가기
		</a>
	</li>
	
	<!-- 컨트롤러 : DIController.java -->
	<h3>DI(Dependency Injection) : 의존성주입</h3>
	<li>
		<a href="di/myCalculator" target = "_blank">
			간단한 사칙연산 계싼기
		</a>
	</li>
	
	<h3>Spring 게시판 만들기1 - DBCP사용</h3>
	<li>
		<a href="board/list.do" target="_blank">
			게시판 리스트 바로가기
		</a>
	</li>
	
	<h3>파일업로드</h3>
	<li>
		<a href="./fileUpload/uploadPath.do" target="_blank">
			물리적경로 확인하기
		</a>
	</li>
	<li>
		<a href="./fileUpload/uploadForm.do" target="_blank">
			파일업로드 폼
		</a>
	</li>
	<li>
		<a href="./fileUpload/uploadList.do" target="_blank">
			파일목록 보기
		</a>
	</li>
	
	<!--  
        트랜잭션의 개념
        - 인터넷뱅킹의 경우 A가 B에게 송금을 진행하는 경우 A에서는 출금되었으나 B에게는 입금이 되지 않은
        상황이 발생된다면 해당 거래는 취소되어야 한다.
        - 이와같이 양쪽 모두 만족되어야 하나의 프로세스를 완료처리 할 수 있도록 해주는 기법을 트랜잭션이라고 한다.
        - 즉 2개 이상의 쿼리를 하나의 커넥션으로 묶어 DB에 전송하고 이 과정에서 에러가 발생하는 경우 양쪽 모두를
        원래의 상태도 되돌려놓는다.
        
        절차
        1. 트랜잭션 매니저를 이용하는 방법 및 트랜잭션 템플릿을 이용하는
        방법에 따른 빈 생성 -> servelt-context.xml 참조
        
        2. 트랜잭션 매니저를 사용하는 경우 : TicketDAO 빈 자동주입
        3. 트랜잭션 템플릿을 사용하는 경우 : TicketTplDAO 빈 자동주입
        4. 2개이상의 DB작업이 있는 경우 모두 다 정상처리 되었을때 commit 된다.
     -->
     <h2>트랜잭션(Transaction)</h2>
     <li>
        <a href="./transaction/buyTicketMain.do" target="_blank">
           티켓구매하기 1
        </a>
     </li>
     <li>
        <a href="./transaction/buyTicketTpl.do" target="_blank">
           티켓구매하기 2
        </a>
     </li>
     
     <!-- 
     1.pom.xml 의존설정
     	<dependency>
      	<groupId>com.fasterxml.jackson.core</groupId>
      	<artifactId>jackson-databind</artifactId>
      	<version>2.5.1</version>
      </dependency>
      
      2.컨트롤러에서 매핑시 @ResponseBody 어노테이션을 추가한다.
      
      3.JSON객체는 Map컬렉션으로 JSON배열은 ArrayList컬렉션으로 생성한다.
      
      4. map컬렉의 참조변수를 반환한다.
      -->
     <h2>Spring Framework에서 JSON 활용하기(RestAPI)</h2>
     <li>
     	<a href="./jsonUse/jsonView.do" target="_blank">
     		@ResponseBody 어노테이션 이용한 JSON데이터 보기
     	</a>
     </li>
     <li>
     	<a href="./jsonUse/board.do" target="_blank">
     		ajax와 json을 활용한 방명록 게시판
     	</a>
     </li>
     
     <!-- 
     	AOP 구현절차
     	
     	1.pox.xml의존설정
     	<dependency>
	         <groupId>org.aspectj</groupId>
	         <artifactId>aspectjweaver</artifactId>
	         <version>1.7.4</version>
   		</dependency>
   		
   		2. AOP설정파일 생성
   		XML 설정파일 생성시 AOP 네임스페이스를 추가함.
   		<app:config>
   			<aop:aspect ref="공통기능을 수행할 클래스"
   				<aop:pointcut expression="공통기능 적용범위"
   				<aop:around(혹은 before 등) : advice 실행방식 설정>
   				
   		3. 공통기능을 수행할 클래스 생성
   		
   		4. 핵심기능을 수행할 클래스 생성 및 핵심기능 구현
   		
   		5. XML 설정파일을 기반으로 컨테이너 생성 후 핵심기능에 해당하는 빈을 주입받아
   		핵심기능 실행 및 공통기능 실행
      -->
     
     <h2>AOP(Aspect Oriented Programming : 관점지향프로그래밍)</h2>
     <li>
     	<a href="aop/main.do" target="_blank">
     		AOP Main 바로가기
     	</a>
     </li>
     <li>
     	<a href="aop/insert.do" target="_blank">
     		AOP 상품등록 바로가기
     	</a>
     </li>

</body>
</html>
