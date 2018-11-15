package world.libertaria.mercury.sdk.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
    private String id;
    @JsonProperty("profile_id")
    private String profileId;
    private String alias;
    private String description;
    private boolean available;
    private String avatar;

    public String getId() {
        return id;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getAlias() {
        return alias;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getAvatar() {
        return avatar;
    }
}
