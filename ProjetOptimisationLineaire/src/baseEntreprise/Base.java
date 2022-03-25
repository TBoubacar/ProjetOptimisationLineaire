package baseEntreprise;

import java.util.ArrayList;

public class Base {
	private static int idBase = 0;
	private ArrayList<Entreprise> listeEntreprise;
	private int nombreEntreprise;
	private double cout;
	private BaseDonneeGeneral baseDonneeGeneral;
	
	public Base (String fileName) {
		// TODO Auto-generated constructor stub
		++Base.idBase;
		this.listeEntreprise = new ArrayList<Entreprise>();
		this.baseDonneeGeneral = BaseDonneeGeneral.createBaseDonneeGeneral();
		this.initialisation(fileName);
	}
	
	public void initialisation (String fileName) {
	//		A COMPLETER : RECUPERATION DES DONNÉES DANS LE FICHIER fileName ET INITIALISER LES DONNÉES PUIS INSERER LA BASE CREER DANS BaseDonneeGeneral 
	
	//		this.nombreEntreprise = 0;
	//		this.cout = 0;
	
	//		this.baseDonneeGeneral.addBase(this);
	}
	
	public boolean researchEntreprise(Entreprise entreprise) {
		// TODO Auto-generated method stub
		for (Entreprise monEntreprise : listeEntreprise) {
			if (monEntreprise == entreprise) return true;
		}
		return false;
	}
	
	public void addEntreprise(Entreprise entreprise) {
		this.listeEntreprise.add(entreprise);
	}
	
		/*---		GETTERS AND SETTERS		---*/
	public static int getIdBase() {
		return idBase;
	}

	public static void setIdBase(int idBase) {
		Base.idBase = idBase;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public int getNombreEntreprise() {
		return nombreEntreprise;
	}

	public void setNombreEntreprise(int nombreEntreprise) {
		this.nombreEntreprise = nombreEntreprise;
	}

	public ArrayList<Entreprise> getListeEntreprise() {
		return listeEntreprise;
	}

	public void setListeEntreprise(ArrayList<Entreprise> listeEntreprise) {
		this.listeEntreprise = listeEntreprise;
	}

	public BaseDonneeGeneral getBaseDonneeGeneral() {
		return baseDonneeGeneral;
	}

	public void setBaseDonneeGeneral(BaseDonneeGeneral baseDonneeGeneral) {
		this.baseDonneeGeneral = baseDonneeGeneral;
	}

	
}
