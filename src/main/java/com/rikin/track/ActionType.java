package com.rikin.track;

/**
 * Enum holds Possible ActionTypes along with regular expression to match user input
 * @author Rikin Asher
 *
 */
public enum ActionType {
	RESTOCK("(R|r)"), 
	SET_WINNER("(W|w)(\\s+)(\\d+)"), 
	//PLACE_BET("\\d+\\s+\\d+"),
	PLACE_BET("(\\d+)(\\s*)(.*)"),
	QUIT("(Q|q)"), 
	DONE("(DONE|done)"),
	INVALID_COMMAND(""),
	INVALID_BET_AMOUNT("");
	
	private String pattern;
	
	ActionType(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}
}
