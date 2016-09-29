package com.rikin.track.domain;

public class Horse {
	private String horseName;
	private int horseOdds;
	private boolean didWin;
	
	public Horse(String horseName, int horseOdds, boolean didWin) {
		this.horseName = horseName;
		this.horseOdds = horseOdds;
		this.didWin = didWin;
	}
	
	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	public int getHorseOdds() {
		return horseOdds;
	}
	public void setHorseOdds(int horseOdds) {
		this.horseOdds = horseOdds;
	}
	
	public boolean isDidWin() {
		return didWin;
	}

	public void setDidWin(boolean didWin) {
		this.didWin = didWin;
	}
	
	public String toString() {
		String didWinString = didWin?"Won":"Lost";
		return horseName + ", " + horseOdds + ", " + didWinString;
	}
	
}
