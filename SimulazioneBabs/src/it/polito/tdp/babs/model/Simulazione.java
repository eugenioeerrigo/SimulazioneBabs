package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.sun.scenario.effect.Blend.Mode;

public class Simulazione {

	private LocalDate date;
	private double k;
	private Model model;                       //Ho bisogno di riferimento al model per usare DB
	private int dropMiss = 0;
	private int pickMiss = 0;
	private PriorityQueue<Event> pq;
	private Map<Station, Integer> stationCount;
	
	
	private enum EventType{                   //ENUM struttura che associa un intero a ogni elemento 
		PICK,
		DROP;
	}
	
	public Simulazione(LocalDate date, double k, Model model) {
		this.date = date;
		this.k = k;
		this.model = model;
		pq = new PriorityQueue<>();
		stationCount = new HashMap<>();
	}

	public void run() {
		List<Trip> trips = model.getTripsByDate(date);
		
		//Aggiungere gli eventi alla PriorityQueue
		for(Trip t : trips){
			pq.add(new Event(EventType.PICK, t.getStartDate(), t));        //Inserisco tutti gli eventi di PICK
		}
		
		//Inizializzo numero bici per ogni stazione
		for(Station s: model.getStations()) {
			stationCount.put(s, (int)(s.getDockCount()*k));       //Associo il conteggio delle bici per ogni stazione con mappa
		}
		
		//Processare gli eventi
		while(!pq.isEmpty()) {
			Event e = pq.poll();         //null se pq vuota, ma se vuota non entra nel while, quindi non controllo
			
			switch(e.type) {
			case PICK:                 //se c'è almeno 1 bici disponibile, la prendo (evento drop), altrimenti PickMiss
				Station station = model.getIdMapStation().get(e.trip.getStartStationID());
				int count = stationCount.get(station);
				
				if(count>0) {          //DISPONIBILE
					count--;
					stationCount.put(station, count);        //Valore aggiornato con put!!
					pq.add(new Event(EventType.DROP, e.trip.getEndDate(), e.trip));
				}else
					pickMiss++;
				
				break;
			case DROP:
				station = model.getIdMapStation().get(e.trip.getEndStationID());
				count = stationCount.get(station);
				
				if(station.getDockCount()>count) {       //Ci sono posti per lasciare la bici
					count++;
					stationCount.put(station, count);
				}else
					dropMiss++;
				break;
			}
		}
		
	}
	
	//Ritornare il numero di pickMiss e dropMiss su classe WRAPPER
	public SimulationResults getResult() { 
		return new SimulationResults(pickMiss, dropMiss);
	}
	
	private class Event implements Comparable<Event>{                   //classe Event esiste soltanto in relazione alla simulazione
		EventType type;
		LocalDateTime date;
		Trip trip;
		
		public Event(EventType type, LocalDateTime date, Trip trip) {
			this.type = type;
			this.date = date;
			this.trip = trip;
		}

		@Override
		public int compareTo(Event other) {                              //Serve a ordinare (in background) la queue
			return date.compareTo(other.date);
		}
		
		
	}

}
