package world.libertaria.mercury.sdk;

public class DAppSession {
    private final String sessionId;
    private final String profileId;
    private final String applicationId;

    public DAppSession(String sessionId, String profileId, String applicationId) {
        this.sessionId = sessionId;
        this.profileId = profileId;
        this.applicationId = applicationId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getApplicationId() {
        return applicationId;
    }
}
