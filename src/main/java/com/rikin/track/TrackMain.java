package com.rikin.track;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main Horse Betting Application
 * 
 * @author Rikin Asher
 *
 */
public class TrackMain {
	
	private static Machine machine;
	
	/**
	 * Main method responsible to collect user input
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<String> inputList = new ArrayList<String>();
		boolean keepReading=true;
		try (Scanner scanner = new Scanner(System.in)) {
			while(keepReading) {
				String text = scanner.nextLine();
				
				if(text.matches(ActionType.QUIT.getPattern())) {
					keepReading=false;
					break;
				}
				else if(text.matches(ActionType.DONE.getPattern())) {
					machine = Machine.setupInitialData();
					System.out.println(machine.toString());
					processInput(inputList, machine);
					break;
				}
				else {
					inputList.add(text);
				}
			}
		}
	}

	/**
	 * This method is responsible for processing the list of commands supplied by the user.
	 * 
	 * @param inputCommands
	 * @param machine
	 * @return
	 */
	public static String processInput(List<String> inputCommands, Machine machine) {
		inputCommands.forEach(input -> processCommand(determineType(input), machine, input));

		StringBuilder output = new StringBuilder();
		return output.toString();
	}
	
	/**
	 * Method determines specific actions to call depending on type of Action
	 * 
	 * @param actionType
	 * @param machine
	 * @param input
	 */

	public static void processCommand(ActionType actionType, Machine machine, String input) {
		switch (actionType) {
		
			case RESTOCK:
				machine.restockInventory();
				System.out.println("RESTOCKING INVENTORY");
				System.out.println(machine.toString());
				break;
				
			case SET_WINNER:
				if(machine.setWinningHorseNumber(Integer.parseInt(input.substring(2)))) {
					System.out.println("SETTING WINNING HORSE NUMBER: " + input.substring(2));
				}
				System.out.println(machine.toString());
				break;
				
			case PLACE_BET:
				System.out.println(placeBet(input));
				System.out.println(machine.toString());
				break;
				
			case INVALID_BET_AMOUNT:
				System.out.println(invalidBetAmount(input));
				break;
				
			case INVALID_COMMAND:
				System.out.println("INVALID COMMAND: " + input);
				break;
				
			case DONE:
				System.out.println("Done!");
				break;
				
			case QUIT:
				System.out.println("Quit!");
				break;
				
			default:
				break;
			}
	}
	
	/**
	 * Method called when input is considered an invalid bet amount
	 * 
	 * @param input
	 * @return String - message stating invalid bet placed.
	 */
	private static String invalidBetAmount(String input) {
		
		StringTokenizer st = new StringTokenizer(input, " ");
		String horseNumber = st.nextToken();
		String betAmountAsString = st.nextToken();
		String message = "INVALID BET: " + betAmountAsString;
		return message;
	}
	
	
	/** 
	 * Method called when input is consided to be a valid placed bet
	 * 
	 * @param input
	 * @return String - confirmation message of bet placed.
	 */
	private static String placeBet(String input) {
		
		String message = "";
		StringTokenizer st = new StringTokenizer(input, " ");
		
		int horseNumber = Integer.parseInt(st.nextToken());
		int betAmount = Integer.parseInt(st.nextToken());
		int payout = machine.placeHorseBet(horseNumber, betAmount);
		String horseName = machine.getHorseInformation().getHorses().get(horseNumber).getHorseName();
		
		if(payout>=0) {
			try {
				machine.deductCashFromInventory(payout);
				message = "PAYOUT: " + horseName + ", $" + payout;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		else {
			message = "NO PAYOUT: " + horseName;
		}
		
		return message;
	}
	
	/**
	 * Method to determine invalid bet amount 
	 * 
	 * @param text
	 * @return String - returns the invalid bet amount
	 */
	private static String determineInvalidBetAmount(String text) {
		String errorMessage = null;
		String invalidAmountRegex2 = "\\d+";
		String patternRegex = "(\\d+)(\\s*)(.*)"; 
		
		if(Pattern.matches(patternRegex,  text)) {
			Pattern p = Pattern.compile(patternRegex);
			Matcher m = p.matcher(text);
			
			String remainingString = "";
			if(m.matches()) {
				remainingString = m.group(3);
			}
			
			//String remainingString = m.group(3);
			if(!Pattern.matches(invalidAmountRegex2, remainingString)) {
				errorMessage = "Invalid bet amount! " + remainingString;
			}	
		}
		return errorMessage;
	}
	
	/**
	 * Method determines the type of action to be performed 
	 * 
	 * @param text
	 * @return
	 */
	public static ActionType determineType(String text) {

		ActionType actionType = null;
		String message = "";
		
		if(text.matches(ActionType.PLACE_BET.getPattern()))  {
			message = determineInvalidBetAmount(text);
			if(message==null) {
				actionType = ActionType.PLACE_BET;
			}
			else {				
				actionType = ActionType.INVALID_BET_AMOUNT;
			}
		}
			//actionType=ActionType.PLACE_BET;
		else if(text.matches(ActionType.SET_WINNER.getPattern()))
			actionType=ActionType.SET_WINNER;
		else if(text.matches(ActionType.RESTOCK.getPattern()))
			actionType=ActionType.RESTOCK;
		else if(text.matches(ActionType.QUIT.getPattern()))
			actionType=ActionType.QUIT;
		else if(text.matches(ActionType.DONE.getPattern()))
			actionType=ActionType.DONE;
		else {
			actionType=ActionType.INVALID_COMMAND;
		}
		
		return actionType;
	}
}
