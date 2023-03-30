package com.geststockapp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.geststockapp.datasources.Dao;
import com.geststockapp.exceptions.DaoException;
import com.geststockapp.implement.ProduitDaoImplDB;
import com.geststockapp.modeles.Produit;
import com.geststockapp.modeles.ProduitModel;
import com.geststockapp.modeles.ProduitSolde;
import com.geststockapp.utils.Utilitaire;

public class UIProductManager extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField nomTF;
	private JTextField prixTF;
	private JTextField tauxTF;
	private JPanel tauxPanel;
	private JRadioButton soldeBR;
	private JRadioButton nonSoldeBR;
	private JLabel nombreProduitSolde;
	private JLabel nombreTotalProduit;
	private ProduitModel produitModel;
	private Dao<Produit> dataSource;
	private JButton ajouter;
	private JButton clear;
	private JButton supprimer;
	private JButton quitter;
	private JButton modifier;
	
	
	public UIProductManager() { 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			createInstanceComponents ();
			initComponents();
		}
		catch (DaoException e) {
		}	
	}

	private void createInstanceComponents() throws DaoException {
		nombreProduitSolde = new JLabel("0");
		nombreTotalProduit = new JLabel("0");
		table = new JTable();
		nomTF = new JTextField();
		prixTF = new JTextField();
		
		soldeBR = new JRadioButton("Sold\u00E9");
		nonSoldeBR = new JRadioButton("Non Sold\u00E9");
		nonSoldeBR.setSelected(true);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(soldeBR);
		buttonGroup.add(nonSoldeBR);
		
		tauxPanel = new JPanel();
		tauxPanel.setVisible(false);
		tauxTF = new JTextField();
		produitModel = new ProduitModel();
		dataSource= new ProduitDaoImplDB();
		produitModel.setProduits(dataSource.list());
	}

	private void initComponents () {
		setTitle("Gestion du stock de produits - version 1.0");
		setLocationRelativeTo(null);
		setSize(new Dimension(550, 300));
		setResizable(false);

		JPanel southPanel = new JPanel();
		southPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onAjouterClicked ();
			}
		});
		ajouter.setPreferredSize(new Dimension(75, 23));
		southPanel.add(ajouter);
		
		
		modifier = new JButton("Modifier");
		modifier.setMinimumSize(new Dimension(75, 21));
		modifier.setMaximumSize(new Dimension(75, 21));
		modifier.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				onModifierClicked();	
			}	
		});
		southPanel.add(modifier);
		
		
		
		supprimer = new JButton("Supprimer");
		supprimer.setMinimumSize(new Dimension(67, 21));
		supprimer.setMaximumSize(new Dimension(67, 21));
		supprimer.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				onSupprimerClicked();	
			}
		});
		supprimer.setPreferredSize(new Dimension(80, 23));
		southPanel.add(supprimer);
		
		
		clear = new JButton("Clear");
		clear.addActionListener( new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				onClearClicked();
			}			
		});
		clear.setPreferredSize(new Dimension(75, 23));
		southPanel.add(clear);
		
		
		quitter = new JButton("Quitter");
		quitter.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				onQuitterClicked ();
			}
		});
		quitter.setPreferredSize(new Dimension(75, 23));
		southPanel.add(quitter);
		
		
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel centerEastPanel = new JPanel();
		eastPanel.add(centerEastPanel, BorderLayout.CENTER);
		centerEastPanel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel northCenterEastPanel = new JPanel();
		centerEastPanel.add(northCenterEastPanel, BorderLayout.NORTH);
		northCenterEastPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		
		JPanel nombreSoldePanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) nombreSoldePanel.getLayout();
		flowLayout_1.setVgap(1);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		northCenterEastPanel.add(nombreSoldePanel);
		
		
		JLabel nombreSoldeLabel = new JLabel("Nombre de produits sold\u00E9s ajout\u00E9s : ");
		nombreSoldePanel.add(nombreSoldeLabel);
				
		nombreProduitSolde.setForeground(Color.BLUE);
		nombreProduitSolde.setText(String.valueOf(nombreProduitSolde));
		nombreSoldePanel.add(nombreProduitSolde);
				
		JPanel nombreTotalPanel = new JPanel ();
		FlowLayout fl_nombreTotalPanel = (FlowLayout) nombreTotalPanel .getLayout();
		fl_nombreTotalPanel.setVgap(1);
		fl_nombreTotalPanel.setAlignment(FlowLayout.LEFT);
		northCenterEastPanel.add(nombreTotalPanel);
		
		JLabel nombreTotalLabel = new JLabel("Nombre total de produits ajout\u00E9s : ");
		nombreTotalPanel.add(nombreTotalLabel);
		
		nombreTotalProduit.setForeground(Color.BLUE);
		nombreTotalProduit.setText(String.valueOf(nbProduit()));
		nombreTotalPanel.add(nombreTotalProduit);
		
		
		compter();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(325, 150));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		eastPanel.add(scrollPane, BorderLayout.NORTH);
		
		table.setModel(produitModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(73);
		table.getColumnModel().getColumn(1).setPreferredWidth(252);
		table.getColumnModel().getColumn(2).setPreferredWidth(148);
		table.getColumnModel().getColumn(3).setPreferredWidth(316);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {				
				onValueChanged ();
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel northCenterPanel = new JPanel();
		northCenterPanel.setBorder(new TitledBorder(null, "D\u00E9tails sur le produit ...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(northCenterPanel, BorderLayout.NORTH);
		northCenterPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel nomPanel = new JPanel();
		FlowLayout fl_nomPanel = (FlowLayout) nomPanel.getLayout();
		fl_nomPanel.setAlignment(FlowLayout.RIGHT);
		fl_nomPanel.setVgap(2);
		northCenterPanel.add(nomPanel);
		
		JLabel nomLabel = new JLabel("Nom : ");
		nomPanel.add(nomLabel);
				
		nomPanel.add(nomTF);
		nomTF.setColumns(10);
		
		JPanel prixPanel = new JPanel();
		FlowLayout fl_prixPanel = (FlowLayout) prixPanel.getLayout();
		fl_prixPanel.setAlignment(FlowLayout.RIGHT);
		fl_prixPanel.setVgap(2);
		northCenterPanel.add(prixPanel);
		
		JLabel prixLabel = new JLabel("Prix : ");
		prixPanel.add(prixLabel);
				
		prixPanel.add(prixTF);
		prixTF.setColumns(10);
		
		JPanel soldePanel = new JPanel();
		FlowLayout fl_soldePanel = (FlowLayout) soldePanel.getLayout();
		fl_soldePanel.setVgap(2);
		northCenterPanel.add(soldePanel);
				
		soldePanel.add(soldeBR);
		soldeBR.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent arg0) {				
				onChangeClicked ();
			}
		});
		soldePanel.add(nonSoldeBR);
		
		FlowLayout fl_tauxPanel = (FlowLayout) tauxPanel.getLayout();
		fl_tauxPanel.setAlignment(FlowLayout.RIGHT);
		fl_tauxPanel.setVgap(2);
		northCenterPanel.add(tauxPanel);
		
		JLabel tauxLabel = new JLabel("Taux : ");
		tauxPanel.add(tauxLabel);
		
		tauxPanel.add(tauxTF);
		tauxTF.setColumns(10);
		
		Utilitaire.setLookAndFeel(this);
		Utilitaire.center(this, getSize());
	}
	
	private void onValueChanged() {
			int row = table.getSelectedRow();
			if (row == -1) return;
			
			int id = (int) produitModel.getValueAt(row, 0);
			try {
				Produit produit = dataSource.read(id);
				
				this.nomTF.setText(produit.getNom());
				this.prixTF.setText(String.valueOf(produit.getPrix()));
				if (produit instanceof ProduitSolde) {
					this.soldeBR.setSelected(true);
					this.tauxTF.setText(String.valueOf(((ProduitSolde)produit).getSolde()));
				} else {
					this.nonSoldeBR.setSelected(true);				
				}
			}
			catch (DaoException e) {}		
	}	

	private int nbProduit() {
		try {
			return dataSource.list().size();
		} catch (DaoException e) {
			return 0;
		} 
	}
	
	private int nbProduitSolde() {
		int nombre=0;
		try {
			List<Produit> produits = dataSource.list();
			for(Produit produit: produits) {
				if(produit instanceof ProduitSolde)
					nombre++;
			}
		} catch (DaoException e) {
		} 
		return nombre;
	}
	
	private void onAjouterClicked () {
		// Faire les contr�les et g�rer convenablement les erreurs ...

		String nom = nomTF.getText();
		String prix = prixTF.getText();
		Double prixDouble = Double.valueOf(prix);
		try {
			Produit produit;
			
			if (soldeBR.isSelected()) {
				String taux = tauxTF.getText();
				Integer tauxInt = Integer.valueOf(taux);
				
				produit = new ProduitSolde(nom, prixDouble, tauxInt);
				dataSource.create(produit);
				
				
			} else {
				produit = new Produit(nom, prixDouble);
				dataSource.create(produit);
			}
	 
			produitModel.setProduits(dataSource.list()); 
			compter();
			
			reinitProductDetailsForm();
		}
		catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Errr: "+e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	private void compter(int nbrProdSolde, int nbProd) {
		nombreProduitSolde.setText(String.valueOf(nbrProdSolde));
		nombreTotalProduit.setText(String.valueOf(nbProd));	
	}
	
	private void compter() {
		compter(nbProduitSolde(), nbProduit());
	}

	private void onChangeClicked () {
		if (soldeBR.isSelected()) {
			tauxPanel.setVisible(true);
		} else {
			tauxPanel.setVisible(false);
		}
	}
	
	private void reinitProductDetailsForm () {
		this.nomTF.setText(null);
		this.prixTF.setText(null);
		this.tauxTF.setText(null);		
	}

	private void onClearClicked() {
		reinitProductDetailsForm();
		// -
		this.produitModel.clear();
		compter(0,0);
		//this.nombreProduitSolde.setText(null);
		//this.nombreTotalProduit.setText(null);
	}

	private void onQuitterClicked () {
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Fermer l'application ?")) {
			MaFenetre maFenetre = new MaFenetre();
			maFenetre.showMe();
			this.dispose(); 
		}
	}
	
	private void onSupprimerClicked() {
		int row = table.getSelectedRow();
		if(row==-1) {
			JOptionPane.showMessageDialog(null, "Veuillez selectionner un produit !");
		}
		else {
			int id=(int)produitModel.getValueAt(row, 0);
			try {
				dataSource.delete(id);
				produitModel.setProduits(dataSource.list());
				
				compter();
			} catch (DaoException e) {
				
			}
		}
	}
	
	private void onModifierClicked() {
		int i=table.getSelectedRow();
		if(i==-1)return;
		
		int id = (int) table.getValueAt(i, 0);
		
		String nom = nomTF.getText();
		String prix = prixTF.getText();
		Double prixDouble = Double.valueOf(prix);
		try {
			Produit produit; 
			
			if (soldeBR.isSelected()) { 
				String taux = tauxTF.getText();
				Integer tauxInt = Integer.valueOf(taux);
				produit = new ProduitSolde(id ,nom, prixDouble, tauxInt);		
			} else {
				produit = new Produit(id, nom, prixDouble);

			}
			dataSource.update(produit);
			produitModel.setProduits(dataSource.list());
		}catch (DaoException e) {
				JOptionPane.showMessageDialog(null,"Errr: "+e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
			}	
	 
	}
	

	public void lancer () {
		this.setVisible(true);
	}
}



