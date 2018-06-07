package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.babs.db.BabsDAO;

public class Model {
	
	private BabsDAO bdao;
	private StationIdMap smap;
	private List<Station> stations;
	
	public Model() {
		bdao = new BabsDAO();
		smap = new StationIdMap();
		stations = bdao.getAllStations(smap);
	}

	public List<CountResult> getTripCounts(LocalDate date) {
		if(getTripsByDate(date).size()==0)
			return null;                                           //Stampo errore nel controller se null 
		
		int numDep;
		int numArr;
		List<CountResult> results = new ArrayList<>();
		for(Station s : stations) {
			
			numDep = bdao.getDepartures(s, date);    //numero trip in partenza
			numArr = bdao.getArrivals(s, date);      //numero trip in arrivo
			
			CountResult cc = new CountResult(s, numDep, numArr);    //WRAPPER
			results.add(cc);
		}
		
		Collections.sort(results);
		return results;
	}
	
	public List<Station> getStations(){
		return stations;
	}
	
	public StationIdMap getIdMapStation() {
		return this.smap;
	}
	
	public List<Trip> getTripsByDate(LocalDate date){
		return bdao.getAllTrips(date);
	}

	public SimulationResults simula(LocalDate date, Double k) {
		Simulazione sim = new Simulazione(date, k, this);            //Simulazione in classe a parte
		
		sim.run();
		
		return sim.getResult();
	}

}
