package com.ehsan.negotiation.model;

import java.util.ArrayList;
import java.util.List;

public class Agent {
	
	private List<Formula> knowledgeBase;

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
	
}
