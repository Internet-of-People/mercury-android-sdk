package global.iop.mercury.sdk.api;

public class GetSelectedProfileRequest extends AuthenticatedSocketRPCRequest {
    public GetSelectedProfileRequest(String sessionId) {
        super(sessionId);
    }
}
