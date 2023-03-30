package com.geststockapp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.geststockapp.datasources.Dao;
import com.geststockapp.exceptions.DaoException;
import com.geststockapp.implement.UserDaoImplDB;
import com.geststockapp.modeles.Administrateur;
import com.geststockapp.modeles.User;
import java.awt.Rectangle;


public class MaFenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelLogin;
	private JPanel panelButton;
	private JPanel panelMessage;
	private JPanel panelPassword;
	private JPanel loginPanel;
	private JPanel panelPass;
	private JPanel panel;
	private JLabel loginLabel;
	private JLabel passwordLabel;
	private JLabel labelLogo;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JTextField textFieldMessage;
	private JButton buttonValider;
	private JButton buttonQuitter;
	private Dao<User> dataSourceU;
	
	public MaFenetre() {
		createInstanceComponent();
		initComponent();
	}
	private void createInstanceComponent(){
		panelLogin = new JPanel();
		panelButton = new JPanel();
		panelMessage = new JPanel();
		panelPassword = new JPanel();
		panelPass = new JPanel();
		panel = new JPanel();
		loginPanel = new JPanel();
		loginLabel = new JLabel("Login");
		passwordLabel = new JLabel("Password");
		labelLogo = new JLabel("");
		textFieldMessage = new JTextField();
		buttonValider = new JButton("Valider");
		buttonQuitter = new JButton("Quitter");
		dataSourceU= new UserDaoImplDB();
	}
	
	private void initComponent() {
		setSize(new Dimension(450, 300));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Connexion");
		getContentPane().setLayout(null);
		
		
		panelLogin.setBounds(160, 10, 276, 39);
		FlowLayout fl_panelLogin = (FlowLayout) panelLogin.getLayout();
		fl_panelLogin.setAlignment(FlowLayout.LEFT);
		fl_panelLogin.setVgap(10);
		fl_panelLogin.setHgap(10);
		getContentPane().add(panelLogin);
		
		
		loginLabel.setHorizontalAlignment(SwingConstants.LEFT);
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLogin.add(loginLabel);
		
		
		panelButton.setBounds(0, 162, 436, 53);
		FlowLayout fl_panelButton = (FlowLayout) panelButton.getLayout();
		fl_panelButton.setVgap(10);
		fl_panelButton.setHgap(10);
		getContentPane().add(panelButton);
		
		panelMessage.setBackground(new Color(240, 240, 240));
		panelMessage.setBounds(0, 217, 436, 46);
		getContentPane().add(panelMessage);
		
		
		textFieldMessage.setPreferredSize(new Dimension(54, 30));
		textFieldMessage.setMinimumSize(new Dimension(125, 200));
		textFieldMessage.setMaximumSize(new Dimension(150, 250));
		textFieldMessage.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMessage.setSize(new Dimension(141, 251));
		textFieldMessage.setBounds(new Rectangle(94, 11, 262, 47));
		panelMessage.add(textFieldMessage);
		textFieldMessage.setColumns(40);
		
		panelPassword.setBounds(160, 85, 276, 46);
		FlowLayout fl_panelPassword = (FlowLayout) panelPassword.getLayout();
		fl_panelPassword.setVgap(10);
		fl_panelPassword.setHgap(10);
		fl_panelPassword.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelPassword);
		
	
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelPassword.add(passwordLabel);
		
		loginPanel.setBounds(160, 49, 276, 31);
		FlowLayout fl_loginPanel = (FlowLayout) loginPanel.getLayout();
		fl_loginPanel.setHgap(10);
		fl_loginPanel.setAlignment(FlowLayout.LEFT);
		getContentPane().add(loginPanel);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldLogin.setHorizontalAlignment(SwingConstants.LEFT);
		loginPanel.add(textFieldLogin);
		textFieldLogin.setColumns(25);
		
		
		panelPass.setBounds(160, 125, 276, 39);
		FlowLayout fl_panelPass = (FlowLayout) panelPass.getLayout();
		fl_panelPass.setVgap(10);
		fl_panelPass.setHgap(10);
		fl_panelPass.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelPass);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordField.setColumns(25);
		panelPass.add(passwordField);
		

		panel.setBounds(0, 10, 160, 154);
		getContentPane().add(panel);
		
		labelLogo.setIcon(new ImageIcon("C:\\Users\\rokya\\Documents\\java\\projet java\\demo-app1-fenetre\\media\\user3.png"));
		labelLogo.setSize(new Dimension(128, 128));
		panel.add(labelLogo);
	
		buttonValider.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelButton.add(buttonValider);
		buttonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				onValiderClicked();
				}
				catch (DaoException e2) {
					
				}
				    	
			}
		});
		
		buttonQuitter = new JButton("Quitter");	
		buttonQuitter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelButton.add(buttonQuitter);
		buttonQuitter.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int retour=JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment quitter?","Quitter",JOptionPane.YES_NO_OPTION);			 	
				if (retour==0) {
					System.exit(0);
				}
			}
		});
	}
	
	private void onValiderClicked() throws DaoException {
		String username = textFieldLogin.getText();
		char [] tabPassword = passwordField.getPassword();
		String password=String.valueOf(tabPassword);

		List<User> log=dataSourceU.list();
		for(User l: log) {
			if(username.equals(l.getLogin()) && password.equals(l.getPassword() )) {
				if(l instanceof User && l.getType().equals("SIMPLE")) {
					this.passwordField.setForeground(Color.green);
					this.textFieldLogin.setForeground(Color.green);
					this.textFieldMessage.setForeground(Color.green);
					UIProductManager uiProduit = new UIProductManager();
				 	uiProduit.lancer();
				    this.dispose();
			    }
				else if(l instanceof Administrateur && l.getType().equals("ADMIN")){
					this.passwordField.setForeground(Color.green);
					this.textFieldLogin.setForeground(Color.green);
					this.textFieldMessage.setForeground(Color.green);
					UIUnique uiUser4 = new UIUnique();
				 	uiUser4.show4();
				    this.dispose();
			    }
		}
			else {
				this.textFieldMessage.setForeground(Color.red);
				this.textFieldLogin.setBackground(Color.red);
				this.passwordField.setForeground(Color.white);
				this.textFieldLogin.setForeground(Color.white);
				this.textFieldMessage.setText("Erreur d'authentification");
			    }
		}
	}

	public void showMe() {
		this.setVisible(true);
		
	}
}
