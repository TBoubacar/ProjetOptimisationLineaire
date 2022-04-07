package baseEntreprise;


public class Entreprise {
	private String nom;
	private int idBaseMin;
	private double coutMin;
	
	public Entreprise(String nom) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.idBaseMin = -1;
		this.coutMin = Integer.MAX_VALUE;
	}
	
	public void changeInfoBaseMoinsGouteux(int idBase, double cout) {
		// TODO Auto-generated method stub
		this.idBaseMin = idBase;
		this.coutMin = cout;
	}

	public int getIdBaseMin() {
		return idBaseMin;
	}

	public void setIdBaseMin(int idBaseMin) {
		this.idBaseMin = idBaseMin;
	}

	public double getCoutMin() {
		return coutMin;
	}

	public void setCoutMin(double coutMin) {
		this.coutMin = coutMin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
