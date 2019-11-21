package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;

public class EditCommand implements BbsCommandImpl{

	
	@Override
	public void execute(Model model) {
		
		//파라미터 한번에 받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String idx = req.getParameter("idx");
		
		//커넥션품을 사용한 DAO
		SpringBbsDAO dao = new SpringBbsDAO();
		
		SpringBbsDTO dto = dao.view(idx);
		model.addAttribute("viewRow",dto);
		dao.close();
		
	}
}
