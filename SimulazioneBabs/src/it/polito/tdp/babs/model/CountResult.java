package it.polito.tdp.babs.model;

public class CountResult implements Comparable<CountResult>{   //Metodo wrapper: è più corretto per raccogliere più elementi 
	
	private Station station;
	private int numArrivals;
	private int numDepartures;
	
	public CountResult(Station station, int numArrivals, int numDepartures) {
		this.station = station;
		this.numArrivals = numArrivals;
		this.numDepartures = numDepartures;
	}

	@Override
	public String toString() {
		return String.format("%-50s %4d %4d\n", station.getName(), numArrivals, numDepartures);  // -50: allinea a sinistra, ho 50 spazi per stampare
	}

	@Override
	public int compareTo(CountResult other) {
	//	return (int) (this.station.getLat()-other.station.getLat());   
	    return Double.compare(this.station.getLat(), other.station.getLat());
	}

	
	

}
