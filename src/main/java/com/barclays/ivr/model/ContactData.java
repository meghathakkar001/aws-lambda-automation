package com.barclays.ivr.model;

import java.util.Map;

public class ContactData{
	public String InitialContactId;
	public CustomerEndpoint CustomerEndpoint;
	public String InitiationMethod;
	public MediaStreams MediaStreams;
	public String Channel;
	public String InstanceARN;
	public SystemEndpoint SystemEndpoint;
	public Map<String,String> Attributes;
	public String PreviousContactId;
	public String ContactId;
	public Object Queue;

	@Override
 	public String toString(){
		return 
			"ContactData{" + 
			"InitialContactId = '" + InitialContactId + '\'' + 
			",CustomerEndpoint = '" + CustomerEndpoint + '\'' + 
			",InitiationMethod = '" + InitiationMethod + '\'' + 
			",MediaStreams = '" + MediaStreams + '\'' + 
			",Channel = '" + Channel + '\'' + 
			",InstanceARN = '" + InstanceARN + '\'' + 
			",SystemEndpoint = '" + SystemEndpoint + '\'' + 
			",Attributes = '" + Attributes + '\'' + 
			",PreviousContactId = '" + PreviousContactId + '\'' + 
			",ContactId = '" + ContactId + '\'' + 
			",Queue = '" + Queue + '\'' + 
			"}";
		}
}
