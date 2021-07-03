package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.film.dao.FilmDAO;

@Controller
public class FilmController {
//	@Autowired
//	private FilmDAO filmDao;
	
	@RequestMapping(path = {"/","home.do"})
	public String index() {
		
		
		
		return "WEB-INF/home.jsp";
	}
}
