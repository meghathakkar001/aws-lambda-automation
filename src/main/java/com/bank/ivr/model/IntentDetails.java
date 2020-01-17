package com.bank.ivr.model;

public class IntentDetails {

    private String intentName;
    private String intentConfirmationPrompt;

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public String getIntentConfirmationPrompt() {
        return intentConfirmationPrompt;
    }

    public void setIntentConfirmationPrompt(String intentConfirmationPrompt) {
        this.intentConfirmationPrompt = intentConfirmationPrompt;
    }
}
