package it.polito.tdp.babs.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{
	
	private EventType type;
	private Trip trip;
	private LocalDateTime time;
	
	public Event(EventType type, Trip trip, LocalDateTime time) {
		this.type = type;
		this.trip = trip;
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public int compareTo(Event arg0) {
		return this.time.compareTo(arg0.time);
	}
	
	
	
	

}
