package com.ehsan.negotiation.scenario;

import java.util.Random;

import com.ehsan.negotiation.model.BuyerAgent;
import com.ehsan.negotiation.model.Formula;
import com.ehsan.negotiation.model.SellerAgent;
import com.ehsan.negotiation.util.Constant;

public class ScenarioOne implements Scenario {

	BuyerAgent buyerAgent = new BuyerAgent();
	SellerAgent sellerAgent = new SellerAgent();
	
	private void initialize() {
		
		buyerAgent = new BuyerAgent();
		sellerAgent = new SellerAgent();			
		
		Random rnd = new Random();
		int k = rnd.nextInt(8)+4;
		
		for (int i = 0; i < k; i++) {
			buyerAgent.addFormula(new Formula(
					Constant.NAME_LIST.get(rnd.nextInt(Constant.NAME_LIST.size())),
					Constant.PROPERTY1_LIST.get(rnd.nextInt(Constant.PROPERTY1_LIST.size())), 
					300 + rnd.nextInt(6)*50, rnd.nextDouble()));
		}
		
		k = rnd.nextInt(8)+4;
		for (int i = 0; i < k; i++) {
			sellerAgent.addFormula(new Formula(
					Constant.NAME_LIST.get(rnd.nextInt(Constant.NAME_LIST.size())),
					Constant.PROPERTY1_LIST.get(rnd.nextInt(Constant.PROPERTY1_LIST.size())), 
					600 + rnd.nextInt(6)*50, rnd.nextDouble()));
		}
		
//		buyerAgent.addFormula(new Formula("*", "14", 500, 0.7));
//		buyerAgent.addFormula(new Formula("HP", "15", 600, 0.6));		
//		buyerAgent.addFormula(new Formula("Toshiba", "15", 550, 0.5));	
//				
//		sellerAgent.addFormula(new Formula("HP", "15", 800, 0.8));		
//		sellerAgent.addFormula(new Formula("Toshiba", "15", 850, 0.7));
//		sellerAgent.addFormula(new Formula("Sony", "14", 800, 0.6));
		
		buyerAgent.reportKnowledgeBase();
		sellerAgent.reportKnowledgeBase();
	}

	@Override
	public void runScenario() {
		
		initialize ();
		
		Formula offer = null;
		int step = 0;
		
		System.out.println("");
		
		while (true) {
			
			step++;			
			//System.out.println("");
			
			System.out.println("Step: " + step);
			System.out.printf("Buyer Agent: ");
			offer = buyerAgent.generateOffer(offer);
			
			System.out.println("---Chosen Offer: " + offer);			
			if (offer == null) break;			
			
			step++;
			System.out.println("Step: " + step);
			System.out.println("Seller Agent: ");
			offer = sellerAgent.generateOffer(offer);
			
			System.out.println("---Chosen Offer: " + offer);			
			if (offer == null) break;			
			
			if (step > 20) break;			
		}
		
		System.out.println("*******************************");
		System.out.println("Took Step: " + step);
		
	}
	
}
