package baseEntreprise;

import java.lang.reflect.Array;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import comparateur.TriParEntreprise;
import comparateur.Trier;

public class BaseDonneeGeneral implements Comparator<Base> {
	private static BaseDonneeGeneral instance;
	private ArrayList<Base> listeBase;
	private static Trier trier;
	private static TriParEntreprise triEntr;
	private ArrayList<Base> listeBasePertinent;
	private BaseDonneeGeneral() {
		// TODO Auto-generated constructor stub
		this.listeBase = new ArrayList<Base>();
		listeBasePertinent= new ArrayList<Base>();
		trier=new Trier();
		triEntr = new TriParEntreprise();
	}
	
	public static BaseDonneeGeneral createBaseDonneeGeneral() {
		if (instance == null) {
			instance = new BaseDonneeGeneral();
		}
		return instance;
	}
	
	
	public boolean researchBase(Base base) {
		// TODO Auto-generated method stub
		for (Base maBase : this.listeBase) {
			if (maBase == base) return true;
		}
		return false;
	}
	
	public void addBase(Base base) {
		// TODO Auto-generated method stub
		this.listeBase.add(base);
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

	@Override
	public int compare(Base arg0, Base arg1) {
		// TODO Auto-generated method stub
		return Double.compare(arg0.getQualitePrix(),arg1.getQualitePrix()); 	
	}
	
	public ArrayList<Base> getListeBasePertinent() {
		return listeBasePertinent;
	}

	public void setListeBasePertinent(ArrayList<Base> listeBasePertinent) {
		this.listeBasePertinent = listeBasePertinent;
	}

	public int optimiserRecherche(ListeEntreprise entreprises,ArrayList<Base>liste) {
		int cout=0;
		for(Base b : liste) {
			int cpt=0;
			System.out.println("[Base n°"+b.getIdBase()+", cout : "+b.getCout() + "]");
			ListIterator<Entreprise>entreprisesIterator=entreprises.getListeEntreprises().listIterator();
			while(entreprisesIterator.hasNext()) {
				Entreprise entr = entreprisesIterator.next();
				if(b.chercheEntreprise(entr)==true) {
					cpt++;
					entreprisesIterator.remove();
				}
			}
			System.out.println("On a retrouvé " + cpt + " dans la base n°" + b.getIdBase() + "\n");
			if(cpt>0) {
				cout+=b.getCout();
			}
		}
		return cout;
	}

	public ListeEntreprise findTheBestSolutionOfRechercheBase(ListeEntreprise entreprises) {		
		ListIterator<Entreprise> entreprisesIterator = entreprises.getListeEntreprises().listIterator();
		while(entreprisesIterator.hasNext()) {
			Entreprise entreprise = entreprisesIterator.next();
			for(Base b : listeBasePertinent) {
				if(b.chercheEntreprise(entreprise) && entreprise.getCoutMin() > b.getCout()) {
					entreprise.changeInfoBaseMoinsGouteux(b.getIdBase(), b.getCout());
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
		trierParCout(listeBasePertinent);
		
	}
	
	public void afficheEntr(ArrayList<Entreprise>stock) {
		for(Entreprise e:stock) {
			System.out.println(e.getNom());
		}
	}
	
	
	public void rechercheEqMoinsCher(){
	int cout=0;
	Base b=listeBasePertinent.get(1);
	Base base = listeBasePertinent.get(1);
	for(int i=2;i<listeBasePertinent.size();i++) {
		double nb= base.getCout()+listeBasePertinent.get(i).getCout();
//	System.out.println("cout nb:"+nb);
//		System.out.println("cout specimen :"+b.getCout());
		if((nb<b.getCout())) {
			ArrayList<Entreprise>stock=new ArrayList<Entreprise>();
			stock.addAll(base.getEntreprisesTrouves());
			stock.addAll(listeBasePertinent.get(i).getEntreprisesTrouves());
			trierParEntreprise(stock);
			trierParEntreprise(b.getEntreprisesTrouves());
			System.out.println("###########test##########");
			afficheEntr(stock);
			System.out.println("###########test2##########");
			afficheEntr(b.getEntreprisesTrouves());
			
			boolean var = stock.stream().allMatch(element -> (b.getEntreprisesTrouves().contains(element)));
			System.out.println(var);
			if(var) {
				cout+=nb;
				System.out.println("le nouveau cout est :"+cout);
//				rechercheEqMoinsCher(base);
				
			}
		}
	}
	}
	
	public void showBases() {
		for(Base b:listeBase) {
		System.out.println(b.getCout());
		}
	}
}
