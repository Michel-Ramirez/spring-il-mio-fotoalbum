package org.java.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.java.auth.db.pojo.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 128)
	@NotBlank(message = "this field is required")
	@Length(min = 5, message = "The description must be longer than 5 characters")
	private String title;

	@Column(columnDefinition = "TEXT")
	@Length(min = 5, message = "The description must be longer than 5 characters")
	private String description;

	@Column(columnDefinition = "TEXT")
	@URL(message = "unvalid URL")
	private String img;

	private boolean visible;

	// -----// RELAZIONE //-----//

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToMany
	private List<Category> categories;

	@JsonProperty
	public List<Category> getCategories() {
		return categories;
	}

	@JsonIgnore
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCategories(Category... categories) {
		setCategories(Arrays.asList(categories));
	}

	// PICTURES //

	public Picture() {
	}

	public Picture(String title, String description, String img, boolean visible, User user, Category... categories) {

		setTitle(title);
		setDescription(description);
		setImg(img);
		setVisible(visible);
		setCategories(categories);
		setUser(user);

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getTitle() + "," + getDescription() + ", " + getImg() + ", " + isVisible();
	}

}
