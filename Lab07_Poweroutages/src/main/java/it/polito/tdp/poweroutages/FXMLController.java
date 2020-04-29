package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
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
		// TODO Auto-generated method stub
		
	}
}
