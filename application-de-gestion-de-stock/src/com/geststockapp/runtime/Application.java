package com.geststockapp.runtime;

import java.sql.Connection;
import javax.swing.JOptionPane;
import com.geststockapp.implement.DBManager;
import com.geststockapp.ui.MaFenetre;


public class Application {

	public static void main(String[] args) {
		MaFenetre connexion = new MaFenetre();
		connexion.showMe();
		
		try (Connection connection = DBManager.getConnection()) {
		} catch (Exception e) {
			JOptionPane.showMessageDialog (null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
		}

	}

}
