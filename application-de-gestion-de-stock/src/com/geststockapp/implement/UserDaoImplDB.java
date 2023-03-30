package com.geststockapp.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.geststockapp.datasources.Dao;
import com.geststockapp.exceptions.DaoException;
import com.geststockapp.modeles.Administrateur;
import com.geststockapp.modeles.User;

public class UserDaoImplDB implements Dao<User>{

	@Override
	public void create(User obj) throws DaoException {
		try(Connection connection = DBManager.getConnection()){
			String query = "Insert into T_User(login, password, type) values(?,?,?)";
			
			PreparedStatement ps =connection.prepareStatement(query);
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getType());
			ps.executeUpdate();
		}catch(Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void update(User obj) throws DaoException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Update T_User Set login=?, password=?, type=? Where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(4, obj.getId());
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			if(obj.getType().equals("ADMIN")) {
				ps.setString(3, "ADMIN");
			}
			else if(obj.getType().equals("SIMPLE")) {
				ps.setString(3, "SIMPLE");
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
			String query="Delete from T_User where id=?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			}
		catch (Exception e) {
			throw new DaoException(e.getMessage());
		}	
	}

	@Override
	public List<User> list() throws DaoException {
		List<User> users = new ArrayList<>();
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From T_User";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String login = rs.getString("login");
				String password = rs.getString("password");
				String type = rs.getString("type");
				User u ; 
				if(type.equals("SIMPLE")) {
					u = new User (id, login, password);
				}
				else {
					u=new Administrateur(id,login, password);
				}
				users.add(u);
			}
		} 
		catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
		return users;
	}

	@SuppressWarnings("unused")
	@Override
	public User read(int id) throws DaoException {		
		try (Connection connection = DBManager.getConnection()) {
				String query = "Select * From T_User where id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.first()) {
					int id1 = rs.getInt("id");
					String login = rs.getString("login");
					String password = rs.getString("password");
					String type = rs.getString("type");
					User users = new User(id1, login, password);
					return users;
				}
			} 
		catch (Exception e) {
			throw new DaoException(e.getMessage());
			}
		return null;
	}

}
