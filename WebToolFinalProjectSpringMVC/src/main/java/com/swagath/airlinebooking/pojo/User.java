package com.swagath.airlinebooking.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airlineuser")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id", unique = true, nullable = false)
	long user_id;

	@Column(name = "username")
	String username;

	@Column(name = "password")
	String password;

	@Column(name = "role")
	String role;

	public User() {

	}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
