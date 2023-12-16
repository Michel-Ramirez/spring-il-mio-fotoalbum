package org.java.db.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.java.auth.db.pojo.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 128)
	@NotBlank(message = "this field is required")
	@Length(min = 3, message = "The description must be longer than 3 characters")
	private String name;

	// ----------| RELAZIONI |------------//
	@ManyToMany(mappedBy = "categories")
	private List<Picture> pictures;

	public List<Picture> getPictures() {
		return pictures;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// GETTEN & SETTER
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Category() {

	}

	public Category(String name, User user) {
		setName(name);
		setUser(user);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {

		return "[" + getId() + "] " + getName();
	}

}
