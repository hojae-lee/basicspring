package springboard.model;

import org.springframework.jdbc.core.JdbcTemplate;

/*
 	JDBCTemplate 을 웹어플리케이션 어디서나 사용할 수 있도록 하기 위해 
 	static(정적) 변수로 생성한다. 
 */
public class JdbcTemplateConst {

	public static JdbcTemplate template;
	
}
