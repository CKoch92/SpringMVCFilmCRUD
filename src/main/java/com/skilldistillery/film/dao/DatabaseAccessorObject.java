package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false", "student",
				"student");

		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rental_rate, "
				+ "film.rental_duration, film.length, film.replacement_cost, category.name, language.name,"
				+ "film.rating\n" + "FROM film  JOIN film_category \n" + "ON film.id = film_category.film_id\n"
				+ "JOIN category \n" + "ON film_category.category_id = category.id\n" + "JOIN language \n"
				+ "ON film.language_id = language.id \n" + "WHERE film.id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		Film film = null;

		while (rs.next()) {

			int id = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");

//			WordWrapper is a Maven dependency, keeps the film description neat. 
//			Commented out because not working with Gradle.

//			String wrappedDescription = WordWrap.from(description).maxWidth(40).insertHyphens(true).wrap();
			String wrappedDescription = description;

			String releaseYear = rs.getString("release_Year");
			String language = rs.getString("language.name");
			String rating = rs.getString("film.rating");
			List<Actor> actors = findActorsByFilmId(id);

			film = new Film(title, wrappedDescription, rating, releaseYear, language, actors);
		}
		rs.close();
		stmt.close();
		conn.close();
		return film;
	}

	@Override
	public Film filmAdditionalInfo(int filmId) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false", "student",
				"student");

		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rental_rate, "
				+ "film.rental_duration, film.length, film.replacement_cost, category.name, language.name,"
				+ "film.rating\n" + "FROM film  JOIN film_category \n" + "ON film.id = film_category.film_id\n"
				+ "JOIN category \n" + "ON film_category.category_id = category.id\n" + "JOIN language \n"
				+ "ON film.language_id = language.id \n" + "WHERE film.id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		Film film = null;

		while (rs.next()) {

			String rentalDuration = rs.getString("rental_duration");
			String rentalRate = rs.getString("rental_rate");
			String length = rs.getString("length");
			String replacementCost = rs.getString("replacement_cost");
			String category = rs.getString("category.name");

			film = new Film(rentalDuration, rentalRate, length, replacementCost, category);
		}
		rs.close();
		stmt.close();
		conn.close();
		return film;
	}

	public List<Film> findFilmByASearchKeyword(String keyword) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false", "student",
				"student");

		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rental_rate, "
				+ "film.rental_duration, film.length, film.replacement_cost, category.name, language.name,"
				+ "film.rating\n" + "FROM film  JOIN film_category \n" + "ON film.id = film_category.film_id\n"
				+ "JOIN category \n" + "ON film_category.category_id = category.id\n" + "JOIN language \n"
				+ "ON film.language_id = language.id \n" + "WHERE film.title LIKE ? OR film.description LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		String search = "%" + keyword + "%";
		stmt.setString(1, search);
		stmt.setString(2, search);
		ResultSet rs = stmt.executeQuery();

		List<Film> films = new ArrayList<Film>();
		Film film = null;

		while (rs.next()) {

			int id = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");
//			WordWrapper is a Maven dependency, keeps the film description neat. 
//			Commented out because not working with Gradle.

//			String wrappedDescription = WordWrap.from(description).maxWidth(40).insertHyphens(true).wrap();
			String wrappedDescription = description;
			String releaseYear = rs.getString("release_Year");
			String language = rs.getString("language.name");
			String rating = rs.getString("film.rating");
			List<Actor> actors = findActorsByFilmId(id);

			film = new Film(id, title, wrappedDescription, rating, releaseYear, language, actors);
			films.add(film);
		}
		rs.close();
		stmt.close();
		conn.close();
		return films;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false", "student",
				"student");
		String sql = "SELECT actor.id, actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "JOIN film ON film_actor.film_id = film.id WHERE actor.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet rs = stmt.executeQuery();

		Actor actor = null;

		while (rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			actor = new Actor(id, firstName, lastName);
		}
		rs.close();
		stmt.close();
		conn.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false", "student",
				"student");
		String sql = "SELECT actor.id, actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "JOIN film ON film_actor.film_id = film.id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		List<Actor> actors = new ArrayList<Actor>();
		Actor actor = null;

		while (rs.next()) {
			int actorId = rs.getInt("actor.id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			actor = new Actor(actorId, firstName, lastName);
			actors.add(actor);
		}
		rs.close();
		stmt.close();
		conn.close();
		return actors;
	}

	public Film createFilm(Film film) throws SQLException {

		String title = film.getTitle();
		String description = film.getDescription();
		String rating = film.getRating();
		String release_year = film.getReleaseYear();
		String language = film.getLanguage();

		String sql = "INSERT INTO film (title, description, rating, release_year, language_id) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false", "student", "student");
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, title);
			st.setString(2, description);
			st.setString(3, rating);
			st.setString(4, release_year);
			st.setString(5, language);

			System.out.println(st);

			int uc = st.executeUpdate();
			System.out.println(uc + " Film record created.");
			if (uc != 1) {
				System.err.println("Ruh Roh!");
				conn.rollback();
				return null;
			}
			ResultSet keys = st.getGeneratedKeys();
			int filmID = 0;
			if (keys.next()) {
				filmID = keys.getInt(1);
				System.out.println("New film ID: " + filmID);
			}
			sql = "SELECT * FROM film WHERE film.id = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, filmID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("title") + " " + rs.getString("description")
						+ " " + rs.getString("rating") + " " + rs.getString("release_year") + " "
						+ rs.getString("language_id"));
			}
			conn.commit();
			st.close();
			conn.close();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			return null;
		}
		return film;
	}

	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false", "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			int updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

}
