package baseEntreprise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

import comparateur.TriParEntreprise;
import comparateur.Trier;

public class BaseDonneeGeneral implements Comparator<Base> {
	private ArrayList<Base> listeBasePertinent;
	private ArrayList<Base> listeBase;
	private static BaseDonneeGeneral instance;
	private static TriParEntreprise triEntr;
	private static Trier trier;
	
	private BaseDonneeGeneral() {
		// TODO Auto-generated constructor stub
		this.listeBase = new ArrayList<Base>();
		this.listeBasePertinent = new ArrayList<Base>();
		BaseDonneeGeneral.triEntr = new TriParEntreprise();
		BaseDonneeGeneral.trier = new Trier();
	}
	
	public static BaseDonneeGeneral createBaseDonneeGeneral() {
		if (instance == null) {
			instance = new BaseDonneeGeneral();
		}
		return instance;
	}
	
	public void reinitialiserBaseDonnee() {
		// TODO Auto-generated method stub
		this.listeBase.removeAll(listeBase);
		this.listeBase.removeAll(listeBasePertinent);
	}

	@Override
	public int compare(Base arg0, Base arg1) {
		// TODO Auto-generated method stub
		return Double.compare(arg0.getQualitePrix(),arg1.getQualitePrix()); 	
	}

	/*--------------------------------GLOUTON--------------------------------*/

	public void addBase(Base base) {
		// TODO Auto-generated method stub
		this.listeBase.add(base);
	}
	
	public boolean researchBase(Base base) {
		// TODO Auto-generated method stub
		for (Base maBase : this.listeBase) {
			if (maBase == base) return true;
		}
		return false;
	}
	
	public int optimiserRecherche(ListeEntreprise entreprises,ArrayList<Base>liste) {
		int cout=0;
		String msg = "";
		for(Base b : liste) {
			int cpt=0;
			msg += "\t[Base n°" + b.getIdBase() + ", ayant pour cout : " + b.getCout() + " euros ";
			ListIterator<Entreprise>entreprisesIterator=entreprises.getListeEntreprises().listIterator();
			while(entreprisesIterator.hasNext()) {
				Entreprise entr = entreprisesIterator.next();
				if(b.chercheEntreprise(entr)==true) {
					cpt++;
					entreprisesIterator.remove();
				}
			}
			msg += "contient " + cpt + " entreprise(s)]\n";
			if(cpt>0) {
				cout+=b.getCout();
			}
			System.out.println(msg);
			msg = "";
		}
		return cout;
	}
	
	/*--------------------------------BRANCH AND BOUND--------------------------------*/

	public ListeEntreprise findTheBestSolutionOfRechercheBase(ListeEntreprise entreprises) {		
		ListIterator<Entreprise> entreprisesIterator = entreprises.getListeEntreprises().listIterator();
		while(entreprisesIterator.hasNext()) {
			Entreprise entreprise = entreprisesIterator.next();
			for(Base b : listeBasePertinent) {
				if(b.chercheEntreprise(entreprise) && entreprise.getCoutMin() > b.getCout()) {
					entreprise.changeInfoBaseMoinsGouteux(b.getIdBase(), b.getCout());
					break;
				}
			}
			System.out.println("Entreprise [" + entreprise.getNom() + "] a pour base le moins couteux : [Base n°" + entreprise.getIdBaseMin() + ", cout : " + entreprise.getCoutMin() + "]\n");
		}
		return entreprises;
	}
	
	public double determineCoutAndEntreprise(ListeEntreprise entreprises) {
		// TODO Auto-generated method stub
		double cout = 0;
		String msg = "";
		ArrayList<Integer> basePayer = new ArrayList<Integer>();
		System.out.println("Nos entreprises ont été retrouvé dans les bases suivants : ");
		ListIterator<Entreprise> entreprisesIterator = entreprises.getListeEntreprises().listIterator();
		while(entreprisesIterator.hasNext()) {
			Entreprise entreprise = entreprisesIterator.next();
			if (! basePayer.contains(entreprise.getIdBaseMin())) {
				cout += entreprise.getCoutMin();
				msg += "\t[Base n°" + entreprise.getIdBaseMin() + ", ayant pour cout : " + entreprise.getCoutMin() + " euros]\n";
				basePayer.add(entreprise.getIdBaseMin());
			}
		}
		System.out.println(msg + "\n");
		return cout;
	}
	
	public void optimiserRechercheApproffondies(ListeEntreprise entreprises) {
		
		for(Base b : listeBase) {
			ListIterator<Entreprise>entreprisesIterator=entreprises.getListeEntreprises().listIterator();
			while(entreprisesIterator.hasNext()) {
				Entreprise entr = entreprisesIterator.next();
				if(b.chercheEntreprise(entr)==true) {
					b.addEntrepriseTrouver(entr);
				}
			}
			if(b.getEntreprisesTrouves().size()>0) {
				listeBasePertinent.add(b);
			}
			
		}		
	}

	/*---		GETTERS AND SETTERS		---*/
	
	public ArrayList<Base> getListeBase() {
		return this.listeBase;
	}

	public void setListeBase(ArrayList<Base> listeBase) {
		this.listeBase = listeBase;
	}

	public static BaseDonneeGeneral getInstance() {
		return instance;
	}

	public static void setInstance(BaseDonneeGeneral instance) {
		BaseDonneeGeneral.instance = instance;
	}

	public void trierParQualitePrix(ArrayList<Base>liste) {
		Collections.sort(liste,this);
	}
	
	public void trierParCout(ArrayList<Base>liste) {
		Collections.sort(liste,trier);
	}
	
	public void trierParEntreprise(ArrayList<Entreprise>ents) {
		Collections.sort(ents,triEntr);
	}
	
	public ArrayList<Base> getListeBasePertinent() {
		return listeBasePertinent;
	}

	public void setListeBasePertinent(ArrayList<Base> listeBasePertinent) {
		this.listeBasePertinent = listeBasePertinent;
	}

}
