package com.rikin.track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

public class InputTest {
	
	@BeforeClass
	public static void init() {
		App app = new App();
	}
	
	@Test
    public void testValidRestock() {
		String input = "R";
		ActionType output = App.determineType(input);
		assertEquals(ActionType.RESTOCK, output);
		
		input="r";
		output=App.determineType(input);
		assertEquals(ActionType.RESTOCK, output);
    }
	
	@Test
    public void testInvalidRestock() {
		String input = "B";
		ActionType output = App.determineType(input);
		assertNotEquals(ActionType.RESTOCK, output);
		
		input="b";
		output=App.determineType(input);
		assertNotEquals(ActionType.RESTOCK, output);
    }
	
	
	@Test
    public void testValidQuit() {
		String input = "Q";
		ActionType output = App.determineType(input);
		assertEquals(ActionType.QUIT, output);
		
		input="q";
		output=App.determineType(input);
		assertEquals(ActionType.QUIT, output);
    }
	
	@Test
    public void testValidSetWinner() {
		String input = "W 55";
		ActionType output = App.determineType(input);
		assertEquals(ActionType.SET_WINNER, output);
		
		input="w 55";
		output=App.determineType(input);
		assertEquals(ActionType.SET_WINNER, output);
    }
	
	@Test
    public void testValidPlaceBet() {
		String input = "5 25";
		ActionType output = App.determineType(input);
		assertEquals(ActionType.PLACE_BET, output);
    }
	
	@Test
	public void testValidPattern() {
		assertEquals(true,(Pattern.matches(ActionType.PLACE_BET.getPattern(), "1 55"))); //true
		assertEquals(true,(Pattern.matches(ActionType.SET_WINNER.getPattern(), "w 55"))); //true
		assertEquals(true,(Pattern.matches(ActionType.SET_WINNER.getPattern(), "W 55"))); //true
		assertEquals(true,(Pattern.matches(ActionType.QUIT.getPattern(), "Q")));
		assertEquals(true,(Pattern.matches(ActionType.QUIT.getPattern(), "q")));
		assertEquals(true,(Pattern.matches(ActionType.RESTOCK.getPattern(), "R")));
		assertEquals(true,(Pattern.matches(ActionType.RESTOCK.getPattern(), "r")));
	}
	
	@Test
	public void testInvalidPattern() {
		assertEquals(false,(Pattern.matches(ActionType.PLACE_BET.getPattern(), "b 55"))); //true
		assertEquals(false,(Pattern.matches(ActionType.SET_WINNER.getPattern(), "b 55"))); //true
		assertEquals(false,(Pattern.matches(ActionType.SET_WINNER.getPattern(), "b 55"))); //true
		assertEquals(false,(Pattern.matches(ActionType.QUIT.getPattern(), "a")));
		assertEquals(false,(Pattern.matches(ActionType.QUIT.getPattern(), "A")));
		assertEquals(false,(Pattern.matches(ActionType.RESTOCK.getPattern(), "z")));
		assertEquals(false,(Pattern.matches(ActionType.RESTOCK.getPattern(), "b 55")));
	}
	
	@Test
	public void testInvalidBet() {
		String invalidBetString="4 10.25"; //invalid
		//String invalidBetString="4a 10.25";
		
		String patternRegex = "(\\d+)(\\s*)(.*)"; //digit followed by space followed by anything
		String invalidAmountRegex2 = "\\D+";
		
		
		if(Pattern.matches(patternRegex,  invalidBetString)) {
			Pattern p = Pattern.compile(patternRegex);
			Matcher m = p.matcher(invalidBetString);
			
			if(m.matches()) {
				System.out.println(m.group(1));
				System.out.println(m.group(2));
				System.out.println(m.group(3));
			}
			
			String remainingString = m.group(3);
			if(!Pattern.matches(invalidAmountRegex2, remainingString)) {
				System.out.println("invalid bet amount!!!");
			}
					
		}
			
	}
}
