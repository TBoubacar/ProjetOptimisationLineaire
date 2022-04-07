package algorithmes;

import java.io.IOException;

import baseEntreprise.Base;
import baseEntreprise.BaseDonneeGeneral;
import baseEntreprise.Entreprise;
import baseEntreprise.ListeEntreprise;
import baseEntreprise.Scenario;

public class BranchAndBound {
	private Scenario scenario;
	private BaseDonneeGeneral baseDonneeGeneral=BaseDonneeGeneral.createBaseDonneeGeneral();
	private ListeEntreprise listeEntreprise;
	public BranchAndBound(String fichiersBases,String fichiersEntreprises) throws Exception {
		scenario = new Scenario();
	
		listeEntreprise = new ListeEntreprise();
		initialisationEntreprises(fichiersEntreprises);		// Les entreprises doivent etre créer avant les bases (IMPORTANT)
//		initialisationBases(fichiersBases);
		initialisationBasesBB(fichiersBases);
		System.out.println("Le cout minimum pour avoir les informations sur l'ensemble de nos " + listeEntreprise.getListeEntreprises().size() + " : " + baseDonneeGeneral.determineCoutAndEntreprise(listeEntreprise));
	}
	
	public void initialisationBases(String filename) throws IOException {
		scenario.lecturesbases(filename);
		for(int i=1;i<scenario.getListesBases().size();i++) {
			Base b = new Base("./data/"+scenario.getListesBases().get(i),i);
			b.setCout(Double.valueOf(b.getBases().get(0)));
//			b.montreBases();
			b.setQualitePrix(b.getCout()/b.getBases().size());
			baseDonneeGeneral.addBase(b);
		}
	}
	
	public void initialisationBasesBB(String filename) throws IOException {
		scenario.lecturesbases(filename);

		for(int i=1;i<scenario.getListesBases().size();i++) {
			Base b = new Base("./data/"+scenario.getListesBases().get(i),i);
			b.setCout(Double.valueOf(b.getBases().get(0)));
			baseDonneeGeneral.addBase(b);
		}
		baseDonneeGeneral.optimiserRechercheApproffondies(listeEntreprise);	// On determine l'ensemble des bases avec leur liste d'entreprise
		for (Base b : baseDonneeGeneral.getListeBasePertinent()) {
			b.setQualitePrix(b.getCout()/b.getEntreprisesTrouves().size());
		}
		baseDonneeGeneral.trierParQualitePrix(baseDonneeGeneral.getListeBasePertinent());
		baseDonneeGeneral.findTheBestSolutionOfRechercheBase(listeEntreprise);
	}
	
	public void initialisationEntreprises(String filename) throws Exception {
		scenario.lectureEntreprises(filename);
		for(int i=1;i<scenario.getListeEntreprises().size();i++) {
			Entreprise e=new Entreprise(scenario.getListeEntreprises().get(i));
			listeEntreprise.addEntreprise(e);
			
		}
	}
	public void recherchecoupOptimalBB() {
		
		System.out.println("En triant par ordre croissant de cout/ :");
//		int cout=baseDonneeGeneral.optimiserRechercheApproffondies(listeEntreprise);
//		System.out.println("cout optimal trouve = "+cout);
	}
}
