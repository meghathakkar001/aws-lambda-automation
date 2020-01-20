package com.bank.ivr.model;

public class AWSConnectResponse {

    private int statusCode;
    private String intentName;
    private String promptText;
    private String isIntentKnown;
    private String confirmationPromptText;
    private String mainBotWelcomeText;

    public String getMainBotWelcomeText() {
        return mainBotWelcomeText;
    }

    public void setMainBotWelcomeText(String mainBotWelcomeText) {
        this.mainBotWelcomeText = mainBotWelcomeText;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public String getIsIntentKnown() {
        return isIntentKnown;
    }

    public void setIsIntentKnown(String isIntentKnown) {
        this.isIntentKnown = isIntentKnown;
    }

    public String getConfirmationPromptText() {
        return confirmationPromptText;
    }

    public void setConfirmationPromptText(String confirmationPromptText) {
        this.confirmationPromptText = confirmationPromptText;
    }

    @Override
    public String toString() {
        return "AWSConnectResponse{" +
                "statusCode=" + statusCode +
                '}';
    }

    public static final class AWSConnectResponseBuilder {
        private int statusCode;
        private String intentName;
        private String promptText;
        private String isIntentKnown;
        private String confirmationPromptText;
        private String mainBotWelcomeText;

        private AWSConnectResponseBuilder() {
        }

        public static AWSConnectResponseBuilder anAWSConnectResponse() {
            return new AWSConnectResponseBuilder();
        }

        public AWSConnectResponseBuilder withStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public AWSConnectResponseBuilder withIntentName(String intentName) {
            this.intentName = intentName;
            return this;
        }

        public AWSConnectResponseBuilder withPromptText(String promptText) {
            this.promptText = promptText;
            return this;
        }

        public AWSConnectResponseBuilder withIsIntentKnown(String isIntentKnown) {
            this.isIntentKnown = isIntentKnown;
            return this;
        }

        public AWSConnectResponseBuilder withConfirmationPromptText(String confirmationPromptText) {
            this.confirmationPromptText = confirmationPromptText;
            return this;
        }

        public AWSConnectResponseBuilder withMainBotWelcomeText(String mainBotWelcomeText) {
            this.mainBotWelcomeText = mainBotWelcomeText;
            return this;
        }

        public AWSConnectResponse build() {
            AWSConnectResponse aWSConnectResponse = new AWSConnectResponse();
            aWSConnectResponse.setStatusCode(statusCode);
            aWSConnectResponse.setIntentName(intentName);
            aWSConnectResponse.setPromptText(promptText);
            aWSConnectResponse.setIsIntentKnown(isIntentKnown);
            aWSConnectResponse.setConfirmationPromptText(confirmationPromptText);
            aWSConnectResponse.setMainBotWelcomeText(mainBotWelcomeText);
            return aWSConnectResponse;
        }
    }
}
