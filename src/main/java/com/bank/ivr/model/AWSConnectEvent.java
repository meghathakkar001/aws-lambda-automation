package com.bank.ivr.model;

public class AWSConnectEvent {
	public Details Details;
	public String Name;


	@Override
 	public String toString(){
		return 
			"AWSConnectEvent{" +
			"details = '" + Details + '\'' +
			",name = '" + Name + '\'' +
			"}";
		}
}
