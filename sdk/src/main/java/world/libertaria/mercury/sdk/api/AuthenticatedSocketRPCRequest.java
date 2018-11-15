package world.libertaria.mercury.sdk.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AuthenticatedSocketRPCRequest extends SocketRPCRequest {
    @JsonProperty("session_id")
    private String sessionId;

    AuthenticatedSocketRPCRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
