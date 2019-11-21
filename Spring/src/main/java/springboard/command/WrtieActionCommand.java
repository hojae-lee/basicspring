package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;

public class WrtieActionCommand implements BbsCommandImpl{

	@Override
	public void execute(Model model) {
		//파라미터 한번에 전달받기
		
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		SpringBbsDTO springBbsDTO = (SpringBbsDTO)paramMap.get("springBbsDTO");
		

			//폼값받기
			String name = req.getParameter("name");
			String title = req.getParameter("title");
			String contents = req.getParameter("contents");
			String pass = req.getParameter("pass");
			
			//커맨드객체 받아서 확인하기
			System.out.println("springBbsDTO.title="+springBbsDTO.getTitle());
			
			//커넥션풀 사용한 DAO
			SpringBbsDAO dao = new SpringBbsDAO();
			dao.write(springBbsDTO);
			dao.close();
		
	}
}
