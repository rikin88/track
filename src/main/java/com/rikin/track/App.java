package com.rikin.track;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

	public static void main(String[] args) {
		System.out.println(createMenu());
		//initializeInventory();

		try (Scanner scanner = new Scanner(System.in)) {
			boolean shouldBreak = false;

			while (!shouldBreak) {
				String text = scanner.nextLine();
				ActionType actionType = determineType(text);
				switch (actionType) {
					case QUIT:
						System.out.println("GOODBYE!");
						shouldBreak=true;
						if (scanner != null) {
							scanner.close();
							break;
						}
						break;
					case RESTOCK:  
						System.out.println("In Restock action");
						break;
					case SET_WINNER:
						System.out.println("In Set Winner action");
						break;
					case PLACE_BET:
						System.out.println("In Place Bet action");
						break;
					case INVALID_COMMAND:
						System.out.println(determineErrorMessageToUser(text));
						break;
				}
				
				if(shouldBreak)
					break;
			}
		}

	}
	
	public static String determineErrorMessageToUser(String text) {
		
		String patternRegex = "(\\d+)(\\s*)(.*)"; //digit followed by space followed by anything
		String invalidAmountRegex2 = "\\D+";
		String errorMessage = "Invalid Command: " + text;
		
		if(Pattern.matches(patternRegex,  text)) {
			Pattern p = Pattern.compile(patternRegex);
			Matcher m = p.matcher(text);
			
			if(m.matches()) {
				String remainingString = m.group(3);
				if(!Pattern.matches(invalidAmountRegex2, remainingString)) {
					errorMessage = "invalid bet amount!" + remainingString;
				}
			}
		}
		
		return errorMessage;
			
	}
	
	/**
	 * Build the main menu
	 * 
	 * @return
	 */
	public static String createMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----Main menu----- \n");
		sb.append("\tR or r to restock inventory\n");
		sb.append("\tQ or q to quit the application\n");
		sb.append("\tW or w [1-7] to set winning horse number\n");
		sb.append("\t[1-7] <amount> to specify horse and bet amount\n");
		sb.append("\nChoice: ");

		return sb.toString();
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
