package com.rikin.track;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrackMain {

	private static final String DONE = "DONE";
	
	public static void main(String[] args) {
		List<String> inputList = new ArrayList<>();
		boolean keepReading=true;
		try (Scanner scanner = new Scanner(System.in)) {
			while(keepReading) {
				String text = scanner.nextLine();
				
				if(DONE.equals(text))
					break;
				inputList.add(text);
			}
		}
		
		System.out.println("list is: " + inputList.toString());
		
		
	}
	
	public String processInput(List<String> inputCommands) {
		
		StringBuilder output = new StringBuilder();
		
		return output.toString();
	}
	
	public static ActionType determineType(String text) {

		ActionType actionType = null;
		
		if(text.matches(ActionType.PLACE_BET.getPattern()))
			actionType=ActionType.PLACE_BET;
		else if(text.matches(ActionType.SET_WINNER.getPattern()))
			actionType=ActionType.SET_WINNER;
		else if(text.matches(ActionType.RESTOCK.getPattern()))
			actionType=ActionType.RESTOCK;
		else if(text.matches(ActionType.QUIT.getPattern()))
			actionType=ActionType.QUIT;
		else {
			actionType=ActionType.INVALID_COMMAND;
		}
		
		//System.out.println("actionType is: " + actionType + " for text: " + text);
		
		return actionType;
	}
}
