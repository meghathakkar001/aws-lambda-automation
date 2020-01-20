package com.bank.ivr;

import com.amazonaws.services.lexruntime.AmazonLexRuntime;
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lexruntime.model.*;
import com.bank.ivr.model.AWSConnectEvent;
import com.bank.ivr.model.AWSConnectResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LexPutSessionMain {
    public AWSConnectResponse putSessionHandler(AWSConnectEvent request, Context context) {

        Map<String, String> profile = getProfile(request.Details.ContactData.SystemEndpoint.Address);
        AWSConnectResponse.AWSConnectResponseBuilder responseBuilder=AWSConnectResponse.AWSConnectResponseBuilder.anAWSConnectResponse().withStatusCode(200);

        if(initializeIntent(request)){
            //This is the second time call will come to this method if caller said Yes to the confimation message in Yes/No Bot
            String intentName=request.Details.Parameters.get("intentName");
            System.out.println("Intent is passed from connect for lex initialization: "+intentName);
            AmazonLexRuntime client = AmazonLexRuntimeClientBuilder.defaultClient();
            PutSessionRequest putSessionRequest = new PutSessionRequest().withBotName("ContactCenterBot").withBotAlias("$LATEST").withUserId(request.Details.ContactData.ContactId);
            Map<String, String> sessionAttributes = new HashMap<>();
            sessionAttributes.put("rfc", intentName);

            //TODO: we are using ConfirmIntent here but this is not the way it is defined in API
            DialogAction dialogAction = new DialogAction().withIntentName(intentName).withType(DialogActionType.ConfirmIntent).withMessage("DOES NT MATTER").withMessageFormat(MessageFormatType.PlainText);
            putSessionRequest.withSessionAttributes(sessionAttributes).withDialogAction(dialogAction);

            PutSessionResult result = client.putSession(putSessionRequest);

            System.out.println("Request: " + putSessionRequest);
            System.out.println("Success with PutSession Response: " + result);
            System.out.println("UserId: " + putSessionRequest.getUserId());

            responseBuilder.withMainBotWelcomeText("");

        }else{
            //This is the first time call will come to this method
            String welcomeMessage=profile.get("welcomeMessage");
            String intentName=profile.get("intentName");
            String intentConfirmationQuestion= profile.get("intentConfirmationQuestion");
            responseBuilder.withPromptText(welcomeMessage).withMainBotWelcomeText(profile.get("mainBotWelcomeText"));
            if(intentName!=null){
                responseBuilder.withIsIntentKnown("Yes").withIntentName(intentName).withConfirmationPromptText(intentConfirmationQuestion);
            }


        }


        return responseBuilder.build();
    }

    private boolean initializeIntent(AWSConnectEvent request) {
        return (request.Details.Parameters.get("initializeIntent")!=null && "Yes".equals(request.Details.Parameters.get("initializeIntent")));
    }

    private Map<String, String> getProfile(String dnis) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            URL url = loader.getResource("dnisProfile.json");
            Map<String, String> dnisProfileMap = objectMapper.readValue(url, new TypeReference<Map<String, String>>() {
            });

            String profileName=dnisProfileMap.get(dnis);
            if(profileName==null){
                System.out.println("DNIS: "+dnis+"does not have an associated profile. Getting default profile");
                profileName=dnisProfileMap.get("default");
            }

            url = loader.getResource("profile-"+profileName+".json");
            Map<String, String> profileMap = objectMapper.readValue(url, new TypeReference<Map<String, String>>() {
            });

            return profileMap;

        } catch (IOException e) {
            System.out.println("Error occured while reading map ");
            e.printStackTrace();
        }
        System.out.println("Profile not found, returning empty profile");
        return new HashMap<String,String>();
    }
}
