package com.ehsan.negotiation.model;

import java.util.ArrayList;
import java.util.List;

import com.ehsan.negotiation.util.Constant;

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
	public void addKnowledgeBaseFormula (Formula formula) {
		if (formula == null)
			return;
		if (this.knowledgeBase == null)
			this.knowledgeBase = new ArrayList<Formula>();
		if (!this.knowledgeBase.contains(formula))
			this.knowledgeBase.add(formula);
	}
	public void addHistoryFormula (Formula formula) {
		if (formula == null)
			return;
		if (this.history == null)
			this.history = new ArrayList<Formula>();
		if (!this.history.contains(formula))
			this.history.add(formula);
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
	
	public void normalizeKnowledgeBase() {
		for (Formula formula: knowledgeBase) {
			if (formula.getName().equals("*")) {
				formula.setPreference(formula.getPreference() + 0.2);
			}
			if (formula.getProperty1().equals("*")) {
				formula.setPreference(formula.getPreference() + 0.02);
			}
		}
		
	}
	
	public void analysePotentialOffers(List<Formula> potentialOffer) {
		
		System.out.println("---+++Analysing Offers");
		System.out.println("---+++Number of potential offers: " + potentialOffer.size());
		
	}
}
