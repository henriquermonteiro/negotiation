package com.ehsan.negotiation.util;

import java.util.ArrayList;

public class Constant {
	public static final int MaxPrice = 1000;
	
	@SuppressWarnings("serial")
	public static ArrayList<String> NAME_LIST = new ArrayList<String>() {{
	    add("*");
	    add("HP");
	    add("Sony");
	    add("Toshiba");
	}};
	
	@SuppressWarnings("serial")
	public static ArrayList<String> PROPERTY1_LIST = new ArrayList<String>() {{
		add("*");
	    add("14");
	    add("15");
	    add("16");
	    add("17");	    
	}};
	
}
