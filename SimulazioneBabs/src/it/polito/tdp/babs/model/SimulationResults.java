package it.polito.tdp.babs.model;

public class SimulationResults {
	
	private int pickMiss;
	private int dockMiss;
	public SimulationResults(int pickMiss, int dockMiss) {
		super();
		this.pickMiss = pickMiss;
		this.dockMiss = dockMiss;
	}
	public int getPickMiss() {
		return pickMiss;
	}
	public void setPickMiss(int pickMiss) {
		this.pickMiss = pickMiss;
	}
	public int getDockMiss() {
		return dockMiss;
	}
	public void setDockMiss(int dockMiss) {
		this.dockMiss = dockMiss;
	}
	@Override
	public String toString() {
		return "Prese mancate: " + pickMiss + ", Ritorni mancati: " + dockMiss + "\n";
	}
	
	

}
