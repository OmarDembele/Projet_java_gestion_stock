package com.geststockapp.modeles;

public class Administrateur extends User {

	public Administrateur(int id, String login, String password) {
		super(id, login, password);
	}
	
	public Administrateur( String login, String password) {
		super(login, password);
	}
	
	public String getType() {
		return "ADMIN";
	}

}
