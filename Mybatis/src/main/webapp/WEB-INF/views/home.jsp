<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Framework Start</title>
</head>
<body>
<!-- 
	1.pom.xml 파일에 의존설정
	
		<dependency>
	      <groupId>org.mybatis</groupId>
	      <artifactId>mybatis</artifactId>
	      <version>3.2.8</version>
	   </dependency>   
	        
	   <dependency>
	      <groupId>org.mybatis</groupId>
	      <artifactId>mybatis-spring</artifactId>
	      <version>1.2.2</version>
	   </dependency>        
	
	2. 스프링 설정파일 servlet-context.xml에서 mybatis 빈을 생성한다.
   <beans:bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean" >
      
      1번속성 : DB연결을 위한 dataSource 빈을 참조함
      <beans:property name="dataSource"  ref="dataSource"/>
      
      2본속성 : mapper파일의 위치를 설정함. 아래는 코드로
         mybatis.mapper 패키지 하위의 모든 xml 파일을 Mapper로 설정함.
      <beans:property name="mapperLocations"
         value="classpath:mybatis/mapper/*.xml" />
   </beans:bean>
   <beans:bean id="sqlSession" 
      class="org.mybatis.spring.SqlSessionTemplate" >
      <beans:constructor-arg index="0"
         ref="sqlSessionFactory" />
   </beans:bean>
   	
   	3. 컨트롤러에서 빈을 자동주입 받는다.
   	
   	@Autowired
   	private Sqlsession sqlSession;
   	
   	4.컨트롤러와 Mapper파일의 중간 매개체 역할을 하는 Interface 생성 및 추상메소드 정의
   	
   	5. Mapper 파일 생성
   	-mapperLocations로 지정된 패키지에 xml파일을 생성
   	-<Doctype 추가
   	-mapper 엘리먼트의 namespace 속성에 연결할 Interface를 패지키를 포함한 전체경로를 기술함
   	-실제 CRUD작업을 위해서
   		<select id="호출할함수명" resultType="반환타입" parameterType="매개변수의 타입명"
   		※ 반환타입을 명시할때 주의사항
   			기본자료형인경우 : int,double과 같이 타입명만 명시한다.
   			클래스 인경우 : 패키지명을 포함한 전체경로를 사용한다.
   	
   	Mapper에서 파라미터 사용방법
   	방법1 : param1, param2...
   		1부터 순차적으로 증가하는 형태
   		select * from 테이블 where 컬럼=#{param1} and 컬럼=#{param2}
   		
   	방법2 : 0, 1 ...
   		0부터 시작하는 인덱스를 사용하는 형태
   		select * from 테이블 where 컬럼=#{0} and 컬럼=#{1}
   		
   	방법3 : 파라미터명의 별칭을 부여하는 @Param 어노테이션을 사용하는 형태
   		호출을 받는 Interface의 추상메소드 정의시
   		public void 함수명 (@Param("매퍼에서 사용할 이름") String 파라미터명);
   		
   		Mapper에서는 select * from 테이블 where 컬럼= #{매퍼에서 사용할 이름}
   		
   	방법4 : DTO객체를 사용하는 방법 
   		호출을 받는 Interface의 추상메소드 정의시
   		public void 함수명 (@Param("매퍼에서 사용할 이름") String 파라미터명);
   		
   		Mapper에서는 
   		<select parameterType="DTO객체의 풀경로">
   			select * from 테이블 where 컬럼= #{매퍼에서 사용할 이름}
   		</select>
   		멤버변수명만 명시하면 getter를 통해 값을 가져옴.
 -->
	<h2>Mybatis를 이용한 한줄게시판(방명록) 제작하기</h2>
	<li>
		<a href="mybatis/list.do" target="_blank">
			앙가자미
		</a>  
	</li>
</body>
</html>
