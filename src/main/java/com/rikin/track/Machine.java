package com.rikin.track;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rikin.track.domain.Horse;


public class Machine {
	private Map<Integer,Integer> availableFunds;
	private HorseInformation horseInformation;
	
	public static Machine setupInitialData() {
		Machine machine = new Machine();
		
		machine.setAvailableFunds(setupInitialFunds());
		machine.setHorseInformation(setupInitialHorseInformation());
		
		return machine;
	}
	
	public static Map<Integer,Integer> setupInitialFunds() {
		Map<Integer, Integer>initalFunds = new LinkedHashMap<Integer,Integer>();
		initalFunds.put(1, 10);
		initalFunds.put(5, 10);
		initalFunds.put(10, 10);
		initalFunds.put(20, 10);
		initalFunds.put(100, 10);
		
		return initalFunds;
	}
	
	public static HorseInformation setupInitialHorseInformation() {
		
		HorseInformation horseInfo = new HorseInformation ();
		Map<Integer, Horse> horses = new LinkedHashMap<Integer,Horse>();
		horseInfo.setHorses(horses);
		horseInfo.getHorses().put(1, new Horse("That Darn Gray Cat", 5, true));
		horseInfo.getHorses().put(2, new Horse("Fort Utopia", 10, false));
		horseInfo.getHorses().put(3, new Horse("Count Sheep", 9, false));
		horseInfo.getHorses().put(4, new Horse("Ms Traitour", 4, false));
		horseInfo.getHorses().put(5, new Horse("Real Princess",3, false));
		horseInfo.getHorses().put(6, new Horse("Pa Kettle", 5, false));
		horseInfo.getHorses().put(7, new Horse("Gin Stinger", 6, false));
		
		return horseInfo;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nInventory ----\n");
		sb.append(printAvailableFunds());
		sb.append("\nHorses ---\n");
		sb.append(printHorseInventory());
		sb.append("------------\n\n");
		return sb.toString();
	}
	
	public void printAll() {
		System.out.println("***************");
		System.out.println(printAvailableFunds());
		System.out.println(printHorseInventory());
		System.out.println("***************");
	}
	public String printAvailableFunds() {
		
		StringBuilder sb = new StringBuilder();
		this.availableFunds.forEach((k,v) -> sb.append("$" + k + ", " + v + "\n"));
		return sb.toString();
	}
	
	public String printHorseInventory() {
		StringBuilder sb = new StringBuilder();
		this.horseInformation.getHorses().forEach((k,v) -> sb.append(k + ", " + v.toString() + "\n"));
		return sb.toString();
	}
	
	public void restockInventory() {
		this.availableFunds = setupInitialFunds();
		//System.out.println("Restock Inventory successfull");
	}
	
	public boolean setWinningHorseNumber(int horseNumber)  {
		if(isValidHorseNumber(horseNumber)) {
			this.horseInformation.getHorses().forEach((k,v) -> v.setDidWin(false));
			this.horseInformation.getHorses().get(horseNumber).setDidWin(true);
			System.out.println("Setting winning horse number to: " + horseNumber);
			return true;
		}
		return false;
	}
	
	public int findWinningHorseNumber() {

		Map<Integer, Horse> horses = getHorseInformation().getHorses();
		//Map<Integer, Horse> winningHorse =
		List<Integer> ids = 
				horses.entrySet().stream()
					.filter(e -> e.getValue().isDidWin() == true)
					.map(e -> e.getKey())
					.collect(Collectors.toList());

		return ids.get(0);
	}
	
	public boolean isValidHorseNumber(int horseNumber) {
		if(horseNumber<0 || horseNumber > horseInformation.getHorses().size()) {
			//System.out.println("Invalid Horse Number: " +  horseNumber);
			return false;
		}
		return true;
	}
	
	public int placeHorseBet(int horseNumber, int betAmount) {
		int payout = 0;
		if(findWinningHorseNumber()==horseNumber) {
			Horse h = horseInformation.getHorses().get(horseNumber);
			int odds = h.getHorseOdds();
			payout = betAmount * odds;
		}
		return payout;
	}

	public Map<Integer, Integer> deductCashFromInventory(int betAmount) throws Exception {
		//imperativeWay
		List<Integer> dollarQuantities = getDollarQuantities(betAmount);
		int index = 0;
		for(int i: availableFunds.keySet()) {
			int qty = availableFunds.get(i) - dollarQuantities.get(index++);
			if (qty>=0) {
				availableFunds.put(i, qty);
			}
			else {
				throw new Exception("InsufficientFunds");
			}
		}
		
		System.out.println(printAvailableFunds());
		return availableFunds;
	}
	
	
	public List<Integer> getDollarQuantities(int dollarAmount) {
		
		List<Integer> quantity= new ArrayList<Integer>();
		
		int remainingBalance = dollarAmount; //275
		int hundreds = dollarAmount / 100; //2
		remainingBalance = remainingBalance % 100; //75
		int twenties = remainingBalance/20; //3
		remainingBalance = remainingBalance %20; //15
		int tens = remainingBalance/10; //1
		remainingBalance = remainingBalance % 10; // 5
		int fives = remainingBalance/5; //1
		remainingBalance = remainingBalance %5;
		int ones = remainingBalance;
		
		quantity.add(ones);
		quantity.add(fives);
		quantity.add(tens);
		quantity.add(twenties);
		quantity.add(hundreds);
		
		return quantity;
	}
	
	
	public boolean isValidBetAmount(int betAmount) {
		//betAmount.
		return false;
	}
	
	public Map<Integer, Integer> getAvailableFunds() {
		return availableFunds;
	}

	public void setAvailableFunds(Map<Integer,Integer> availableFunds) {
		this.availableFunds = availableFunds;
	}

	public HorseInformation getHorseInformation() {
		return horseInformation;
	}

	public void setHorseInformation(HorseInformation horseInformation) {
		this.horseInformation = horseInformation;
	}
	
}
