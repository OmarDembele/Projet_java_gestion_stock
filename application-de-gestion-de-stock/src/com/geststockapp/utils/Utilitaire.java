package com.geststockapp.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Utilitaire {
	/**
	 * @param component
	 * Cette fonction configure le look and feel de l'application.
	 */
	public static void setLookAndFeel (Component component) { 
		try { 
		   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		   // force chaque composant de la fen�tre à appeler sa m�thode updateUI 
		   SwingUtilities.updateComponentTreeUI(component);
		} catch (InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException ignored) {}
	}
	
	/**
	 * @param component
	 * @param dimension
	 * Cette fonction positionne le composant au centre de l'�cran.
	 */
	public static void center (Component component, Dimension dimension) {
		component.setSize(dimension);
		
		// getScreenSize() method returns the size of the screen(ecran) in pixels
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// getSize() method returns the size of the component in pixels
		Dimension frameSize = component.getSize();

		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;

		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;

		component.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}
}
