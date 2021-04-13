package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {
	
	AnagrammaDAO anagrammaDAO;
	
	public Model() {
		this.anagrammaDAO= new AnagrammaDAO();
	}

	public List<String> anagrammi(String parola) {
		List<String> risultato = new ArrayList<>();
		permuta("",parola,0,risultato);
		return risultato;
	}
	
	private void permuta (String parziale, String lettere, int livello, List<String> risultato) {
		if (lettere.length()==0) { //caso terminale
			risultato.add(parziale);
		} else { //fai ricorsione
			//il sottoproblema e' una lettera dellla parola
			for (int pos=0; pos<lettere.length(); pos++) {
				char tent= lettere.charAt(pos);
				String newParz = parziale+tent;
				String newLettere= lettere.substring(0,pos)+lettere.substring(pos+1); //togli carattere[pos] da lettere
				
				/*if (anagrammaDAO.isCorrect(newParz)) { //newParziale e' nuovo anagramma valido
					
				} else {
					
				}*/
				
				this.permuta(newParz, newLettere, livello, risultato);
			}
		}
	}
	
	public List<String> anagrammiCorretti (List<String> risultato) {
		List<String> temp= new ArrayList<String>();
		for (String a : risultato) {
			if (this.anagrammaDAO.isCorrect(a))
				temp.add(a);
		}
		return temp;
	}
	
	public List<String> anagrammiSbagliati (List<String> risultato) {
		List<String> temp= new ArrayList<String>();
		for (String a : risultato) {
			if (this.anagrammaDAO.isCorrect(a)==false)
				temp.add(a);
		}
		return temp;
	}
	
}
