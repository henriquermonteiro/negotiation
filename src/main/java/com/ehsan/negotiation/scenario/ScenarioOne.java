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

		sellerAgent.saveKnowlegdeBaseSnapShot();
		buyerAgent.saveKnowlegdeBaseSnapShot();



		//		buyerAgent.addFormula(new Formula("*", "14", 500, 0.7));
		//		buyerAgent.addFormula(new Formula("HP", "15", 600, 0.6));		
		//		buyerAgent.addFormula(new Formula("Toshiba", "15", 550, 0.5));	
		//				
		//		sellerAgent.addFormula(new Formula("HP", "15", 800, 0.8));		
		//		sellerAgent.addFormula(new Formula("Toshiba", "15", 850, 0.7));
		//		sellerAgent.addFormula(new Formula("Sony", "14", 800, 0.6));	

	}

	@Override
	public void runScenario() {

		

		int s1=0,s2=0;
		
		Formula finalOffer = null;
		//while (true) {
			initialize ();
			
			boolean exitCondition = false;
			System.out.println("");
			System.out.println("*************************************************");
			System.out.println("***************** Scenario One ******************");
			System.out.println("*************************************************");
			buyerAgent.reportKnowledgeBase();
			sellerAgent.reportKnowledgeBase();	
			finalOffer = negotiate(1);
			//exitCondition = (finalOffer != null);
			if (finalOffer != null) s1++;


			System.out.println("");
			System.out.println("*************************************************");
			System.out.println("***************** Scenario Two ******************");
			System.out.println("*************************************************");
			sellerAgent.restoreKnowlegdeBaseSnapShot();
			buyerAgent.restoreKnowlegdeBaseSnapShot();
			buyerAgent.reportKnowledgeBase();
			sellerAgent.reportKnowledgeBase();		
			finalOffer = negotiate(2);
			//exitCondition &= (finalOffer != null);
			if (finalOffer != null) s2++;
			
			System.out.println("S1: " + s1 + ", S2: " + s2);
			
			//if (exitCondition) break;
		//}
	}

	private Formula negotiate(int snum) {
		Formula offer = null, prevOffer= null;
		int step = 0;
		Formula finalOffer = null;

		System.out.println("");
		System.out.println("************* Negotiation Starts *************");
		while (true) {

			step++;			
			//System.out.println("");

			System.out.println("Step: " + step);
			System.out.println("Buyer Agent: ");
			prevOffer = offer;
			offer = buyerAgent.generateOffer(offer, snum);

			if (offer == null) {
				System.out.println("*****No Agreement reached******");
				break;
			};
			if (offer.isAccepted()) {
				System.out.println("*****Agreement reached******");
				System.out.println("===Chosen Offer: " + prevOffer);
				finalOffer = prevOffer;
				break;
			}
			System.out.println("===Chosen Offer: " + offer);	


			step++;
			System.out.println("Step: " + step);
			System.out.println("Seller Agent: ");
			prevOffer = offer;
			offer = sellerAgent.generateOffer(offer, snum);

			if (offer == null) {
				System.out.println("*****No Agreement reached******");
				break;
			};	
			if (offer.isAccepted()) {
				System.out.println("*****Agreement reached******");
				System.out.println("===Chosen Offer: " + prevOffer);
				finalOffer = prevOffer;
				break;
			}					
			System.out.println("===Chosen Offer: " + offer);

			if (step > 100) {
				System.out.println("*****No Agreement reached after 100 steps******");
			};			
		}

		System.out.println("Took Step: " + step);
		return finalOffer;
	}

}
