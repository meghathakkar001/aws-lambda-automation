package com.bank.ivr.model;

public class CustomerEndpoint{
	public String Type;
	public String Address;

	@Override
 	public String toString(){
		return 
			"CustomerEndpoint{" + 
			"Type = '" + Type + '\'' +
			",Address = '" + Address + '\'' +
			"}";
		}
}
