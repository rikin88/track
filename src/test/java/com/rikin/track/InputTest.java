package com.rikin.track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

public class InputTest {
	
	@BeforeClass
	public static void init() {
		TrackMain app = new TrackMain();
	}
	
	@Test
    public void testValidRestock() {
		String input = "R";
		ActionType output = TrackMain.determineType(input);
		assertEquals(ActionType.RESTOCK, output);
		
		input="r";
		output=TrackMain.determineType(input);
		assertEquals(ActionType.RESTOCK, output);
    }
	
	@Test
    public void testInvalidRestock() {
		String input = "B";
		ActionType output = TrackMain.determineType(input);
		assertNotEquals(ActionType.RESTOCK, output);
		
		input="b";
		output=TrackMain.determineType(input);
		assertNotEquals(ActionType.RESTOCK, output);
    }
	
	
	@Test
    public void testValidQuit() {
		String input = "Q";
		ActionType output = TrackMain.determineType(input);
		assertEquals(ActionType.QUIT, output);
		
		input="q";
		output=TrackMain.determineType(input);
		assertEquals(ActionType.QUIT, output);
    }
	
	@Test
    public void testValidSetWinner() {
		String input = "W 55";
		ActionType output = TrackMain.determineType(input);
		assertEquals(ActionType.SET_WINNER, output);
		
		input="w 55";
		output=TrackMain.determineType(input);
		assertEquals(ActionType.SET_WINNER, output);
    }
	
	@Test
    public void testValidPlaceBet() {
		String input = "5 25";
		ActionType output = TrackMain.determineType(input);
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
		String text = "4 10.25";
		ActionType actionType = TrackMain.determineType(text);
		assertEquals(ActionType.INVALID_BET_AMOUNT, actionType);		
	}
	
	
	/*public void testStubs() {
		
		List<String> inputs = TrackMain.setupStubOutput();
		System.out.println("start test");
		inputs.forEach(input -> System.out.println(TrackMain.determineType(input).toString()));
	}*/
	

}
