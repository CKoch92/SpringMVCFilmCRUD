package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	{
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
	
	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student",
				"student");
// NOT WORKING Can't retreive new created film data:
//		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rental_rate, "
//				+ "film.rental_duration, film.length, film.replacement_cost, category.name, language.name,"
//				+ "film.rating\n" + "FROM film  JOIN film_category \n" + "ON film.id = film_category.film_id\n"
//				+ "JOIN category \n" + "ON film_category.category_id = category.id\n" + "JOIN language \n"
//				+ "ON film.language_id = language.id \n" + "WHERE film.id = ?";
		
		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rental_rate, film.rental_duration, film.length, film.replacement_cost, film.language_id, film.rating FROM film WHERE film.id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		Film film = null;
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			String wrappedDescription = description;
			String releaseYear = rs.getString("release_year");
			String language = rs.getString("film.language_id");
			String rentalDuration = rs.getString("rental_duration");
			String rentalRate = rs.getString("rental_rate");
			String length = rs.getString("length");
			String replacementCost = rs.getString("replacement_cost");
			
			List<String> category = findCategoryByFilmId(id);
			
			
			
			
			String rating = rs.getString("film.rating");
			List<Actor> actors = findActorsByFilmId(id);

			film = new Film(id, title, wrappedDescription, releaseYear, language, rentalDuration, rentalRate, length, replacementCost, category, rating, actors);
		}
		rs.close();
		stmt.close();
		conn.close();
		return film;
	}

	@Override
	public Film filmAdditionalInfo(int filmId) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student",
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
			List<String> category = findCategoryByFilmId(filmId);
			film = new Film(rentalDuration, rentalRate, length, replacementCost, category);
		}
		rs.close();
		stmt.close();
		conn.close();
		return film;
	}

	public List<Film> findFilmByKeyword(String keyword) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student",
				"student");
//
//		NOT WORKING:
//		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rental_rate, "
//				+ "film.rental_duration, film.length, film.replacement_cost, category.name, language.name,"
//				+ "film.rating\n" + "FROM film  JOIN film_category \n" + "ON film.id = film_category.film_id\n"
//				+ "JOIN category \n" + "ON film_category.category_id = category.id\n" + "JOIN language \n"
//				+ "ON film.language_id = language.id \n" + "WHERE film.title LIKE ? OR film.description LIKE ?";
		
		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rental_rate, film.rental_duration, film.length, film.replacement_cost, film.language_id, film.rating FROM film WHERE film.title LIKE ? OR film.description LIKE ?";

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
			String wrappedDescription = description;
			String releaseYear = rs.getString("release_Year");
			String language = rs.getString("film.language_id");
			String rentalDuration = rs.getString("rental_duration");
			String rentalRate = rs.getString("rental_rate");
			String length = rs.getString("length");
			String replacementCost = rs.getString("replacement_cost");
			List<String> category = findCategoryByFilmId(id);
			String rating = rs.getString("film.rating");
			List<Actor> actors = findActorsByFilmId(id);

			film = new Film(id, title, wrappedDescription, releaseYear, language, rentalDuration, rentalRate, length, replacementCost, category, rating, actors);
			films.add(film);
		
		}
		rs.close();
		stmt.close();
		conn.close();
		return films;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student",
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
	public List<String> findCategoryByFilmId(int filmId) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student",
				"student");
		
		String sql = "SELECT film.id, film.title, category.name FROM film JOIN film_category ON film.id = film_category.film_id "
				+ "JOIN category ON film_category.category_id = category.id WHERE film.id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		List<String> categories = new ArrayList<>(Arrays.asList("",""));

		while (rs.next()) {
			String category = rs.getString("category.name");
			categories.add(category);
		}
		rs.close();
		stmt.close();
		conn.close();
		return categories;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student",
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
	@Override
	public Film createFilm(Film film) throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student", "student");
		String title = film.getTitle();
		String description = film.getDescription();
		String releaseYear = film.getReleaseYear();
		String language = film.getLanguage();
		String rentalDuration = film.getRentalDuration();
		String rentalRate = film.getRentalRate();
		String length = film.getLength();
		String replacementCost = film.getReplacementCost();
		String rating = film.getRating();
		String specialFeatures = film.getSpecialFeatures();

		String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration,"
				+ " rental_rate, length, replacement_cost, rating, special_features) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		Connection conn = null;

		Film newFilm = null;
		try {
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, title);
			st.setString(2, description);
			st.setString(3, releaseYear);
			st.setString(4, language);
			st.setString(5, rentalDuration);
			st.setString(6, rentalRate);
			st.setString(7, length);
			st.setString(8, replacementCost);
			st.setString(9, rating);
			st.setString(10, specialFeatures);
			System.out.println(st);


			int uc = st.executeUpdate();
			if (uc != 1) {
				System.err.println("Ruh Roh!");
				conn.rollback();
				return null;
			}
			ResultSet keys = st.getGeneratedKeys();
			int id = 0;
			if (keys.next()) {
				id = keys.getInt(1);
			}
			sql = "SELECT * FROM film WHERE film.id = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
			 newFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("release_year"), rs.getString("language_id"), rs.getString("rental_duration"), 
					 rs.getString("rental_rate"),rs.getString("length"), rs.getString("replacement_cost"), rs.getString("rating"), rs.getString("special_features"));
			}
			rs.close();
			conn.commit();
			st.close();
			conn.close();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			return null;
		}
		return newFilm;
	}
	@Override
	public Film updateFilm(Film film) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student", "student");
		try {
			conn.setAutoCommit(false);
			String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, "
					+ "rating=?, special_features=? WHERE id=?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setString(3, film.getReleaseYear());
			st.setString(4, film.getLanguage());
			st.setString(5, film.getRentalDuration());
			st.setString(6, film.getRentalRate());
			st.setString(7, film.getLength());
			st.setString(8, film.getReplacementCost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecialFeatures());
			st.setInt(11, film.getId());

			int uc = st.executeUpdate();
			if (uc != 1) {
				System.err.println("Ruh Roh!");
				conn.rollback();
				return null;
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
	
	@Override
	public boolean deleteFilm(Film film) {
		Connection conn = null;
		int filmId = film.getId(); 
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
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
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain", "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION
		String sql = "SELECT * FROM film WHERE film.id = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, filmId);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return false;
		}
		rs.close();
		conn.commit();
		st.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
		return true;
	}


}
