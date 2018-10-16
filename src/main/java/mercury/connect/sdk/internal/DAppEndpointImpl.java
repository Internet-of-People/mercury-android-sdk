package mercury.connect.sdk.internal;

import mercury.connect.sdk.DAppEndpoint;
import mercury.connect.sdk.DAppSession;

import java.util.UUID;

class DAppEndpointImpl implements DAppEndpoint {
    @Override
    public DAppSession connect(UUID applicationId) {
        return null;
    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
