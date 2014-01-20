package com.ehsan.negotiation.model;

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
	
}
