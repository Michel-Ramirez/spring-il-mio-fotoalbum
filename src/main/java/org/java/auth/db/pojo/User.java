package org.java.auth.db.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotBlank(message = "this flield is required")
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "this flield is required")
	private String surname;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "this flield is required")
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "this flield is required")
	private String password;

	public User() {

	}

	public User(String name, String surname, String email, String password) {

		setName(surname);
		setSurname(surname);
		setEmail(email);
		setPassword(password);

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getName() + " " + getSurname() + " " + getEmail();
	}
}