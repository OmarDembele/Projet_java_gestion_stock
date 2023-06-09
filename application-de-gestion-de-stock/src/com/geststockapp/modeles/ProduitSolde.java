package com.geststockapp.modeles;

public class ProduitSolde extends Produit {
	
	private int solde;
	private static int nbProduitSolde;
	
	public ProduitSolde(int id, String nom, double prix, int solde) {
		this(nom, prix, solde);
		this.setId(id);
		this.setSolde(solde);
	}

	public ProduitSolde(String nom, double prix, int solde) {
		super(nom, prix);
		this.setSolde(solde);
		
		nbProduitSolde ++;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		if (solde >= 10 && solde <= 70) this.solde = solde;
		else solde = 10;
	}

	@Override
	public double getPrix() {
		return prix * (1 - solde / 100.0);
	}
	
	public static int getNbProduitSolde() {
		return nbProduitSolde;
	}
}
