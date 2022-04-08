package algorithmes;

import java.io.IOException;
import java.util.ArrayList;

import baseEntreprise.Base;
import baseEntreprise.BaseDonneeGeneral;
import baseEntreprise.Entreprise;
import baseEntreprise.ListeEntreprise;
import baseEntreprise.Scenario;
import utils.Observable;
import utils.Observer;

public class BranchAndBound implements Observable {
	private BaseDonneeGeneral baseDonneeGeneral;
	private ListeEntreprise listeEntreprise;
	private ArrayList<Observer> observers;
	private Scenario scenario;
	
	public BranchAndBound(String fichiersBases,String fichiersEntreprises, Observer observer) throws Exception {
		scenario = new Scenario();
		this.observers = new ArrayList<Observer>();
		this.addObserver(observer);
		baseDonneeGeneral = BaseDonneeGeneral.createBaseDonneeGeneral(observer);
		listeEntreprise = new ListeEntreprise();
		initialisationEntreprises(fichiersEntreprises);		// Les entreprises doivent etre créer avant les bases (IMPORTANT)
		initialisationBasesBB(fichiersBases);
		String msg = "";
		msg = "Le cout minimum pour avoir les informations sur l'ensemble de nos " + listeEntreprise.getListeEntreprises().size() + " entreprise(s) : " + baseDonneeGeneral.determineCoutAndEntreprise(listeEntreprise) + "\n\n";
		notifyObserver(msg + "\n");
		System.out.println(msg);
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

	public ArrayList<Observer> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}

	@Override
	public void notifyObserver(String msg) {
		for(Observer o : this.observers)
			o.update(msg);		
	}

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);		
	}
}
