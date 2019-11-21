package com.kosmo.k11actualex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	// 내 위치값 알아내기
	@RequestMapping(value = "/01GeoLocation/01GeoLocation.do", method = RequestMethod.GET)
	public String geoFunc1() {

		return "01GeoLocation/01GeoLocation";

	}
	
	// 구글맵연동
	@RequestMapping(value = "/01GeoLocation/02GoogleMap.do", method = RequestMethod.GET)
	public String geoFunc2() {
		
		return "01GeoLocation/02GoogleMap";
		
	}
	
	// 구글맵에 내위치 출력하기
	@RequestMapping(value = "/01GeoLocation/03MyLocation.do", method = RequestMethod.GET)
	public String geoFunc3() {
		
		return "01GeoLocation/03MyLocation";
		
	}
	
	//웹노티피케이션
	@RequestMapping("/Notification/01WebNoti.do")
	public String webNoti() {
		return "02Notification/01WebNoti";
	}

}
