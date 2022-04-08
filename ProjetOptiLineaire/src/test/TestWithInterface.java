package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import algorithmes.BranchAndBound;
import algorithmes.Glouton;
import utils.Observer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class TestWithInterface extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private static JTextArea jTextArea;
	/*____________________________________________________CONSTRUCTEUR__________________________________________________________*/
	public TestWithInterface () {
		super ( "GLOUTON ET BRANCH & BOUND" );
		this.pack();
		
		this.setLayout( new GridLayout(1,2));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize( 1200, 700);
		this.setLocationRelativeTo( null );
		
		jTextArea = new JTextArea(50, 50);
		jTextArea.setText("\t MISE EN PLACE DES ALGORITHMES GLOUTON ET BRANCH & BOUND:\n"
				+ "****************************************************************************************************************************************\n"
				+ "\t		(^_^)\n"
				+ "****************************************************************************************************************************************\n");
		
		jTextArea.setPreferredSize( new Dimension(50,50) );
		jTextArea.setAlignmentX(CENTER_ALIGNMENT);
		jTextArea.setAlignmentY(CENTER_ALIGNMENT);
		jTextArea.setForeground(Color.WHITE);
		jTextArea.setBackground(Color.BLACK);
		jTextArea.setFont(new Font("Serif", Font.BOLD, 20));
	}

	@Override
	public void update(String msg) {
		System.out.println("Update : " + msg);
		jTextArea.setText(jTextArea.getText() + msg);
	}
	
	public static JTextArea getjTextArea() {
		return jTextArea;
	}

	public static void setjTextArea(JTextArea jTextArea) {
		TestWithInterface.jTextArea = jTextArea;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*____________________________________________________PROGRAMME PRINCIPAL__________________________________________________________*/
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		TestWithInterface test = new TestWithInterface();
		/*------------------------TEST POUR LE CHAINAGE AVANT------------------------*/
		System.out.println("------------------------TEST POUR LE CHAINAGE AVANT------------------------\n"
				+ "\t**************VOIR SUR L'INTERFACE GRAPHIQUE**************\n"
				+ "\t			(^_^)");
		JPanel jPanelQ1 = new JPanel(new GridLayout(1,1));
		JButton boutonDemarre = new JButton("Demarrer l'algorithme");
		
		boutonDemarre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				jTextArea.setText("\t MISE EN PLACE DES ALGORITHMES GLOUTON ET BRANCH & BOUND:\n"
						+ "****************************************************************************************************************************************\n"
						+ "\t		DEBUT DE LA RECHERCHE\n"
						+ "****************************************************************************************************************************************\n");
				Scanner clavier;
				TestWithInterface test = new TestWithInterface();
				String commande = "";
				String nomFichierBase = "";
				String nomFichierEntreprise = "";
				do {
					try {
						clavier = new Scanner(System.in);
						System.out.print("Exemple des commandes possibles : \n"
								+ "Tapez 1		===>	Pour lancer l'exécution du premier jeu de données\n"
								+ "Tapez 2		===>	Pour lancer l'exécution du deuxième jeu de données\n"
								+ "Tapez 3		===>	Pour lancer l'exécution du troisième jeu de données\n"
								+ "Tapez exit	===>	Pour quitter la série de tests\n"
								+ "Saisissez votre commande : ");
						commande = clavier.next();
						
						switch (commande) {
						case "1":
							nomFichierBase = "./data/Scenarios\\ListeBases\\ListeBases1.txt";
							nomFichierEntreprise = "./data/Scenarios\\ListeEntreprises\\ListeEnt1.txt";
							break;
						case "2":
							nomFichierBase = "./data/Scenarios\\ListeBases\\ListeBases2.txt";
							nomFichierEntreprise = "./data/Scenarios\\ListeEntreprises\\ListeEnt2.txt";
							break;
						case "3":
							nomFichierBase = "./data/Scenarios\\ListeBases\\ListeBases3.txt";
							nomFichierEntreprise = "./data/Scenarios\\ListeEntreprises\\ListeEnt3.txt";
							break;
						default:
							commande = "exit";
							System.out.println("Merci de votre visite (^_^) !\n\n");
							break;
						}
						
						if (! commande.equals("exit")) {
							System.out.println("\t_________________________Glouton_________________________");
							@SuppressWarnings("unused")
							Glouton glouton = new Glouton(nomFichierBase, nomFichierEntreprise, test);
							
							System.out.println("\t_________________________Branch and Bound_________________________");
							@SuppressWarnings("unused")
							BranchAndBound b = new BranchAndBound(nomFichierBase, nomFichierEntreprise, test);
							System.out.println("_________________________END OF TEST_________________________\n"
											+ "________________________________________________________________\n\n");
							commande = "exit";
						}
						
					}catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
				} while (! commande.equals("exit"));

			}
		});
		
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		JPanel panelText = new JPanel(new GridLayout(2,1));
		
		jPanelQ1.add(boutonDemarre);
		panelText.add(jScrollPane);
		panelText.add(jPanelQ1);

		test.add(panelText);
		UIManager.setLookAndFeel( new NimbusLookAndFeel() );
		test.setVisible( true );
	}	

}