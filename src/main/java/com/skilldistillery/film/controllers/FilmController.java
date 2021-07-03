package com.skilldistillery.film.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.DatabaseAccessorObject;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private DatabaseAccessorObject filmDao;
	
	public void setFilmDAO(DatabaseAccessorObject filmDao) {
		this.filmDao = filmDao;
	}
	
	@RequestMapping(path = {"/","home.do"})
	public String index() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping(path = "GetFilmFromID.do", 
			params = "id", method = RequestMethod.GET) //RequestMapping matches form from filmDetailsFromID.html
	public ModelAndView getFilmFromID(String id) { //takes in params
		Film f = null;
		int userID = Integer.parseInt(id);  //parse from string to int, so it can be sent into findFilmByID()
		ModelAndView mv = new ModelAndView();
		try {									//findFilmByID throws exception, so it is wrapped in try/catch
			f = filmDao.findFilmById(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("film", f);			//Film Object f is added to mv and given the reference name, film
		mv.setViewName("WEB-INF/findFilmDetailsFromID.jsp");  //mv (with Film f) is sent to findFilmDetailsFromID.jsp
		return mv;
	}
}
