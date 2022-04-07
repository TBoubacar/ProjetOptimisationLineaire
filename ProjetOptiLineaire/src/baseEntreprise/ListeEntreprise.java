package baseEntreprise;

import java.util.ArrayList;

public class ListeEntreprise {

	private ArrayList<Entreprise>listeEntreprises;
	public ListeEntreprise() {
		listeEntreprises = new ArrayList<Entreprise>();
	}
	
	public void addEntreprise(Entreprise e) {
		this.listeEntreprises.add(e);
	}
	
	public void showEntreprises() {
		for(Entreprise e :listeEntreprises) {
			System.out.println(e.getNom());
		}
	}

	public ArrayList<Entreprise> getListeEntreprises() {
		return listeEntreprises;
	}

	public void setListeEntreprises(ArrayList<Entreprise> listeEntreprises) {
		this.listeEntreprises = listeEntreprises;
	}
	
	
}
