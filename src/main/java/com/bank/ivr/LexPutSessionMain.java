package com.bank.ivr;

import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lexruntime.model.*;
import com.bank.ivr.model.AWSConnectEvent;
import com.bank.ivr.model.AWSConnectResponse;
import com.bank.ivr.model.IntentDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LexPutSessionMain {
    public AWSConnectResponse putSessionHandler(AWSConnectEvent request, Context context) {
        Map<String, IntentDetails> dnisIntentMap = getDnisIntentMap();

        AmazonLexRuntime client = AmazonLexRuntimeClientBuilder.defaultClient();
        System.out.println(request);
        IntentDetails intentDetails = dnisIntentMap.get(request.Details.ContactData.SystemEndpoint.Address);
        PutSessionRequest putSessionRequest = new PutSessionRequest().withBotName("ContactCenterBot").withBotAlias("$LATEST").withUserId(request.Details.ContactData.ContactId);
        if (intentDetails != null) {
            String intentName = intentDetails.getIntentName();
            //name should never be null in the json if intentDetails exist
            Map<String, String> sessionAttributes = new HashMap<>();
            sessionAttributes.put("rfc", intentName);
            //DialogAction dialogAction = new DialogAction().withIntentName("Identification").withType(DialogActionType.ElicitSlot).withMessage("Please provide your slot name").withMessageFormat(MessageFormatType.PlainText).withSlotToElicit("MerchantID");
            DialogAction dialogAction = new DialogAction().withIntentName(intentName).withType(DialogActionType.ConfirmIntent).withMessageFormat(MessageFormatType.PlainText).withMessage(intentDetails.getIntentConfirmationPrompt());
            putSessionRequest.withDialogAction(dialogAction).withSessionAttributes(sessionAttributes);

        } else {
            DialogAction dialogAction = new DialogAction().withType(DialogActionType.ElicitIntent).withMessageFormat(MessageFormatType.PlainText).withMessage("Welcome to Bank Payment Solutions. In a few words please state the reason for your call");
            putSessionRequest.withDialogAction(dialogAction);
        }


        PutSessionResult result = client.putSession(putSessionRequest);
        System.out.println("Success with dialogAction: " + result);
        System.out.println("Request: " + putSessionRequest);
        System.out.println("UserId: " + putSessionRequest.getUserId());

        AWSConnectResponse response = new AWSConnectResponse();
        response.statusCode = 200;

        return response;
    }

    private Map<String, IntentDetails> getDnisIntentMap() {
        Map<String, IntentDetails> dnisIntentMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            URL url = loader.getResource("dnisIntent.json");
            Map<String, IntentDetails> map = objectMapper.readValue(url, new TypeReference<Map<String, IntentDetails>>() {
            });

            dnisIntentMap = map;
        } catch (IOException e) {
            System.out.println("Error occured while reading map");
        }
        return dnisIntentMap;
    }
}
