package baseEntreprise;

import java.util.ArrayList;

public class BaseDonneeGeneral {
	private static BaseDonneeGeneral instance;
	private ArrayList<Base> listeBase;

	private BaseDonneeGeneral() {
		// TODO Auto-generated constructor stub
		this.listeBase = new ArrayList<Base>();
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
}
