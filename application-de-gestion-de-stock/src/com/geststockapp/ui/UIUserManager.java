package com.geststockapp.ui;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.geststockapp.datasources.Dao;
import com.geststockapp.exceptions.BadFormatException;
import com.geststockapp.exceptions.DaoException;
import com.geststockapp.exceptions.MissingDataException;
import com.geststockapp.implement.UserDaoImplDB;
import com.geststockapp.modeles.Administrateur;
import com.geststockapp.modeles.User;
import com.geststockapp.modeles.UserModel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import java.awt.Insets;

public class UIUserManager extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel panel_de_creation;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JButton aProposButton;
	private JButton exitFileButton;
	private JMenu questionMenu;
	private JTextField textFieldSearch;
	private JRadioButton createRadioButton;
	private JRadioButton updateRadioButton;
	private JLabel searchLabel;
	private JButton go;
	private JPanel panel_des_boutons;
	private JButton exitButton;
	private JButton clearButton;
	private JButton deleteButton;
	private JButton listButton;
	private JButton saveButton;
	private JPanel panel_details_et_users;
	private JPanel panel_details;
	private JLabel labelDetail;
	private JPanel panel_user_only;
	private JPanel panel_id;
	private JPanel panel_login;
	private JPanel panel_password;
	private JPanel panel_confirm_password;
	private JPanel panel;
	private JPanel eastPanel;
	private JTextField textField_id;
	private JLabel labelId;
	private JTextField textField_login;
	private JLabel labelLogin;
	private JLabel labelPassword;
	private JLabel labelConfirmPassword;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private UserModel userModel;
	private Dao<User> dataSourceUser;
	private JRadioButton adminRadioButton;
	

	
	
	public UIUserManager() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try {
			createInstanceComponent();
			initComponent();
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	private void createInstanceComponent() throws DaoException {	
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		exitFileButton = new JButton("Exit");
		questionMenu = new JMenu("?");
		aProposButton = new JButton("A propos");
		panel_de_creation = new JPanel();
		eastPanel = new JPanel();
		panel_des_boutons = new JPanel();
		createRadioButton = new JRadioButton("Create");
		updateRadioButton = new JRadioButton("Update");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(createRadioButton);
		buttonGroup.add(updateRadioButton);
		searchLabel = new JLabel("Rechercher Selon le login :");
		textFieldSearch = new JTextField();
		go = new JButton("Go");
		table = new JTable();
		userModel = new UserModel();
		dataSourceUser= new UserDaoImplDB();
		userModel.setUser(dataSourceUser.list());
	}


	@SuppressWarnings("deprecation")
	private void initComponent() {
		setTitle("User Manager System");
		getContentPane().setLayout(new BorderLayout(0, 0));		
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(new Dimension(550, 350));
		
		userModel.clear();
		
		panel_de_creation.setPreferredSize(new Dimension(20, 50));
		panel_de_creation.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		getContentPane().add(panel_de_creation, BorderLayout.NORTH);
		panel_de_creation.setLayout(new GridLayout(2, 1, 0, 0));
		
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		menuBar.add(questionMenu);
		fileMenu.add(exitFileButton);
		
		questionMenu.add(aProposButton);
		
		panel_de_creation.setPreferredSize(new Dimension(30, 30));
		getContentPane().add(panel_de_creation, BorderLayout.NORTH);
		panel_de_creation.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		 
		
		createRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		createRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onCreateUpdateClicked();
				
			}
		});
		panel_de_creation.add(createRadioButton);
		
		
		updateRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onCreateUpdateClicked();
				
			}
		});
		panel_de_creation.add(updateRadioButton);
		
		
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchLabel.setBounds(new Rectangle(50, 0, 0, 0));
		searchLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		searchLabel.setAlignmentX(20.0f);
		searchLabel.setMaximumSize(new Dimension(121, 50));
		searchLabel.setPreferredSize(new Dimension(200, 20));
		searchLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		searchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		searchLabel.setIconTextGap(6);
		searchLabel.setSize(new Dimension(30, 30));
		panel_de_creation.add(searchLabel);
		
		
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSearch.setPreferredSize(new Dimension(8, 20));
		panel_de_creation.add(textFieldSearch);
		textFieldSearch.setColumns(11);
		
		
		go.setHorizontalTextPosition(SwingConstants.RIGHT);
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OnGoClicked();
				
			}
		});
		go.setHorizontalAlignment(SwingConstants.RIGHT);
		go.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		go.setAlignmentX(Component.RIGHT_ALIGNMENT);
		go.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_de_creation.add(go);
		
		//panel_des_boutons.setVisible(false);
		panel_des_boutons.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_des_boutons.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_des_boutons.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		FlowLayout fl_panel_des_boutons = (FlowLayout) panel_des_boutons.getLayout();
		fl_panel_des_boutons.setAlignment(FlowLayout.RIGHT);
		panel_des_boutons.setPreferredSize(new Dimension(20, 37));
		getContentPane().add(panel_des_boutons, BorderLayout.SOUTH);
		
		saveButton = new JButton();
		saveButton.setVisible(false);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onSaveClicked();
			}
		});
		
		
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_des_boutons.add(saveButton);
		
		listButton = new JButton("List");
		listButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onListClicked();				
			}
		});
		listButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_des_boutons.add(listButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onDeleteClicked();
				
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_des_boutons.add(deleteButton);
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onClearClicked();
				
			}
		});
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_des_boutons.add(clearButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onExitClicked();
				
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_des_boutons.add(exitButton);
		
		panel_details_et_users = new JPanel();
		panel_details_et_users.setPreferredSize(new Dimension(250, 0));
		getContentPane().add(panel_details_et_users, BorderLayout.WEST);
		panel_details_et_users.setLayout(new BorderLayout(0, 0));
		
		panel_details = new JPanel();
		panel_details.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout fl_panel_details = (FlowLayout) panel_details.getLayout();
		fl_panel_details.setVgap(2);
		fl_panel_details.setAlignment(FlowLayout.LEFT);
		panel_details.setPreferredSize(new Dimension(10, 20));
		panel_details_et_users.add(panel_details, BorderLayout.NORTH);
		
		labelDetail = new JLabel("User details");
		labelDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_details.add(labelDetail);
		
		panel_user_only = new JPanel();
		panel_user_only.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_user_only.setPreferredSize(new Dimension(250, 10));
		panel_details_et_users.add(panel_user_only, BorderLayout.WEST);
		panel_user_only.setLayout(new GridLayout(5, 2, 2, 2));
		
		panel_id = new JPanel();
		panel_user_only.add(panel_id);
		panel_id.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		labelId = new JLabel("Id :");
		labelId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_id.add(labelId);
		
		textField_id = new JTextField();
		textField_id.disable();
		textField_id.setPreferredSize(new Dimension(7, 23));
		panel_id.add(textField_id);
		textField_id.setColumns(11);
		
		panel_login = new JPanel();
		panel_user_only.add(panel_login);
		panel_login.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		labelLogin = new JLabel("Login :");
		labelLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_login.add(labelLogin);
		
		textField_login = new JTextField();
		textField_login.setPreferredSize(new Dimension(7, 23));
		textField_login.setText("");
		panel_login.add(textField_login);
		textField_login.setColumns(11);
		
		panel_password = new JPanel();
		panel_user_only.add(panel_password);
		panel_password.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_password.add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(105, 23));
		panel_password.add(passwordField);
		
		panel_confirm_password = new JPanel();
		panel_confirm_password.setVisible(false);
		panel_user_only.add(panel_confirm_password);
		panel_confirm_password.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		labelConfirmPassword = new JLabel("Confirm Password :");
		labelConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_confirm_password.add(labelConfirmPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setPreferredSize(new Dimension(105, 23));
		panel_confirm_password.add(passwordField_1);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_user_only.add(panel);
		
		adminRadioButton = new JRadioButton("Administrateur");
		adminRadioButton.setMargin(new Insets(0, 0, 0, 0));
		adminRadioButton.setVerticalAlignment(SwingConstants.TOP);
		adminRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		adminRadioButton.setPreferredSize(new Dimension(131, 34));
		panel.add(adminRadioButton);
		
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension(283, 220));
		eastPanel.add(scrollPane, BorderLayout.NORTH);
		
		table.setModel(userModel);
		table.setPreferredSize(new Dimension(80, 196));
		table.setShowGrid(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(252);
		table.getColumnModel().getColumn(2).setPreferredWidth(148);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {				
				onValueChanged ();
			}
		});
		scrollPane.setViewportView(table);	
	} 

 
	private void OnGoClicked() {
		String value = textFieldSearch.getText();
		List<User> using;
		try {
			using = dataSourceUser.list();
			for(User l : using) {
				if(l.getLogin().equals(value)) {
					JOptionPane.showMessageDialog(null, "Recherche des informations :\n Login :"+l.getLogin()+"\n Password :"+l.getPassword()+"","Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		catch (DaoException e) {
			
		}
	}
		

	private void onListClicked() {
		try {
			userModel.setUser(dataSourceUser.list());
		} 
		catch (DaoException e) {
		}	
	}

	private void onDeleteClicked() {
		int row = table.getSelectedRow();
		if(row==-1) {
			JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit !");
		}
		else {
			int id=(int)userModel.getValueAt(row, 0);
			try {
				dataSourceUser.delete(id);
				userModel.setUser(dataSourceUser.list());

			} catch (DaoException e) {
				
			}
		}	
	}

	private void onClearClicked() {
		reinitProductDetailsForm();
		this.userModel.clear();	
	}

	private void onExitClicked() {
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Fermer l'application ?")) {
			MaFenetre maFenetre = new MaFenetre();
			maFenetre.showMe();
			this.dispose(); 
		}	
	}

	private void onCreateUpdateClicked() {
		if (createRadioButton.isSelected()) {
			saveButton.setText("Save");
			panel_confirm_password.setVisible(true);
			saveButton.setVisible(true);
		 }
		else if(updateRadioButton.isSelected()){
			saveButton.setText("Update");
			panel_confirm_password.setVisible(false);
			adminRadioButton.setVisible(true);
			saveButton.setVisible(true);	
		}	
	}

	private void onSaveClicked(){
		String username = textField_login.getText();
		char [] tabPassword = passwordField.getPassword();
		String password1=String.valueOf(tabPassword);
		char [] tabPassword1 = passwordField_1.getPassword();
		String password2=String.valueOf(tabPassword1);
		
			try {
				User user=new User(username, password1);
				if(createRadioButton.isSelected()) {
					if(user.authentification(username, password2) && password1.equals(password2)) {
						if (adminRadioButton.isSelected()){
							user = new Administrateur(username, password1);
							dataSourceUser.create(user);
					 	 }
					 	 else {
					 		user=new User(username, password1);
				 	 		dataSourceUser.create(user);
					 	 }				
						reinitProductDetailsForm();
						userModel.clear();
						}
					else {
						JOptionPane.showMessageDialog(null, "Donnee invalide");
					}
				}
				else if(updateRadioButton.isSelected()) {
					int i=table.getSelectedRow();
					if(i==-1)return;

					int id = (int) table.getValueAt(i, 0);
						List<User> log=dataSourceUser.list();
						for(User l: log) {
								if(l instanceof User && l.getType().equals("SIMPLE")) {
									if(adminRadioButton.isSelected()) {
										user= new User(id, username, password1);
										dataSourceUser.update(user);
									}
									else {
										user= new User(id, username, password1);
										dataSourceUser.update(user);
									}
							    
							}
								else {
									if(adminRadioButton.isSelected()) {
										user= new Administrateur(id, username, password1);
										dataSourceUser.update(user);
									}
									else {
										user= new Administrateur(id, username, password1);
										dataSourceUser.update(user);
									}
							    }
						}
				 	 	dataSourceUser.update(user);
					 	userModel.setUser(dataSourceUser.list());
									
						reinitProductDetailsForm();	
				}
			}
			catch (DaoException | BadFormatException| MissingDataException e) {
					JOptionPane.showMessageDialog(null,"Errr: "+e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
				} 
			} 
				
	
	private void onValueChanged() {
		
		int row = table.getSelectedRow();
		if (row == -1) return;
		
		int id = (int) userModel.getValueAt(row, 0);
		try {
			User user = dataSourceUser.read(id);
			this.textField_login.setText(user.getLogin());
			this.passwordField.setText(user.getPassword());
			List<User> log=dataSourceUser.list();
			for(User l: log) {
					if(l instanceof User && l.getType().equals("SIMPLE")) {
						this.adminRadioButton.setVisible(true);
				    }
					else if (l instanceof Administrateur && l.getType().equals("ADMIN")){
						this.adminRadioButton.setSelected(true);
				    }
			}
		}
		catch (DaoException e) {}		
}	
	
	private void reinitProductDetailsForm () {
		this.textField_id.setText(null);
		this.textField_login.setText(null);
		this.passwordField.setText(null);
		this.passwordField_1.setText(null); 
	}

	public void run() {
		this.setVisible(true);
	}
}
