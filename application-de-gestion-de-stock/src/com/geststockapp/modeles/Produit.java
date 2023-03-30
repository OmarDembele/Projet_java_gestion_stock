package com.geststockapp.modeles;

public class Produit {
	protected int id;
	protected String nom;
	protected double prix;
	
	private static int nbProduit;

	public Produit(int id, String nom, double prix) {
		this (nom, prix);
		this.id = id;
	}

	public Produit(String nom, double prix) {
		this.nom = nom;
		this.setPrix(prix);
		
		nbProduit ++;
		setId(nbProduit);
		
	}

	public Produit() {
		this("noname", 0.0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		if (prix >= 0) this.prix = prix;
	}
	
	public static int getNbProduit() {
		return nbProduit;
	}
}