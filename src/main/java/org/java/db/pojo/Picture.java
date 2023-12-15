package org.java.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

	@URL(message = "unvalid URL")
	private String img;

	@NotNull(message = "this field is required")
	private boolean visible;

	// -----// RELAZIONE //-----//

	@ManyToMany
	@JsonProperty
	private List<Category> category;

	public List<Category> getCategories() {
		return category;
	}

	@JsonIgnore
	public void setCategories(List<Category> category) {
		this.category = category;
	}

	public void setCategories(Category... categories) {
		setCategories(Arrays.asList(categories));
	}

	// PICTURES //

	public Picture() {
	}

	public Picture(String title, String description, String img, boolean visible, Category... categories) {

		setTitle(title);
		setDescription(description);
		setImg(img);
		setVisible(visible);
		setCategories(categories);

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
