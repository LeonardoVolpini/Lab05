package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParolaInput;

    @FXML
    private TextArea txtAnCorr;

    @FXML
    private TextArea txtAnSbagl;

    @FXML
    void handleCalcolaAnagrammi(ActionEvent event) {
    	String parola= this.txtParolaInput.getText();
    	if (parola.isEmpty()) {
    		this.txtAnCorr.setText("Inserire una parola della quale calcolare gli anagrammi");
    		return;
    	}
    	List<String> anagrammi =this.model.anagrammi(parola);
    	List<String> corretti=this.model.anagrammiCorretti(anagrammi);
    	List<String> sbagliati= this.model.anagrammiSbagliati(anagrammi);
    	if (corretti.isEmpty()) {
    		this.txtAnCorr.setText("Non ci sono anagrammi corretti di "+parola);
    		return;
    	}
    	if (sbagliati.isEmpty()) {
    		this.txtAnSbagl.setText("Non ci sono anagrammi sbagliati di "+parola);
    		return;
    	}
    	this.txtAnCorr.setText(print(corretti));
    	this.txtAnSbagl.setText(print(sbagliati));
    }

    @FXML
    void handleReset(ActionEvent event) {
    	this.txtAnCorr.clear();
    	this.txtAnSbagl.clear();
    	this.txtParolaInput.clear();
    }

    @FXML
    void initialize() {
        assert txtParolaInput != null : "fx:id=\"txtParolaInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnCorr != null : "fx:id=\"txtAnCorr\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnSbagl != null : "fx:id=\"txtAnSbagl\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
    
    public String print (List<String> temp) {
    	String stampa="";
    	for (String s : temp)
    		stampa=stampa+s+"\n";
    	return stampa;
    }
    
}
