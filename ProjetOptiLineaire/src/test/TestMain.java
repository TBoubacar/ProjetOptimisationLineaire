package test;

import java.util.Scanner;

import algorithmes.BranchAndBound;
import algorithmes.Glouton;
public class TestMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner clavier;
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
					Glouton glouton = new Glouton(nomFichierBase, nomFichierEntreprise);
					
					System.out.println("\t_________________________Branch and Bound_________________________");
					@SuppressWarnings("unused")
					BranchAndBound b = new BranchAndBound(nomFichierBase, nomFichierEntreprise);
					System.out.println("_________________________END OF TEST_________________________\n"
									+ "________________________________________________________________\n\n");
					commande = "exit";
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} while (! commande.equals("exit"));
	}

}