<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Framework Start</title>
</head>
<body>
<!-- 
	Spring JDBC를 사용하기 위한 절차
	:웹 어플리케이션을 제작할떄 DB사용을 위해 매번 같은 동작을 반복하게 된다.
	드라이버로드, 커넥션생성 및 DB연결 SQL실행 등의 동작들인데 jDBCTemplate을 이용하면 이런
	반복적인 작업을 아주 짧은 코드로 처리할 수 있따.
	Spring Bean을 이용하여 Java코드를 간소화하고 DB관련 클래스들을 생성하고 조립할 수 있다.
	DI(의존주입) 개념을 이용한다.
	
	사용절차
	1. 
	Spring JDBC(JDBCTemplate)을 사용하기 위한 의존설정 
	pom.xml 의존설정 반드시 <dependencies> 엘리먼트 가장 뒷부분에 삽입한다.
   
	   <dependency>
	      <groupId>org.springframework</groupId>
	      <artifactId>spring-jdbc</artifactId>
	      <version>4.1.4.RELEASE</version>
	   </dependency>
	   
	2.
	스프링 설정 파일에서 JdbcTemplate을 사용하기 위한 빈을 생성한다.
	servlet-context.xml에서 설정한다.
	
	<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
      <beans:property name="driverClassName" value="oracle.jdbc.OracleDriver "/>
      <beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:orcl"/> 
      <beans:property name="username" value="오라클계정id"/>
      <beans:property name="password" value="패스워드"/>
   </beans:bean>
   <beans:bean name="template" class="org.springframework.jdbc.core.JdbcTemplate">
      <beans:property name="dataSource" ref="dataSource"/>
   </beans:bean>
   DB연결정보를 가지고 있는 dataSource빈을 1차로 생성한 후 JDBCTemplate클래스의 빈을 2차로 생성한다.
   해당 빈을 DAO에서 주입(Injection) 받아서 사용한다.
	
	3. 컨트롤러에서 빈을 주입받기 위해 멤버변수와 setter()를 추가한다.
	여기서의 타입은 JDBCTemplate 이다.
	
	4. setter()에 @Autowired 어노테이션을 적용하여 빈을 자동으로 주입받는다.
	
	※Autowired 어노테이션
	-의존관계를 자동으로 설정해주는 역할을 한다. (type기반)
	-생성자,필드,메소드에 적용 가능하다.
	-setXXX()의 형식이 아니어도 적용이 가능하다.
	-타입을 이용해 자동적으로 프로퍼티의 값을 설정하므로 해당 타입의 빈객체가 존재하지 않거나
	또는 2개이상 존재할경우 예외가 발생되므로 주의해야 한다.
	
	5. JdbcTemplate을 웹어플리케이션 전체에서 사용하기 위해서 static(정적)타입의 변수를 생성 후 빈을
	할당한다.
	
	6. 해당 빈을 통해 query(), queryForObject(), update()메소드를 통해 DB작업을 진행한다.
 -->
<h1>
	Spring JDBC 사용하기
</h1>
<h3>Spring 게시판 만들기2 - JDBCTemplate사용하기</h3>

<li>
	<a href="board/list.do" target="_blank">
		게시판 리스트 바로가기
	</a>
</li>
</body>
</html>
