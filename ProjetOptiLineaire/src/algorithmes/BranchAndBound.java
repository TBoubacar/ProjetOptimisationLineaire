package algorithmes;

import java.io.IOException;

import baseEntreprise.Base;
import baseEntreprise.BaseDonneeGeneral;
import baseEntreprise.Entreprise;
import baseEntreprise.ListeEntreprise;
import baseEntreprise.Scenario;

public class BranchAndBound {
	private BaseDonneeGeneral baseDonneeGeneral = BaseDonneeGeneral.createBaseDonneeGeneral();
	private ListeEntreprise listeEntreprise;
	private Scenario scenario;
	
	public BranchAndBound(String fichiersBases,String fichiersEntreprises) throws Exception {
		scenario = new Scenario();
		listeEntreprise = new ListeEntreprise();
		initialisationEntreprises(fichiersEntreprises);		// Les entreprises doivent etre créer avant les bases (IMPORTANT)
		initialisationBasesBB(fichiersBases);
		System.out.println("Le cout minimum pour avoir les informations sur l'ensemble de nos " + listeEntreprise.getListeEntreprises().size() + " entreprise(s) : " + baseDonneeGeneral.determineCoutAndEntreprise(listeEntreprise) + "\n\n");
		baseDonneeGeneral.reinitialiserBaseDonnee();
	}
	
	/*_________________________Branch and Bound - Initialisation_________________________*/
	
	public void initialisationBases(String filename) throws IOException {
		scenario.lecturesbases(filename);
		for(int i=1;i<scenario.getListesBases().size();i++) {
			Base b = new Base("./data/"+scenario.getListesBases().get(i),i);
			b.setCout(Double.valueOf(b.getBases().get(0)));
			b.setQualitePrix(b.getCout()/b.getBases().size());
			baseDonneeGeneral.addBase(b);
		}
	}
	
	public void initialisationEntreprises(String filename) throws Exception {
		scenario.lectureEntreprises(filename);
		for(int i=1;i<scenario.getListeEntreprises().size();i++) {
			Entreprise e=new Entreprise(scenario.getListeEntreprises().get(i));
			listeEntreprise.addEntreprise(e);
		}
	}
	
	/*_________________________Branch and Bound_________________________*/

	public void initialisationBasesBB(String filename) throws IOException {
		System.out.println("Application de l'algorithme de Branch And Bound :\n");
		scenario.lecturesbases(filename);
		
		for(int i=1;i<scenario.getListesBases().size();i++) {
			Base b = new Base("./data/"+scenario.getListesBases().get(i),i);
			b.setCout(Double.valueOf(b.getBases().get(0)));
			baseDonneeGeneral.addBase(b);
		}
		baseDonneeGeneral.optimiserRechercheApproffondies(listeEntreprise);	// On determine l'ensemble des bases avec leur liste d'entreprise
		double moyenne = 0;
		double coutApproximatif = 0;
		for (Base b : baseDonneeGeneral.getListeBasePertinent()) {
			moyenne = b.getCout()/b.getEntreprisesTrouves().size();
			coutApproximatif = moyenne - (b.getCout()/moyenne);
			b.setQualitePrix(coutApproximatif - b.getEntreprisesTrouves().size());
		}
		baseDonneeGeneral.trierParQualitePrix(baseDonneeGeneral.getListeBasePertinent());
		baseDonneeGeneral.findTheBestSolutionOfRechercheBase(listeEntreprise);
	}
	
}
