package com.barclays.ivr.model;

import java.util.Map;

public class Details{
	public Map<String,String> Parameters;
	public ContactData ContactData;

	@Override
 	public String toString(){
		return 
			"Details{" + 
			"Parameters = '" + Parameters + '\'' +
			",ContactData = '" + ContactData + '\'' +
			"}";
		}
}
