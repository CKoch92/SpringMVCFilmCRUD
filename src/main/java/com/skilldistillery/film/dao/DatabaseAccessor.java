package com.skilldistillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface DatabaseAccessor {
	public Film findFilmById(int filmId) throws SQLException;

	public Actor findActorById(int actorId) throws SQLException;

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException;

	Film filmAdditionalInfo(int filmId) throws SQLException;

	public List<Film> findFilmByASearchKeyword(String keyword) throws SQLException;
	
	public Film createFilm(Film film) throws SQLException;

}
