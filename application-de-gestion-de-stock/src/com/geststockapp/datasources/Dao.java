package com.geststockapp.datasources;

import java.util.List;

import com.geststockapp.exceptions.DaoException;

public interface Dao<T> {
	
	public void create (T obj) throws DaoException;
	
	public T read(int id) throws DaoException;
	
	public void update (T obj) throws DaoException;
	
	public void delete (int id) throws DaoException;
	
	public List<T> list() throws DaoException;
	
	
}
