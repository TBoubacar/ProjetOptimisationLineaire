package baseEntreprise;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Scenario {

	private ArrayList<String>listesBases;
	private ArrayList<String>listeEntreprises;
	public Scenario() {
		listesBases = new ArrayList<String>();
		listeEntreprises = new ArrayList<String>();
	}
	
	public void lecturesbases(String filename) throws IOException {
		  File file = new File(filename);    
	      FileReader fr = new FileReader(file);  
	      BufferedReader br = new BufferedReader(fr);  
	      String line;
	      try {
			while((line = br.readLine()) != null){
			 listesBases.add(line);		
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lectureEntreprises(String filename) throws IOException{
		 File file = new File(filename);    
	      FileReader fr = new FileReader(file);  
	      BufferedReader br = new BufferedReader(fr);  
	      String line;
	      try {
			while((line = br.readLine()) != null){
			 listeEntreprises.add(line);		
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getListesBases() {
		return listesBases;
	}

	public void setListesBases(ArrayList<String> listesBases) {
		this.listesBases = listesBases;
	}
	
	public ArrayList<String> getListeEntreprises() {
		return listeEntreprises;
	}

	public void setListeEntreprises(ArrayList<String> listeEntreprises) {
		this.listeEntreprises = listeEntreprises;
	}

	public void showScenarios() {
		for (String base : listesBases) {
			System.out.println(base);
		}
	}
	
}
