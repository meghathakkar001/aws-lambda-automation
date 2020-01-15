package com.barclays.ivr;

import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lexruntime.model.*;
import com.barclays.ivr.model.AWSConnectEvent;
import com.barclays.ivr.model.AWSConnectResponse;

import java.util.HashMap;
import java.util.Map;

public class LexPutSessionMain {

    public AWSConnectResponse putSessionHandler(AWSConnectEvent request, Context context) {
        AmazonLexRuntime client = AmazonLexRuntimeClientBuilder.defaultClient();
        System.out.println(request);
        String intentName=request.Details.Parameters.get("intent");
        System.out.println(request);
        Map<String,String> sessionAttributes= new HashMap<>();
        sessionAttributes.put("rfc",intentName);
        if(intentName!=null){
            //DialogAction dialogAction = new DialogAction().withIntentName("Identification").withType(DialogActionType.ElicitSlot).withMessage("Please provide your slot name").withMessageFormat(MessageFormatType.PlainText).withSlotToElicit("MerchantID");
            DialogAction dialogAction = new DialogAction().withIntentName(intentName).withType(DialogActionType.Delegate);
            PutSessionRequest putSessionRequest= new PutSessionRequest().withBotName("ContactCenterBot").withBotAlias("$LATEST").withUserId(request.Details.ContactData.ContactId).withDialogAction(dialogAction).withSessionAttributes(sessionAttributes);
            PutSessionResult result=client.putSession(putSessionRequest);
            System.out.println("Success with dialogAction: "+result);
            System.out.println("Request: "+putSessionRequest);
            System.out.println("UserId: "+putSessionRequest.getUserId());
        }

        AWSConnectResponse response= new AWSConnectResponse();
        response.statusCode=200;

        return response;
    }
}
