package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public class Simulazione {

	private LocalDate date;
	private double k;
	private Model model;                       //Ho bisogno di riferimento al model per usare DB
	
	private PriorityQueue pq;
	
	private enum EventType{                   //ENUM struttura che associa un intero a ogni elemento 
		PICK,
		DROP;
	}
	
	public Simulazione(LocalDate date, double k, Model model) {
		this.date = date;
		this.k = k;
		this.model = model;
		pq = new PriorityQueue();
	}

	public void run() {
		List<Trip> trips = model.getTripsByDate(date);
		
		//Aggiungere gli eventi alla PriorityQueue
		
		//Processare gli eventi
		
		//Ritornare il numero di pickMiss e dropMiss
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
