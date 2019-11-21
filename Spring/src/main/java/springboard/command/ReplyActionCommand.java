package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;

public class ReplyActionCommand implements BbsCommandImpl{

	@Override
	public void execute(Model model) {
		System.out.println("ReplyActionCommand");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		SpringBbsDTO dto = (SpringBbsDTO)map.get("springBbsDTO");
		
		SpringBbsDAO dao = new SpringBbsDAO();
		
		dao.reply(dto);
		dao.close();
		
	}
	
}
