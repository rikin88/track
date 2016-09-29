package com.rikin.track;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrackMain {
	
	public static void main(String[] args) {
		/*List<String> inputList = new ArrayList<String>();
		boolean keepReading=true;
		try (Scanner scanner = new Scanner(System.in)) {
			while(keepReading) {
				String text = scanner.nextLine();
				
				if(text.matches(ActionType.QUIT.getPattern())) {
					keepReading=false;
					break;
				}
				else if(text.matches(ActionType.DONE.getPattern())) {
					break;
				}
				else {
					inputList.add(text);
				}
			}
		}*/
		
		List<String> inputList =  setupStubOutput();
		
		Machine machine = Machine.setupInitialData();
		System.out.println(machine.toString());
		
		
	}
	
	public static List<String> setupStubOutput() {
		List<String> inputList = new ArrayList<String>();
		inputList.add("R");
		inputList.add("W 7");
		inputList.add("1 55");
		inputList.add("4a 44");
		inputList.add("4 10.25");
		inputList.add("10 55");
		inputList.add("DONE");
		
		inputList.forEach(input -> System.out.println(input));
		return inputList;
	}
	
	/*public String processInput(List<String> inputCommands) {
		inputCommands.forEach(action);
		
		StringBuilder output = new StringBuilder();
		return output.toString();
	}*/
	
	public static boolean passAdditionalTest(String text, String message) {
		String invalidAmountRegex2 = "\\d+";
		String patternRegex = "(\\d+)(\\s*)(.*)"; 
		
		if(Pattern.matches(patternRegex,  text)) {
			Pattern p = Pattern.compile(patternRegex);
			Matcher m = p.matcher(text);
			
			if(m.matches()) {
				//System.out.println(m.group(1));
				//System.out.println(m.group(2));
				//System.out.println(m.group(3));
			}
			
			String remainingString = m.group(3);
			if(!Pattern.matches(invalidAmountRegex2, remainingString)) {
				message = "Invalid bet amount! " + remainingString;
				System.out.println(message);
				return false;
			}	
		}
		return true;
	}
	
	public static ActionType determineType(String text) {

		ActionType actionType = null;
		String message = "";
		
		if(text.matches(ActionType.PLACE_BET.getPattern()))  {
			if(passAdditionalTest(text, message)) {
				actionType = ActionType.PLACE_BET;
			}
			else {
				actionType = ActionType.INVALID_COMMAND;
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
		
		//System.out.println("actionType is: " + actionType + " for text: " + text);
		
		return actionType;
	}
}
