package com.rikin.track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	public void testSettingWinningNumber() {
		assertTrue(machine.setWinningHorseNumber(5));
		Map<Integer, Horse> horses = machine.getHorseInformation().getHorses();
		ArrayList<Boolean> didWinList = new ArrayList<Boolean>();	
		horses.forEach((k,v) -> didWinList.add(v.isDidWin()));
		assertEquals(1, Collections.frequency(didWinList, true));	
		
		Horse horse = machine.getHorseInformation().getHorses().get(5);
		assertEquals(true, horse.isDidWin());
		//System.out.println(machine.printHorseInventory());
	}
	
	@Test
	public void testInvalidSettingWinningNumber() {
		assertFalse(machine.setWinningHorseNumber(10));
		
	}
	
	@Test
	public void testPlacingValidBet() {
		assertTrue(machine.isValidHorseNumber(1));
	}
	
	@Test
	public void determineWinningHorseNumber() {
	//	List<Horse> = machine.getHorseInformation().getHorses().values();
	}
}
