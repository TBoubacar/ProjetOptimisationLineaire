package comparateur;

import java.util.Comparator;

import baseEntreprise.Entreprise;

public class TriParEntreprise  implements Comparator<Entreprise>{

		@Override
		public int compare(Entreprise arg0, Entreprise arg1) {
			// TODO Auto-generated method stub
			return arg0.getNom().compareTo(arg1.getNom());
		}
		
	
}
