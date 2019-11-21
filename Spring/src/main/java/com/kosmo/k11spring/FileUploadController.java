package com.kosmo.k11spring;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	//파일업로드폼
	@RequestMapping("/fileUpload/uploadPath.do")
	public void uploadPath(HttpServletRequest req, HttpServletResponse resp) {

		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		resp.setContentType("text/html; charset=utf-8");
		
		try {
			PrintWriter pw = resp.getWriter();
			pw.println("/upload 폴더의 물리적 경로 출력");
			pw.println(path);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/fileUpload/uploadForm.do")
	public String uploadForm() {
		return "09FileUpload/uploadForm";
	}
	
	//파일업로드 처리 메소드
	@RequestMapping("/fileUpload/uploadAction.do")
	public String uploadAction(HttpServletRequest req, Model model) {
      
		//서버의 물리적 경로 가져오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
      
		//뷰로 전달할 데이터 저장용 Map
		Map returnObj = new HashMap();
      
		try {
         /*
         파일업로드를 위한 Multipart객체를 생성한다. 생성돠
         동시에 업로드는 완료되고, 나머지 폼값은 multipart가
         통째로 받아서 처리한다.
          */
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
         
			Iterator itr= mhsr.getFileNames();
         
			MultipartFile mfile =null;
			String fileName = "";
         
			List resultList = new ArrayList();
         
			String title = mhsr.getParameter("title");
         
			File directory = new File(path);
			if (!directory.isDirectory()) {
				directory.mkdirs();
			}
			while(itr.hasNext()) {
				fileName = (String)itr.next();
            
				//서보로 업로드된 임시파일명 가져오기
				mfile = mhsr.getFile(fileName);
				System.out.println("mfile+"+mfile);
            
				//한글깨짐 방지 처리후 업로드된 파일명을 가져옴
				String originalName = new String(mfile.getOriginalFilename().getBytes(),"UTF-8");
            
				if("".equals(originalName)) {
					continue;
				}
            
				//파일의 확장자 가져오기
				String ext = originalName.substring(originalName.lastIndexOf('.'));
	            //UUID를 통해 생성된 문자열과 확장자 조립.
	            String saveFileName = getUuid() + ext;
	            
	            File serverFullName = new File(path+File.separator+saveFileName);
	            
	            mfile.transferTo(serverFullName);
	            
	            Map file = new HashMap();
	            file.put("originalName", originalName);//원본파일명
	            file.put("saveFileName", saveFileName);//저장된파일명
	            file.put("serverFullName", serverFullName);//서버에 저장된 전체경로 및 파일명
	            file.put("title", title);//타이틀
	            
	            //Map에 저장된 정보를 List에 추가함.
	            resultList.add(file);
            
			}
			returnObj.put("files", resultList);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
      
		model.addAttribute("returnObj",returnObj);
      
		return "09FileUpload/uploadAction";
      
   }
	
	/*
	 UUID(Universally Unique Indentifier)
	 	: 범용 고유 식별자 randomUUID() 메소드를 통해 문자열을 생성하면
	 	하이폰이 4개 포함된 32자의 랜덤한 문자열이 생성된다.
	 	cf4e96b-4a35-344d-fb7b-30eccf8045945
	 	※JDK에서 기본제공되는 클래스임.
	 */
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString();
		uuid =uuid.replaceAll("-", "");
		System.out.println("생성된UUID:"+uuid);
		return uuid;
   }
	
	//파일목록보기
	@RequestMapping("/fileUpload/uploadList.do")
	public String uploadList(HttpServletRequest req, Model model) {
		//물리적 경로 가져오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		//경로를 통해 File객체 생성
		File file = new File(path);
		//해당 경로의 파일목록 가져오기
		File[] fileArray = file.listFiles();
		/*
		 파일정보 저장을 위해 Map컬렉션 생성.
		 Key값은 파일명, value는 파일의 크기 저장.
		 */
		Map<String,Integer> fileMap = new HashMap<String, Integer>();
		
		for(File f : fileArray) {
			fileMap.put(f.getName(), (int)Math.ceil(f.length()/1024.0));
		}
		
		model.addAttribute("fileMap",fileMap);
		
		return "09FileUpload/uploadList";
	}
	
	//파일다운로드
	@RequestMapping("/fileUpload/download.do")
	public ModelAndView download(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		String fileName = req.getParameter("fileName");
		String oriFileName = req.getParameter("oriFileName");
		
		String saveDirectory = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		File downloadFile = new File(saveDirectory+"/"+fileName);
		
		if(!downloadFile.canRead()) {
			throw new Exception("파일을 찾을 수 없습니다.");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fileDownloadView");
		mv.addObject("downloadFile", downloadFile);
		mv.addObject("oriFileName", oriFileName);
		return mv;
		
		
	}
	
}
