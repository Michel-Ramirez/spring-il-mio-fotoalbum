package org.java.db.pojo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotBlank(message = "this field is required")
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	private String email;

	@Column(nullable = false, columnDefinition = "TEXT")
	@NotBlank(message = "this field is required")
	private String message;

	private boolean message_read;

	@Column(columnDefinition = "TIMESTAMP")
	private String data_recived;

	public Message() {

	}

	public Message(String name, String email, String message) {

		setName(name);
		setEmail(email);
		setMessage(message);
		setMessage_read(message_read);
		setData_recived(LocalDateTime.now().toString());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMessage_read() {
		return message_read;
	}

	public void setMessage_read(boolean message_read) {
		this.message_read = message_read;
	}

	public String getData_recived() {

		return data_recived;
	}

	public void setData_recived(String data_recived) {

		this.data_recived = data_recived;
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getName() + ", " + getEmail();
	}

}
