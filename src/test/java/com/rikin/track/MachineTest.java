package com.rikin.track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rikin.track.domain.Horse;

public class MachineTest {
	static Machine machine;
	
	@BeforeClass
	public static void init() {
		machine = Machine.setupInitialData();
	}
	
	@Test
	public void testSetupInitialData() {
		Map<Integer, Integer> initialFunds = machine.getAvailableFunds();
		HorseInformation horseInformation = machine.getHorseInformation();
		
		assertEquals(5, initialFunds.size());
		assertEquals(7, horseInformation.getHorses().size());
	}
	
	@Test
	public void testRestock() {
		machine.restockInventory();
		assertEquals(5, machine.getAvailableFunds().size());
	}
	
	@Test
	public void testSettingWinningHorseNumber() {
		assertTrue(machine.setWinningHorseNumber(5));
		Map<Integer, Horse> horses = machine.getHorseInformation().getHorses();
		Map<Integer, Horse> winningHorse = horses.entrySet().stream()
			.filter(e -> e.getValue().isDidWin() == true)
			.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
			
		assertEquals(1, winningHorse.size());
		assertEquals("Real Princess", winningHorse.get(5).getHorseName());
	}
	
	@Test
	public void testInvalidSettingWinningNumber() {
		assertFalse(machine.setWinningHorseNumber(10));
	}
	
	@Test
	public void findWinningHorseNumber() {
		machine.setWinningHorseNumber(1);
		assertEquals(1, machine.findWinningHorseNumber());
		
		machine.setWinningHorseNumber(5);
		assertEquals(5, machine.findWinningHorseNumber());
	}
	
	@Test
	public void testPlacingValidBet() {
		int payout = 0;
		int betAmount = 50;
		machine.setWinningHorseNumber(1);
		int winningHorseNumber = machine.findWinningHorseNumber();
		Horse winningHorse = machine.getHorseInformation().getHorses().get(winningHorseNumber);
		System.out.println("odds: " + winningHorse.getHorseOdds());
		System.out.println("betAmount: " + betAmount);
		
		betAmount = machine.placeHorseBet(1,50);
		assertEquals(250, betAmount);
	}
	
	@Test
	public void deductCashFromInventory() {
		try {
			int total = (100*10) + (20*10) + (10*10) + (5*10) + (1*10);
			System.out.println(total);
			machine.deductCashFromInventory(1067);
		} catch (Exception e) {
			System.out.println("Insufficient Funds!");
		}
		
	}
	
	@Test
	public void testDollarQuantities() {
		int amount = 277;
		List<Integer> expectedQuantities = new ArrayList<Integer>(Arrays.asList(2,1,1,3,2));
		List<Integer> actualQuantities = machine.getDollarQuantities(amount);
		assertEquals(expectedQuantities, actualQuantities);
	}
}
