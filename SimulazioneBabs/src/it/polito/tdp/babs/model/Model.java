package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.babs.db.BabsDAO;

public class Model {
	
	private BabsDAO dao;
	private List<Station> stations;
	private StationIdMap smap;
	
	public Model() {
		dao = new BabsDAO();
		smap = new StationIdMap();
		stations = dao.getAllStations(smap);
	}
	
	public StationIdMap getSmap() {
		return smap;
	}

	public List<Station> getStations(){
		return stations;
	}
	
	public List<StationNumber> tripInPartenza(LocalDate date){
		return dao.tripInPartenza(date, smap);
	}

	public List<StationNumber> tripInArrivo(LocalDate date) {
		return dao.tripInArrivo(date, smap);
	}

	public SimulationResults simula(LocalDate date, double k) {
		
		Simulatore sim = new Simulatore(date, k, this);
		sim.run();
		
		return sim.getResults();
	}

	public List<Trip> getTripsByDate(LocalDate date) {
		return dao.getTripsByDate(date);
	}
	
	
}
