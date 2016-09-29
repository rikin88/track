package com.rikin.track;

import java.util.List;
import java.util.Map;

import com.rikin.track.domain.Horse;

public class HorseInformation {
	private Map<Integer, Horse> horses;

	public Map<Integer, Horse> getHorses() {
		return horses;
	}

	public void setHorses(Map<Integer, Horse> horses) {
		this.horses = horses;
	}
	
	public void determineWinningHorse() {
		/*
		horses.entrySet().stream()
			.filter(map -> horses.get)
		//Map -> Stream -> Filter -> String
        result = HOSTING.entrySet().stream()
                .filter(map -> "aws.amazon.com".equals(map.getValue()))
                .map(map -> map.getValue())
                .collect(Collectors.joining());*/
		/*		
		List<Integer> transactionsIds = 
			    transactions.stream()
			                .filter(t -> t.getType() == Transaction.GROCERY)
			                .sorted(comparing(Transaction::getValue).reversed())
			                .map(Transaction::getId)
			                .collect(toList());		*/
	}
}
