package com.ehsan.negotiation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ehsan.negotiation.model.Formula.ArgumentClass;
import com.ehsan.negotiation.util.MathTools;

public class Agent {

	public enum AgentType {BUYER, SELLER};

	protected int id;
	protected AgentType type;	
	protected List<Formula> knowledgeBase;
	protected List<Formula> history;
	protected List<Formula> snapShotKnowledgeBase;

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
		
		if (potentialOffer.size() == 0) return;
		
		System.out.println("---+++Analysing Offers");
		System.out.println("---+++Number of potential offers: " + potentialOffer.size());
		
		Random rnd = new Random();
		
		double total = 0;
		double debugTotal = 0;
		for (Formula formula: potentialOffer) {
			formula.setRiskOfFailure(rnd.nextDouble()*2);
			if (formula.getRiskOfFailure() < 0.5) 
				formula.setArgumentClass(ArgumentClass.A);
			else if (formula.getRiskOfFailure() < 1)
				formula.setArgumentClass(ArgumentClass.B);
			else 
				formula.setArgumentClass(ArgumentClass.C);
			total += (2-formula.getRiskOfFailure());
		}
			
		
		for (Formula formula: potentialOffer) {
			formula.setProbability((2-formula.getRiskOfFailure()) / total);
			debugTotal += formula.getProbability();
		}
		
		System.out.println("---+++Test Total Prob: " + debugTotal);
		
		double entropy = 0;
		for (Formula formula: potentialOffer) {
			entropy -= formula.getProbability() * MathTools.Log2(formula.getProbability());
		}
		
		double uncertainty = 0;
		if (MathTools.Log2(potentialOffer.size()) == 0) 
			uncertainty = 0;
		else 
			uncertainty = entropy / MathTools.Log2(potentialOffer.size());
		
		System.out.printf("---+++Uncertainty: %.2f \n", uncertainty);
	}
	
	public void saveKnowlegdeBaseSnapShot () {
		snapShotKnowledgeBase = new ArrayList<Formula>();
		for (Formula formula: knowledgeBase) {		
			Formula newFormula = new Formula(formula);
			snapShotKnowledgeBase.add(newFormula);
		}
	}
	
	public void restoreKnowlegdeBaseSnapShot () {
		knowledgeBase = new ArrayList<Formula>();
		for (Formula formula: snapShotKnowledgeBase) {			
			Formula newFormula = new Formula(formula);
			knowledgeBase.add(newFormula);
		}
	}
}
