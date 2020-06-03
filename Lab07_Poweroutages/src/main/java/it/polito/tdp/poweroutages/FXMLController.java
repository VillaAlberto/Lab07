package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> chBoxNERC;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnAnalyze;

    @FXML
    private TextArea txtCombination;

	private Model model;

    @FXML
    void doAnalyze(ActionEvent event) {
    	try {
    		txtCombination.clear();
    		int maxYears=Integer.parseInt(txtYears.getText());
    		int maxHours=Integer.parseInt(txtHours.getText());
    		Nerc nerc=chBoxNERC.getValue();
    		List<PowerOutage> combination= model.worstCase(nerc, maxYears, maxHours);
    		txtCombination.setText("Event for nerc "+nerc.getValue()+" :"+model.getOutagesByNerc(nerc).size()+"\n");
    		txtCombination.appendText("Tot people affected: "+model.getWorstAffected()+"\n");
    		txtCombination.appendText("Tot hours of outage: "+model.sumHoursValue(combination)+"\n");
    		String s="";
    		for (PowerOutage p: combination)
    		{
    			s+=p.toString();
    		}
    		txtCombination.appendText(s);
    	}
    	catch(NumberFormatException e) {
    		txtCombination.setText("Errore inserimento dati");
    	}
    	catch (Exception e) {
			txtCombination.setText("Errore di runtime");
		}
    }

    @FXML
    void initialize() {
        assert chBoxNERC != null : "fx:id=\"chBoxNERC\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalyze != null : "fx:id=\"btnAnalyze\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCombination != null : "fx:id=\"txtCombination\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		chBoxNERC.getItems().addAll(model.getNercList());
		chBoxNERC.setValue(model.getNercList().get(0));
		txtCombination.setStyle("-fx-font-family: monospace");
		
	}
}
