package com.skilldistillery.film.entities;

import java.util.List;

public class Film {

	private int id;
	private String title;
	private String description;
	private String releaseYear;
	private String language;
	private String rentalDuration;
	private String rentalRate;
	private String length;
	private String replacementCost;
	private String category;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;

	public Film(int id, String title, String description, String releaseYear,  String rating) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rating = rating;
	}
	
	
	public Film(int id, String title, String description, String releaseYear, String language, String rentalDuration,
			String rentalRate, String length, String replacementCost, String category, String rating,
			List<Actor> actors) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.category = category;
		this.rating = rating;
		this.actors = actors;
	}
	
	public Film(int id, String title, String description, String releaseYear, String language, String rentalDuration,
			String rentalRate, String length, String replacementCost, String rating,String specialFeatures) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}
	
	public Film(String title, String description, String releaseYear, String language, String rentalDuration,
			String rentalRate, String length, String replacementCost, String rating,String specialFeatures) {

		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}

	public Film(String rentalDuration, String rentalRate, String length, String replacementCost, String category) {
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.category = category;
	}

	public Film(String title, String description, String rating, String releaseYear, String language,
			List<Actor> actors) {
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rating = rating;
		this.actors = actors;
	}


	public Film(int id, String title, String description, String rating, String releaseYear, String language,
			List<Actor> actors) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rating = rating;
		this.actors = actors;
	}

	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(String rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public String getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(String rentalRate) {
		this.rentalRate = rentalRate;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(String replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public String getSpecialFeatures() {
		return specialFeatures;
	}


	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		result = prime * result + ((rentalDuration == null) ? 0 : rentalDuration.hashCode());
		result = prime * result + ((rentalRate == null) ? 0 : rentalRate.hashCode());
		result = prime * result + ((replacementCost == null) ? 0 : replacementCost.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		if (rentalDuration == null) {
			if (other.rentalDuration != null)
				return false;
		} else if (!rentalDuration.equals(other.rentalDuration))
			return false;
		if (rentalRate == null) {
			if (other.rentalRate != null)
				return false;
		} else if (!rentalRate.equals(other.rentalRate))
			return false;
		if (replacementCost == null) {
			if (other.replacementCost != null)
				return false;
		} else if (!replacementCost.equals(other.replacementCost))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String toString(String type) {
		if (type.equals("additional")) {
			return (("| Rental Duration: " + rentalDuration + "\n| Rental Rate: " + rentalRate + "\n| Length: " + length
					+ "\n| Replacement Cost: " + replacementCost + "\n| Category: " + category));

		} else if (type.equals("short")) {
			return ("| ID: " + id + " Title: " + title);

		} else {
			return (("| Title: " + title + "\n| Description: \n" + description.replaceAll("(?m)^", "| ")
					+ "\n| Release Year: " + releaseYear + "\n| Rating: " + rating + "\n| Language: " + language
					+ "\n| Actors:" + actors.toString().replaceAll("\\[|\\]|,|-", "")));
		}

	}




}
