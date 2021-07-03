package com.skilldistillery.film.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FilmController {
//	@Autowired
//	private FilmDAO filmDao;
	
	@RequestMapping(path = {"/","home.do"})
	public String index() {
		
		
		
		return "WEB-INF/home.jsp";
	}
}
