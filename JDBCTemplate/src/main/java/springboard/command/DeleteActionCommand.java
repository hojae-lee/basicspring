package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBbsDAO;

public class DeleteActionCommand implements BbsCommandImpl{

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		//폼값받기
		String idx = req.getParameter("idx");
		String pass = req.getParameter("pass");
		
		//커넥션풀을 이용한 DAO
		//SpringBbsDAO dao = new SpringBbsDAO();
		
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		dao.delete(idx, pass);
		
	}
	
}
