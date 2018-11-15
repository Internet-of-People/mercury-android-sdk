package global.iop.mercury.sdk.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetContactsByProfileRequest extends AuthenticatedSocketRPCRequest {
    @JsonProperty("profile_id")
    private final String profileId;

    public GetContactsByProfileRequest(String sessionId, String profileId) {
        super(sessionId);
        this.profileId = profileId;
    }
}
