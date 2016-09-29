package com.rikin.track;

public enum ActionType {
	RESTOCK("(R|r)"), 
	SET_WINNER("(W|w)(\\s+)(\\d+)"), 
	//PLACE_BET("\\d+\\s+\\d+"),
	PLACE_BET("(\\d+)(\\s*)(.*)"),
	QUIT("(Q|q)"), 
	DONE("(DONE|done)"),
	INVALID_COMMAND("");
	
	private String pattern;
	
	ActionType(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}
}
