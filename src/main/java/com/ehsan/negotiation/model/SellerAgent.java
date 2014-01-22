package com.ehsan.negotiation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SellerAgent extends Agent{

	public SellerAgent () {
		super();
		type = AgentType.SELLER;		
	}

	@Override
	public Formula generateOffer (Formula offer) {
		Formula responseOffer = getPreferedOffer(offer);	
		if (responseOffer != null) {
			addHistoryFormula(responseOffer);
		}		
		if (offer != null && offer.equals(responseOffer) && offer.getPrice() >= responseOffer.getThreshold())
			responseOffer.setAccepted(true);
		return responseOffer;
	}

	@Override
	public Formula getPreferedOffer (Formula offer) {
		Formula resultOffer =  null;	
		List<Formula> potentialOffer = getPotentialOffer(offer);

		analysePotentialOffers (potentialOffer);
		
		Collections.sort(potentialOffer, new Comparator<Formula>(){
			public int compare(Formula s1, Formula s2) {
				//return Double.compare(s2.getPreference(), s1.getPreference());
				return Double.compare(s2.getProbability(), s1.getProbability());
			}		    
		});
					
		for (Formula formula : potentialOffer) {
			System.out.println("---Potiencial Offer: " + formula);
		}

		Formula responseOffer =  null;		
		if (potentialOffer.size() > 0) {
			responseOffer = potentialOffer.get(0);		
			if (history.contains(responseOffer)) {
				responseOffer.setPrice(responseOffer.getPrice() - 50);
			}			
			if (responseOffer.getPrice() >= responseOffer.getThreshold()) {
				resultOffer = responseOffer;
				addHistoryFormula(responseOffer);
			}			
		}
		
		return resultOffer;
	}

	@Override
	public Formula getPreferedOfferOfName (String name) {
		return null;	
	}

	@Override
	public Formula getPreferedOfferOfProperty(String property) {
		return null;	
	}

	@Override
	public Formula getPreferedOfferOfNameAndProperty(String name, String property) {
		return null;	
	}

	@Override
	public List<Formula> getPotentialOffer(Formula offer) {
		List<Formula> potentialOffer = new ArrayList<Formula>();
		if (offer == null) {
			potentialOffer = new ArrayList<Formula>(knowledgeBase);
			return potentialOffer;
		}

		for (Formula formula : knowledgeBase) {
			
			boolean nameMatch = false;
			boolean property1Match = false;
			
			if (offer.getName() == null || offer.getName().isEmpty() || offer.getName().equals("*") || offer.getName().equals(formula.getName())) {
				nameMatch = true;
			}

			if (offer.getProperty1() == null || offer.getProperty1().isEmpty() || offer.getProperty1().equals("*") || offer.getProperty1().equals(formula.getProperty1())) {
				property1Match = true;
			}

			if (nameMatch && property1Match) {
			//	if (offer.getPrice() >= formula.getThreshold()) {
					potentialOffer.add(formula);
					continue;
			//	}
			}					
		}
		return potentialOffer;
	}

}
