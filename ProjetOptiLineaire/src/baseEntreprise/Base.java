package baseEntreprise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Base {
	private  int idBase;
	private ArrayList<Entreprise> listeEntreprise;
	private int nombreEntreprise;
	private double cout;
	private BaseDonneeGeneral baseDonneeGeneral;
	public ArrayList<String>bases;
	public double qualitePrix;
	public ArrayList<Entreprise>entreprisesTrouves;
	
	public Base (String fileName,int id) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		idBase=id;
		bases = new ArrayList<String>();
		this.listeEntreprise = new ArrayList<Entreprise>();
		this.entreprisesTrouves = new ArrayList<Entreprise>();
//		this.baseDonneeGeneral = BaseDonneeGeneral.createBaseDonneeGeneral();
		this.initialisation(fileName);
	}
	
	public void initialisation (String fileName) throws FileNotFoundException {
		  File file = new File(fileName);    
	      FileReader fr = new FileReader(file);  
	      BufferedReader br = new BufferedReader(fr);  
	      String line;
	      try {
			while((line = br.readLine()) != null){
			 bases.add(line);		
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public  int getIdBase() {
		return idBase;
	}

	public  void setIdBase(int idBase) {
		this.idBase = idBase;
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

	public void montreBases() {
		for (String base : bases) {
			System.out.println(base);
		}
	}

	public ArrayList<String> getBases() {
		return bases;
	}

	public void setBases(ArrayList<String> bases) {
		this.bases = bases;
	}
	
	public boolean chercheEntreprise(Entreprise e) {
		for(String entreprise:bases) {
			if(entreprise.equals(e.getNom()))
				return true;
		}
		return false;
	}

	public double getQualitePrix() {
		return qualitePrix;
	}

	public void setQualitePrix(double qualitePrix) {
		this.qualitePrix = qualitePrix;
	}

	public ArrayList<Entreprise> getEntreprisesTrouves() {
		return entreprisesTrouves;
	}

	public void setEntreprisesTrouves(ArrayList<Entreprise> entreprisesTrouves) {
		this.entreprisesTrouves = entreprisesTrouves;
	}
	
	public void addEntrepriseTrouver(Entreprise e) {
		// TODO Auto-generated method stub
		this.entreprisesTrouves.add(e);
	}
}
