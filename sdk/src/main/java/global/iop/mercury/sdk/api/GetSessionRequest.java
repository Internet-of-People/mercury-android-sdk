package global.iop.mercury.sdk.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetSessionRequest extends SocketRPCRequest {
    @JsonProperty("application_id")
    private final String applicationId;
    private final List<Long> permissions;

    public GetSessionRequest(String applicationId, List<Long> permissions) {
        this.applicationId = applicationId;
        this.permissions = permissions;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public List<Long> getPermissions() {
        return permissions;
    }
}
