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
	}
	
	public List<Station> getStations(){
		stations = dao.getAllStations(smap);
		return stations;
	}
	
	public List<StationNumber> tripInPartenza(LocalDate date){
		return dao.tripInPartenza(date, smap);
	}

	public List<StationNumber> tripInArrivo(LocalDate date) {
		return dao.tripInArrivo(date, smap);
	}
}
