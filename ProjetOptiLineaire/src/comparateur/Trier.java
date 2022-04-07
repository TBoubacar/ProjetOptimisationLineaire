package comparateur;

import java.util.Comparator;

import baseEntreprise.Base;
import baseEntreprise.Entreprise;

public class Trier implements Comparator<Base>{

	@Override
	public int compare(Base b1, Base b2) {
		return Double.compare(b2.getEntreprisesTrouves().size(),b1.getEntreprisesTrouves().size()); 	
	}

	
}
