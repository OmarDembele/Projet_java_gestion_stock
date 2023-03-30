package com.geststockapp.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.geststockapp.datasources.Dao;
import com.geststockapp.exceptions.DaoException;
import com.geststockapp.modeles.Produit;
import com.geststockapp.modeles.ProduitSolde;

public class ProduitDaoImplDB implements Dao<Produit> {
	
	@Override
	public void create(Produit obj) throws DaoException {
		try(Connection connection = DBManager.getConnection()){
			String query = "Insert into T_Produit(id,nom, prix, solde) values(?,?,?,?)";
			
			PreparedStatement ps =connection.prepareStatement(query);
			ps.setInt(1, (int)obj.getId());
			ps.setString(2, obj.getNom());
			ps.setDouble(3, (double)obj.getPrix());
			if(obj instanceof ProduitSolde) {
				ProduitSolde p =(ProduitSolde)obj;
				ps.setInt(4, p.getSolde());
			}
			else {
				ps.setInt(4, 0);
			}
			ps.executeUpdate();
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void update(Produit obj) throws DaoException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Update T_Produit Set nom=?, prix=?, solde=? Where id=?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(4, obj.getId());
			ps.setString(1, obj.getNom());
			ps.setDouble(2,obj.getPrix());
			if(obj instanceof ProduitSolde) {
				ProduitSolde p =(ProduitSolde)obj;
				ps.setInt(3, p.getSolde());
			}
			else {
				ps.setInt(3, 0);
			}
			ps.executeUpdate();
			} 
		catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws DaoException {
		try(Connection connection = DBManager.getConnection()){
			String query="Delete from T_Produit where id=?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			}
		catch (Exception e) {
			throw new DaoException(e.getMessage());
		}	
	}

	@Override
	public List<Produit> list() throws DaoException {
		List<Produit> produits = new ArrayList<>();
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From T_Produit";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				double prix = rs.getDouble("prix");
				int solde = rs.getInt("solde");
				Produit u;
				if(solde == 0) {
					u = new Produit (id, nom, prix);
				}else {
					u = new ProduitSolde (id, nom, prix, solde);
				}
			produits.add(u);
			}
		} 
		catch (Exception e) {
		throw new DaoException(e.getMessage());
		}
		return produits;
	}

	@Override
	public Produit read(int id) throws DaoException {		
		try (Connection connection = DBManager.getConnection()) {
				String query = "Select * From T_Produit where id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.first()) {
					int id1 = rs.getInt("id");
					String nom = rs.getString("nom");
					double prix = rs.getDouble("prix");
					
					Produit produits = new Produit(id1,nom, prix);
					return produits;
				}
			} 
		catch (Exception e) {
			throw new DaoException(e.getMessage());
			}
		return null;
	}
}
	
	