package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.DatabaseAccessorObject;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private DatabaseAccessorObject filmDao;

	public void setFilmDAO(DatabaseAccessorObject filmDao) {
		this.filmDao = filmDao;
	}

	@RequestMapping(path = { "/", "home.do" })
	public String index() {
		return "WEB-INF/home.jsp";
	}

	@RequestMapping(path = "GetFilmFromID.do", params = "id", method = RequestMethod.GET) // RequestMapping matches form
																							// // filmDetailsFromID.html
	public ModelAndView getFilmFromID(String id) { // takes in params
		Film f = null;
		int userID = Integer.parseInt(id); // parse from string to int, so it can be sent into findFilmByID()
		ModelAndView mv = new ModelAndView();
		try { // findFilmByID throws exception, so it is wrapped in try/catch
			f = filmDao.findFilmById(userID);
			System.out.println(f);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("film", f); // Film Object f is added to mv and given the reference name, film
		mv.setViewName("WEB-INF/filmDetailsFromID.jsp"); // mv (with Film f) is sent to findFilmDetailsFromID.jsp
		return mv;
	}

	@RequestMapping(path = "createFilm.do", params = {"title", "description", "rating", "releaseYear"}, method = RequestMethod.GET) // RequestMapping
																															// //
																															// filmDetailsFromID.html
	public ModelAndView createFilm(String title, String description, String rating, String releaseYear) { // takes in
																											// params
		String language = "1";
		List<Actor> actors = null;
		Film film = new Film(title, description, rating, releaseYear, language, actors);
		Film newFilm = null;

		ModelAndView mv = new ModelAndView();
		try { // findFilmByID throws exception, so it is wrapped in try/catch
 newFilm = filmDao.createFilm(film);
//System.out.println(newFilm.toString("short"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("newFilm", newFilm); // Film Object f is added to mv and given the reference name, film
		mv.setViewName("WEB-INF/createFilm.jsp"); // mv (with Film f) is sent to findFilmDetailsFromID.jsp
		return mv;
	}

}
