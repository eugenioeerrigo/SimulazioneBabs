package it.polito.tdp.babs.model;

import java.util.HashMap;
import java.util.Map;

public class StationIdMap {
	
	private Map<Integer, Station> map;
	
	public StationIdMap() {
		map = new HashMap<>();
	}
	
	public Station get(int id) {
		return map.get(id);
	}
	
	public Station get(Station station) {
		Station old = map.get(station.getStationID());
		if(old==null) {
			map.put(station.getStationID(), station);
			return station;
		}
		return old;
	}

}
