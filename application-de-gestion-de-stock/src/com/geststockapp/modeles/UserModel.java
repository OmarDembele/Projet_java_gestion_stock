package com.geststockapp.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class UserModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private Vector<Object []> rows = new Vector<Object []>();
	private List<User> users= new ArrayList<User>();
	protected String [] columns = {"Id", "Login", "Password"};
	

	@Override
	public int getRowCount() {	
		return rows.size();
	}

	@Override
	public int getColumnCount() {		
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[] obj = rows.get(rowIndex);
		return obj[columnIndex];
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columns[arg0];
	}
	
	public void setUser(List<User> user) {
		clear();
		this.users.addAll(user);
		for(User user1 : users) {
			rows.add( new Object [] {
					user1.getId(),
					user1.getLogin(), 
					user1.getPassword(), 
				});
		}
		
		fireTableDataChanged();
		
	}
	
	public void clear () {
		rows.clear();
		users.clear();
		
		fireTableDataChanged();
	}
	
	public List<User> getUser() { 
		return users; 
	}

}
