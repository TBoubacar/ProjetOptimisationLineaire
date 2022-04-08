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

public class Glouton implements Observable {
	private ArrayList<Observer> observers;
	private ListeEntreprise listeEntreprise;
	private BaseDonneeGeneral baseDonneeGeneral;
	private Scenario scenario;
	
	public Glouton(String fichiersBases,String fichiersEntreprises, Observer observer) throws Exception {
		this.observers = new ArrayList<Observer>();
		baseDonneeGeneral = BaseDonneeGeneral.createBaseDonneeGeneral(observer);
		this.addObserver(observer);
		scenario = new Scenario();
		listeEntreprise = new ListeEntreprise();
		initialisationBases(fichiersBases);
		initialisationEntreprises(fichiersEntreprises);
		this.recherchecoupOptimal();
		baseDonneeGeneral.reinitialiserBaseDonnee();
	}

	/*_________________________Glouton - Initialisation_________________________*/
	
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
	public void recherchecoupOptimal() {
		System.out.println("Application de l'algorithme de Glouton :");
		baseDonneeGeneral.trierParQualitePrix(baseDonneeGeneral.getListeBase());
		int cout = baseDonneeGeneral.optimiserRecherche(listeEntreprise,baseDonneeGeneral.getListeBase());
		String msg = "";
		msg = "Le cout minimum pour avoir les informations sur l'ensemble de nos entreprise(s) : " + cout + "\n\n";
		notifyObserver(msg + "\n");
		System.out.println(msg);
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
