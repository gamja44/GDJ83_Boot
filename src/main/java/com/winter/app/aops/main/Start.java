package com.winter.app.aops.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winter.app.aops.pays.Card;
import com.winter.app.aops.transfers.Transfer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class Start {
	
	@Autowired
	private Transfer transfer;
	@Autowired
	private Card card;
	
	public void go() {
		//card.cardCheck();
		transfer.takeBus(50);
		//card.cardCheck();
		
		//card.cardCheck();
		transfer.takeSubway(15L, "winter");
		//card.cardCheck();
		
		transfer.walk();
	}
	
}
