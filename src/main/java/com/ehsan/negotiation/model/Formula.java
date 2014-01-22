package com.ehsan.negotiation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Formula {
	double price;
	List<Map<String, Object>> properties; 
	
	public enum ArgumentClass {A, B, C};
	
	String name;
	String property1;
	String property2;	
	
	double max;
	double min;
	double threshold;

	double preference;
	
	//helper
	double probability;
	double riskOfFailure;
	ArgumentClass argumentClass;
	boolean accepted;
	
	public Formula () {
		this.accepted = false;
	}
	
	public Formula (String name, String property1, double price, double preference, double threshold) {
		this.name = name;
		this.property1 = property1;
		this.price = price;
		this.preference = preference;
		this.threshold = threshold;
		this.accepted = false;
	}
		
	public void addProperty (Map<String, Object> property) {
		if (property == null)
			return;
		if (this.properties == null)
			this.properties = new ArrayList<Map<String,Object>>();
		if (!this.properties.contains(property))
			this.properties.add(property);
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<Map<String, Object>> getProperties() {
		return properties;
	}
	public void setProperties(List<Map<String, Object>> properties) {
		this.properties = properties;
	}		
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}

	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}	
    public double getPreference() {
		return preference;
	}
	public void setPreference(double preference) {
		this.preference = preference;
	}	
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public double getRiskOfFailure() {
		return riskOfFailure;
	}
	public void setRiskOfFailure(double riskOfFailure) {
		this.riskOfFailure = riskOfFailure;
	}
	public ArgumentClass getArgumentClass() {
		return argumentClass;
	}
	public void setArgumentClass(ArgumentClass argumentClass) {
		this.argumentClass = argumentClass;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Formula)) return false;
		Formula otherFormula = (Formula)other;		
		boolean equals = otherFormula.name.equals(this.name) && otherFormula.property1.equals(this.property1);
		if (property2 != null && otherFormula.getProperty2() != null) {
			equals &= otherFormula.property2.equals(this.property2);
		}
		return equals;
	}

	@Override
	public int hashCode()
	{
		int hash = 7 * name.hashCode() + 3;
		hash = 3 * hash + property1.hashCode();		
		return hash;
	}
	
	@Override
	public String toString () {
		String result = String.format("%s, %s : Price%.0f, [pref: %.2f], th: %.0f --- RiskOfFail: %.2f, Prob: %.4f, Class: %s", getName(), getProperty1(), getPrice(), getPreference(), getThreshold(), getRiskOfFailure(), getProbability(), getArgumentClass());
		return result;
	}
	
}
