package global.iop.mercury.sdk.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSessionResult extends SocketRPCResult {
    @JsonProperty("profile_id")
    private String profileId;
    @JsonProperty("session_id")
    private String sessionId;

    public String getProfileId() {
        return profileId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
