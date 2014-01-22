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
		int k = rnd.nextInt(16)+7;

		for (int i = 0; i < k; i++) {
			double price = 300 + rnd.nextInt(6)*50;
			buyerAgent.addKnowledgeBaseFormula(new Formula(
					Constant.NAME_LIST.get(rnd.nextInt(Constant.NAME_LIST.size())),
					Constant.PROPERTY1_LIST.get(rnd.nextInt(Constant.PROPERTY1_LIST.size())), 
					price,
					rnd.nextDouble(),
					price + (rnd.nextInt(8))*50));
		}

		k = rnd.nextInt(16)+7;
		for (int i = 0; i < k; i++) {
			double price = 600 + rnd.nextInt(6)*50;
			sellerAgent.addKnowledgeBaseFormula(new Formula(
					Constant.NAME_LIST.get(rnd.nextInt(Constant.NAME_LIST.size())),
					Constant.PROPERTY1_LIST.get(rnd.nextInt(Constant.PROPERTY1_LIST.size())), 
					price,
					rnd.nextDouble(),
					price - (rnd.nextInt(8))*50));
		}

		sellerAgent.normalizeKnowledgeBase();
		buyerAgent.normalizeKnowledgeBase();

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
		System.out.println("************* Negotiation Starts *************");

		while (true) {

			step++;			
			//System.out.println("");

			System.out.println("Step: " + step);
			System.out.println("Buyer Agent: ");
			offer = buyerAgent.generateOffer(offer);

			System.out.println("===Chosen Offer: " + offer);			
			if (offer == null) {
				System.out.println("*****No Agreement reached******");
				break;
			};				

			//Reached aggreement?
			if (offer.isAccepted()) {
				System.out.println("*****Agreement reached******");
				break;
			}

			step++;
			System.out.println("Step: " + step);
			System.out.println("Seller Agent: ");
			offer = sellerAgent.generateOffer(offer);

			System.out.println("===Chosen Offer: " + offer);			
			if (offer == null) {
				System.out.println("*****No Agreement reached******");
				break;
			};	

			//Reached aggreement?
			if (offer.isAccepted()) {
				System.out.println("*****Agreement reached******");
				break;
			}

			if (step > 100) {
				System.out.println("*****No Agreement reached after 100 steps******");
			};			
		}

		System.out.println("Took Step: " + step);

	}

}
