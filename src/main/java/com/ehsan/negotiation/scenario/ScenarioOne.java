package com.ehsan.negotiation.scenario;

import com.ehsan.negotiation.model.BuyerAgent;
import com.ehsan.negotiation.model.Formula;
import com.ehsan.negotiation.model.SellerAgent;

public class ScenarioOne implements Scenario {

	BuyerAgent buyerAgent = new BuyerAgent();
	SellerAgent sellerAgent = new SellerAgent();
	
	private void initialize() {
		
		buyerAgent = new BuyerAgent();
		sellerAgent = new SellerAgent();				
		
		buyerAgent.addFormula(new Formula("*", "14", 500, 0.7));
		buyerAgent.addFormula(new Formula("HP", "15", 600, 0.6));		
		buyerAgent.addFormula(new Formula("Toshiba", "15", 550, 0.5));	
				
		sellerAgent.addFormula(new Formula("HP", "15", 800, 0.8));		
		sellerAgent.addFormula(new Formula("Toshiba", "15", 850, 0.7));
		sellerAgent.addFormula(new Formula("Sony", "14", 800, 0.6));
		
		buyerAgent.reportKnowledgeBase();
		sellerAgent.reportKnowledgeBase();
	}

	@Override
	public void runScenario() {
		
		initialize ();
		
		Formula offer = null;
		int turn = 0;
		
		while (true) {
			
			//System.out.println("");
			
			System.out.println("Buyer Agent: ");
			offer = buyerAgent.generateOffer(offer);
			
			System.out.println("---Chosen Offer: " + offer);
			
			// Report offer
			
			System.out.println("Seller Agent: ");
			offer = sellerAgent.generateOffer(offer);
			
			System.out.println("---Chosen Offer: " + offer);
			
			// Report offer
			
			turn++;
			if (turn > 10) break;			
		}
		
	}
	
}
