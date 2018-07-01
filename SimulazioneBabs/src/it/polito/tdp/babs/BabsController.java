package it.polito.tdp.babs;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.babs.model.Model;
import it.polito.tdp.babs.model.SimulationResults;
import it.polito.tdp.babs.model.StationNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class BabsController {

	private Model model;

	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private DatePicker pickData;

	@FXML
	private Slider sliderK;

	@FXML
	private TextArea txtResult;

	@FXML
	void doContaTrip(ActionEvent event) {
		txtResult.clear();
		try {
			
			LocalDate date = pickData.getValue();
			
			if(date==null) {
				txtResult.appendText("Seleziona una data valida");
				return;
			}			
			
			List<StationNumber> partenza = model.tripInPartenza(date);
			if(partenza.isEmpty())
				txtResult.appendText("Non sono presenti trips nella data selezionata.\n");
			else {
			
				Collections.sort(partenza);
				txtResult.appendText("Trip in partenza per la data selezionata:\n");
				for(StationNumber sn : partenza)
					txtResult.appendText(sn.toString()+"\n");
	
				
				txtResult.appendText("\nTrip in arrivo per la data selezionata:\n");
				List<StationNumber> arrivo = model.tripInArrivo(date);
				Collections.sort(arrivo);
				for(StationNumber sn : arrivo)
					txtResult.appendText(sn.toString()+"\n");
			}
			
			
		}catch(RuntimeException e) {
			txtResult.appendText("Errore nella connessione al DB");
		}
	}

	@FXML
	void doSimula(ActionEvent event) {
		txtResult.clear();
		
		try{
			
			LocalDate date = pickData.getValue();
			if(date.getDayOfWeek().getValue()<6) {
			
			double K = this.sliderK.getValue();
			SimulationResults res = model.simula(date, K);
			
			txtResult.appendText("SIMULAZIONE\nRisultati:\n"+res.toString());
			
			}else {
				txtResult.appendText("Scegli un giorno feriale!");
			}
			
			
			
		}catch(RuntimeException e) {
			txtResult.appendText("Errore nella connessione al DB.");
		}
	}

	@FXML
	void initialize() {
		assert pickData != null : "fx:id=\"pickData\" was not injected: check your FXML file 'Babs.fxml'.";
		assert sliderK != null : "fx:id=\"sliderK\" was not injected: check your FXML file 'Babs.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Babs.fxml'.";

		pickData.setValue(LocalDate.of(2013, 9, 1));
	}
}
