package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TicketDAO {
   
   //스프링JDBC사용
   JdbcTemplate template;
   public void setTemplate(JdbcTemplate template) {
      this.template = template;
   }
   
   /*
   트랜잭션 처리를 위한 멤버변수와 세터 설정
    */
   PlatformTransactionManager transactionManager;
   public void setTransactionManager(PlatformTransactionManager transactionManager) {
      this.transactionManager = transactionManager;
   }
   
   //기본생성자
   public TicketDAO() {System.out.println("TicketDAO생성자 호출 : "+template);}
   
   //티겟구매를 위한 메소드
   public void buyTicket(final TicketDTO dto) {
	   System.out.println("buyTicket()메소드 호출");
	   System.out.println(dto.getCustomerId() + "님이 "+"티켓 "+dto.getAmount()+"장을 "+"구매합니다.");
	   
	   TransactionDefinition def = new DefaultTransactionDefinition();
	   TransactionStatus status = transactionManager.getTransaction(def);
	   
	   try {
		   //결제금액처리
		   template.update(new PreparedStatementCreator() {
			
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into transaction_pay (customerId, amount) values (?,?)";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, dto.getCustomerId());
					//티켓1장에 만원
					psmt.setInt(2, dto.getAmount()*10000);
					return psmt;
				}
		   });
		   
		   template.update(new PreparedStatementCreator() {
			
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into transaction_ticket (customerId, countNum) values (?,?)";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, dto.getCustomerId());
					psmt.setInt(2, dto.getAmount());
					return psmt;
				}
		   });
		   System.out.println("카드결제와 티멧구매 모두 정상처리되었습니다.");
		   transactionManager.commit(status);
	   } 
	   catch (Exception e) {
		   //countNum이 5이상 일떄는 모든 작업이 취소처리됨.
		System.out.println("제약조건을 위배하여 카드결제와 티켓구매 모두가 취소 되었습니다.");
		transactionManager.rollback(status);
	   }
   }

}