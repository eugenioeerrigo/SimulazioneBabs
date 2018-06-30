package it.polito.tdp.babs.model;

public class StationNumber {

	private Station station;
	private int number;
	
	public StationNumber(Station station, int number) {
		this.station = station;
		this.number = number;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Station: " + station + " - " + number;
	}
	
	
	
}
