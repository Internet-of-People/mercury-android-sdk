package world.libertaria.mercury.sdk.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSelectedProfileResult extends SocketRPCResult {
    @JsonProperty("profile_id")
    private String profileId;

    public String getProfileId() {
        return profileId;
    }
}
