package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;

public class ReplyCommand implements BbsCommandImpl{
	
	@Override
	public void execute(Model model) {
	
		//request 한꺼번에 전달받기
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		String idx = req.getParameter("idx");
		
		//커넥션풀을 이용한 DAO
		SpringBbsDAO dao = new SpringBbsDAO();
		SpringBbsDTO dto = dao.view(idx);
		
		//제목처리
		dto.setTitle("[RE]"+dto.getTitle());
		//내용처리
		dto.setContents("\n\r\n\r---[원본글]---\n\r"+ dto.getContents());
		
		model.addAttribute("replyRow", dto);
		dao.close();
	}

}
