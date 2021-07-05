package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

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

	@RequestMapping(path = "createFilm.do", params = { "title", "description", "releaseYear", "language",
			"rentalDuration", "rentalRate", "length", "replacementCost", "rating", "specialFeatures" })
	public ModelAndView createFilm(String title, String description, String releaseYear, String language,
			String rentalDuration, String rentalRate, String length, String replacementCost, String rating,
			String specialFeatures) {

		Film film = new Film(title, description, releaseYear, language, rentalDuration, rentalRate, length,
				replacementCost, rating, specialFeatures);
		Film newFilm = null;
		ModelAndView mv = new ModelAndView();
		try {
			newFilm = filmDao.createFilm(film);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("newFilm", newFilm); // Film Object f is added to mv and given the reference name, film
		mv.setViewName("WEB-INF/createFilm.jsp"); // mv (with Film f) is sent to findFilmDetailsFromID.jsp
		return mv;
	}

	@RequestMapping(path = "GetFilmFromKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView getFilmFromKeyword(String keyword) {
		Film film = null;
		List<Film> films = null;
		ModelAndView mv = new ModelAndView();
		try {
			films = filmDao.findFilmByKeyword(keyword);
			System.out.println(film);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("films", films);
		mv.setViewName("WEB-INF/filmDetailsFromKeyword.jsp");
		return mv;
	}

	@RequestMapping(path = "updateFilm.do", params = { "title", "description", "releaseYear", "language",
			"rentalDuration", "rentalRate", "length", "replacementCost", "rating", "specialFeatures",
			"filmID" }, method = RequestMethod.GET)
	public ModelAndView updateFilmByID(String title, String description, String releaseYear, String language,
			String rentalDuration, String rentalRate, String length, String replacementCost, String rating,
			String specialFeatures, int filmID) {
		Film film = new Film(filmID, title, description, releaseYear, language, rentalDuration, rentalRate, length,
				replacementCost, rating, specialFeatures);

		ModelAndView mv = new ModelAndView();
		try {
			film = filmDao.updateFilm(film);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/updatedFilm.jsp");
		return mv;
	}

	@RequestMapping(path = "editFilm.do", params = {"filmID", "action"}, method = RequestMethod.GET)
	public ModelAndView editFilm(int filmID, String action) {
		Film film = null;
		ModelAndView mv = new ModelAndView();

		if (action.equals("Edit")) {
		try {
			film = filmDao.findFilmById(filmID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/updateFilm.jsp");
		}
		else if (action.equals("Delete") ) {
<<<<<<< HEAD
			try {
			filmDao.deleteFilm(filmDao.findFilmById(filmID));
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			mv.addObject("film", film);
			mv.setViewName("WEB-INF/deleted.jsp");
			// Handle Film Delete
=======
// -----------------
			try {									
				film = filmDao.findFilmById(filmID);
				System.out.println(film);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			boolean deleted = filmDao.deleteFilm(film);
			
			if (deleted) {
				mv.addObject("film", film);			
				mv.setViewName("WEB-INF/deleted.jsp");		
			} else {
				film = null;
				mv.addObject("film", film);			
				mv.setViewName("WEB-INF/deleted.jsp");	
			}
 
// -----------------
>>>>>>> ed8bef9a10c460962a06235d79cbced7ed7600ec

		}
		return mv;
	}

	@RequestMapping(path = "goHome.do", params = {}, method = RequestMethod.GET)
	public ModelAndView goHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/home.jsp");
		return mv;
	}
<<<<<<< HEAD

	@RequestMapping(path = "DeleteFilm.do", params = "id", method = RequestMethod.GET)
	public ModelAndView deleteFilm(String id) {
	  ModelAndView mv = new ModelAndView();
	  Film f = null;
	  int userID = Integer.parseInt(id);
	  try {
	    f = filmDao.findFilmById(userID);
	    System.out.println(f);
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  filmDao.deleteFilm(f);
	  try {
	    f = filmDao.findFilmById(userID);
	    System.out.println(f);
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }
	  mv.addObject("film", f);
	  mv.setViewName("WEB-INF/deleteFilm.jsp");
	  return mv;
	}
=======
	
>>>>>>> ed8bef9a10c460962a06235d79cbced7ed7600ec
}
