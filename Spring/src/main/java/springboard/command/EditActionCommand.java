package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;

public class EditActionCommand implements BbsCommandImpl{
	
	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		//커맨드객체(SpringBbsDTO) 가져오기
		SpringBbsDTO springBbsDTO = (SpringBbsDTO)map.get("springBbsDTO");
		
		//폼값확인
		System.out.println("[command]springBbsDTO[내용]="+springBbsDTO.getContents());
		
		//커넥션풀을 이용한 DAO
		SpringBbsDAO dao = new SpringBbsDAO();
		
		dao.edit(springBbsDTO);
		
	}

}
