package com.ehsan.negotiation.scenario;

import com.ehsan.negotiation.model.BuyerAgent;
import com.ehsan.negotiation.model.Formula;
import com.ehsan.negotiation.model.SellerAgent;

public class ScenarioOne implements Scenario {

	@Override
	public void runScenario() {
	
		BuyerAgent buyerAgent = new BuyerAgent();
		SellerAgent sellerAgent = new SellerAgent();
		
		Formula offer = null;
		int turn = 0;
		
		while (true) {
			
			System.out.println("");
			
			offer = buyerAgent.generateOffer(offer);
			
			// Report offer
			
			offer = sellerAgent.generateOffer(offer);
			
			// Report offer
			
			turn++;
			if (turn > 10) break;			
		}
		
	}

}
