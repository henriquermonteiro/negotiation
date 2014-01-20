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
		if (offer == null) {
			return getPreferedOffer(offer);
		}		
		return getPreferedOffer(offer);
	}
	
	@Override
	public Formula getPreferedOffer (Formula offer) {
		List<Formula> potentialOffer = getPotentialOffer(offer);
		
		Collections.sort(potentialOffer, new Comparator<Formula>(){
		    public int compare(Formula s1, Formula s2) {
		        return Double.compare(s2.getPreference(), s1.getPreference());
		    }		    
		});
		
		for (Formula formula : potentialOffer) {
			System.out.println("---Potiencial Offer: " + formula);
		}
		
		if (potentialOffer.size() > 0) return potentialOffer.get(0);
		return null;
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
		
		boolean nameMatch = false;
		boolean property1Match = false;

		for (Formula formula : knowledgeBase) {
			if (offer.getName() == null || offer.getName().isEmpty() || offer.getName().equals("*") || offer.getName().equals(formula.getName())) {
				nameMatch = true;
			}
			
			if (offer.getProperty1() == null || offer.getProperty1().isEmpty() || offer.getProperty1().equals("*") || offer.getProperty1().equals(formula.getProperty1())) {
				property1Match = true;
			}
			
			if (nameMatch && property1Match) {
				potentialOffer.add(formula);
				continue;
			}					
		}
		return potentialOffer;
	}
	
}
