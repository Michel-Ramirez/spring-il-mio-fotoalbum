package org.java.auth.db.pojo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User implements UserDetails {

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
//	@NotBlank(message = "this flield is required")
	private String username;

	@Column(nullable = false, unique = true)
	@NotBlank(message = "this flield is required")
	private String email;

	@Column(nullable = false)
	@NotBlank(message = "this flield is required")
	private String password;

	// RELAZIONE TRA GLI USE E I RUOLI
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	// METODO CON LO SPRED OPERATOR, RICEVO UNA LISTA DI RUOLI E LA SETTO
	public void setRoles(Role... roles) {
		setRoles(List.of(roles));
	}

	public User() {

	}

	public User(String name, String surname, String username, String email, String password, Role... roles) {

		setName(surname);
		setSurname(surname);
		setUsername(username);
		setEmail(email);
		setPassword(password);
		setRoles(roles);

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	private void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getUsername() + " " + getName() + " " + getSurname() + " " + getEmail();
	}
}