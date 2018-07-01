package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Simulatore {
	
	private PriorityQueue<Event> queue;
	private int PICKMISS = 0;
	private int DROPMISS = 0;
	private Model model;
	private Map<Station, Integer> stationCount;
	
	public Simulatore(LocalDate date, double K, Model model) {
		queue = new PriorityQueue<>();
		stationCount = new HashMap<>();
		this.model = model;
		
		for(Trip t : model.getTripsByDate(date))
			queue.add(new Event(EventType.PICK, t, t.getStartDate()));
		
		for(Station s : model.getStations())
			stationCount.put(s, (int) (s.getDockCount()*K));
		
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e = queue.poll();
			
			switch(e.getType()) {
			case PICK:
				
				Station station = model.getSmap().get(e.getTrip().getStartStationID());
				int count = stationCount.get(station);
				
				if(count > 0) {
					
					count--;
					stationCount.put(station, count);
					queue.add(new Event(EventType.DROP, e.getTrip(), e.getTrip().getEndDate()));
					
				}else
					PICKMISS++;
				
				break;
				
			case DROP:
				station = model.getSmap().get(e.getTrip().getEndStationID());;
				count = stationCount.get(station);
				
				if(station.getDockCount() > count) {
					
					count++;
					stationCount.put(station, count);
					
				}else 
					DROPMISS++;
				
				break;
			}
			
		}
	}
	
	public SimulationResults getResults(){
		return new SimulationResults(PICKMISS, DROPMISS);
	}

}
