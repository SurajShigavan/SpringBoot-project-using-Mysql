package com.app.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String userInput;
    
    
    @Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userInput=" + userInput
				+ "]";
	}
	public User(Long id, String username, String password, String userInput) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userInput = userInput;
	}


	
    
	public String getUserInput() {
		return userInput;
	}
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}

