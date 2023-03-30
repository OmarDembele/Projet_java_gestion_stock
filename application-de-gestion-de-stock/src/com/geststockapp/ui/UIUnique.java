package com.geststockapp.ui;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class UIUnique extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private FlowLayout flowLayout_1;
	private JButton registerButton;
	private JButton StockButton;
	
	
	public UIUnique() {
		setSize(new Dimension(393, 195));
		setTitle("Redirect");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		createInstanceComponent();
		initComponent();
	}

		private void createInstanceComponent() {
			panel = new JPanel();
			panel_1 = new JPanel();
			panel_2 = new JPanel();
			flowLayout_1 = (FlowLayout) panel_1.getLayout();
			registerButton = new JButton("Register");
			StockButton = new JButton("Gestion de Stock");
		
	}

		private void initComponent() {
			panel.setPreferredSize(new Dimension(300, 150));
			getContentPane().add(panel);
			panel.setLayout(new GridLayout(2, 1, 0, 0));
			
			
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(20);
			panel.add(panel_2);
			
			flowLayout_1.setHgap(20);
			flowLayout_1.setVgap(20);
			panel.add(panel_1);
			
			
			registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			registerButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					onRegisterClicked();
					
				}
			});
			registerButton.setPreferredSize(new Dimension(140, 35));
			panel_2.add(registerButton);	
			
			
			StockButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			StockButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					onStockClicked();
					
				}
			});
			StockButton.setPreferredSize(new Dimension(140, 35));
			panel_1.add(StockButton);
			
		}

		private void onStockClicked() {
			UIProductManager uiProd = new UIProductManager();
			uiProd.lancer();
			this.dispose();
		}

		private void onRegisterClicked() {
			UIUserManager uiUser2= new UIUserManager();
			uiUser2.run();
			this.dispose();
			
		}	
		
		public void show4() {
			this.setVisible(true);
			
		}
		
		
	}

