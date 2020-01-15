package com.barclays.ivr.model;

public class SystemEndpoint{
	public String Type;
	public String Address;

	@Override
 	public String toString(){
		return 
			"SystemEndpoint{" + 
			"Type = '" + Type + '\'' +
			",Address = '" + Address + '\'' +
			"}";
		}
}
