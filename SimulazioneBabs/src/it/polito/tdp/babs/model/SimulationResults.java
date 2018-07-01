package it.polito.tdp.babs.model;

public class SimulationResults {
	
	private int pickMiss;
	private int dropMiss;
	
	public SimulationResults(int pickMiss, int dropMiss) {
		this.pickMiss = pickMiss;
		this.dropMiss = dropMiss;
	}

	public int getPickMiss() {
		return pickMiss;
	}

	public void setPickMiss(int pickMiss) {
		this.pickMiss = pickMiss;
	}

	public int getDropMiss() {
		return dropMiss;
	}

	public void setDropMiss(int dropMiss) {
		this.dropMiss = dropMiss;
	}

	@Override
	public String toString() {
		return String.format("Prese mancate: %d - Ritorni mancati: %d", this.pickMiss, this.dropMiss);
	}

	

}
