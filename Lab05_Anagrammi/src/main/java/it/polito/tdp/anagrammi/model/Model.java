package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Set;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {
	
	AnagrammaDAO anagrammaDAO;
	
	public Model() {
		this.anagrammaDAO= new AnagrammaDAO();
	}

	public Set<String> anagrammi(String parola) {
		Set<String> risultato = new HashSet<>();
		permuta("",parola,0,risultato);
		return risultato;
	}
	
	private void permuta (String parziale, String lettere, int livello, Set<String> risultato) {
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
	
	public Set<String> anagrammiCorretti (Set<String> risultato) {
		Set<String> temp= new HashSet<String>();
		for (String a : risultato) {
			if (this.anagrammaDAO.isCorrect(a))
				temp.add(a);
		}
		return temp;
	}
	
	public Set<String> anagrammiSbagliati (Set<String> risultato) {
		Set<String> temp= new HashSet<String>();
		for (String a : risultato) {
			if (this.anagrammaDAO.isCorrect(a)==false)
				temp.add(a);
		}
		return temp;
	}
	
}
