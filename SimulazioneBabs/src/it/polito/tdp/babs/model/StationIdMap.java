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
	
	public Station get(Station s) {
		Station old = map.get(s.getStationID());
		if(old==null) {
			map.put(s.getStationID(), s);
			return s;
		}
		return old;
	}
	
	public void put(int id, Station s) {
		map.put(id, s);
	}

}
