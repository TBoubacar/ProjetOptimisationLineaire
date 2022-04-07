package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import algorithmes.BranchAndBound;
import algorithmes.Glouton;
import baseEntreprise.Base;
import baseEntreprise.BaseDonneeGeneral;
import baseEntreprise.Entreprise;
import baseEntreprise.ListeEntreprise;
import baseEntreprise.Scenario;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
//		Glouton glouton = new Glouton("./data/Scenarios\\ListeBases\\ListeBases1.txt", "./data/Scenarios\\ListeEntreprises\\ListeEnt1.txt");
//		glouton.recherchecoupOptimal();
		
		System.out.println("######### Branch and Bound#######################");
		BranchAndBound b = new BranchAndBound("./data/Scenarios\\ListeBases\\ListeBases1.txt", "./data/Scenarios\\ListeEntreprises\\ListeEnt1.txt");		
	}

}