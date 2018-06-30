package it.polito.tdp.babs.model;

import java.util.HashMap;
import java.util.Map;

public class StationIdMap {
	
	private Map<String, Station> map;
	
	public StationIdMap() {
		map = new HashMap<>();
	}
	
	public Station get(String name) {
		return map.get(name);
	}
	
	public Station get(Station station) {
		Station old = map.get(station.getName());
		if(old==null) {
			map.put(station.getName(), station);
			return station;
		}
		return old;
	}

}
