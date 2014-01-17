package com.ehsan.negotiation.model;


public class SellerAgent extends Agent{
	
	public SellerAgent () {
		super();
		type = AgentType.SELLER;		
	}
	
	@Override
	public Formula generateOffer (Formula offer) {
		return null;
	}
	
	@Override
	public Formula getPreferedOffer () {
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
