package com.geststockapp.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ProduitModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private Vector<Object[]> rows = new Vector<Object[]>();
	private List<Produit> produits = new ArrayList<Produit>();
	protected String [] columns = {"Id", "Nom", "Prix", "Solde (yes or No)"}; 
	
	@Override	
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Object [] obj = rows.get(rowIndex);
		return obj[columnIndex];
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columns[arg0];
	}

	public void setProduits(List<Produit> produits) {
		clear ();
		this.produits.addAll(produits);
		for (Produit produit : produits ) {
			rows.add( new Object [] {
				produit.getId(),
				produit.getNom(), 
				produit.getPrix(), 
				produit instanceof ProduitSolde ? "Yes" : "No"
			});
		}

		fireTableDataChanged();
	}
	
	public void clear () {
		rows.clear();
		produits.clear();
		
		fireTableDataChanged();
	}
	
	public List<Produit> getProduits() { 
		return produits; 
	}
	
	public void removeRow(int i){
		 rows.remove(i);
		 produits.remove(i);
		 
		 fireTableDataChanged();
		 }
	
	public int getNbreProduit() {
		int nombre=0;
		for(Produit x : produits) {
			if(x instanceof Produit) {
				 nombre++;	 
			}
		}
		return nombre;
		
	}
	
	public int getNbreProduitSolde() {
		int nombre=0;
		for(Produit x : produits) {
			if(x instanceof ProduitSolde) {
				nombre++;
			}
		}
		return nombre;
	}
}
