package com.geststockapp.modeles;

import com.geststockapp.exceptions.BadFormatException;
import com.geststockapp.exceptions.MissingDataException;

public class User {
	private int id;
	private String login;
	private String password;
	private String confirmPassword;
	
	public User( int id, String login, String password) {
		this.login = login;
		this.password = password;
		setId(id);
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password; 
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public  String getType() {
		return "SIMPLE"; 
	}
	
	public boolean authentification(String login, String password) throws BadFormatException , MissingDataException {
		if(login==null || login.length()==0 || password==null || password.length()==0 )
		   throw new MissingDataException("Veuillez renseigner correctement les informations");
		
		if(this.password.equals(this.confirmPassword))
			   throw new MissingDataException("Veuillez renseigner correctement les informations");
		
		if(login.length() <=3 || password.length()<=3)
		     throw new BadFormatException("Mauvais format! La taille doit etre superieure à 3");
		        
		return this.login.equals(login) && this.password.equals(password);
	}

	
}

