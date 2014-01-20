package com.ehsan.negotiation.model;

import java.util.ArrayList;
import java.util.List;

public class Agent {

	public enum AgentType {BUYER, SELLER};

	protected int id;
	protected AgentType type;	
	protected List<Formula> knowledgeBase;
	protected List<Formula> history;				

	public Agent () {
		knowledgeBase = new ArrayList<Formula>();
		history = new ArrayList<Formula>();		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public AgentType getType() {
		return type;
	}
	public void setType(AgentType type) {
		this.type = type;
	}
	public List<Formula> getKnowledgeBase() {
		return knowledgeBase;
	}
	public void setKnowledgeBase(List<Formula> knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}
	public void addFormula (Formula formula) {
		if (formula == null)
			return;
		if (this.knowledgeBase == null)
			this.knowledgeBase = new ArrayList<Formula>();
		if (!this.knowledgeBase.contains(formula))
			this.knowledgeBase.add(formula);
	}


	public Formula generateOffer (Formula offer) {
		return null;
	}

	public Formula getPreferedOffer (Formula offer) {
		return null;	
	}

	public Formula getPreferedOfferOfName (String name) {
		return null;	
	}

	public Formula getPreferedOfferOfProperty(String property) {
		return null;	
	}

	public Formula getPreferedOfferOfNameAndProperty(String name, String property) {
		return null;	
	}

	public List<Formula> getPotentialOffer(Formula offer) {
		List<Formula> potentialOffer = new ArrayList<Formula>(knowledgeBase);					
		return potentialOffer;
	}
	
	public void reportKnowledgeBase () {
		System.out.println("Agent Id: " + id + ", Type: " + type);
		for (Formula formula: knowledgeBase) {			
			System.out.println("---KB: " + formula);
		}
	}
}
