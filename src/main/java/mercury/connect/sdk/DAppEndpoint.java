package mercury.connect.sdk;

import java.util.UUID;

public interface DAppEndpoint {
    DAppSession connect(UUID applicationId);

    void disconnect();

    boolean isConnected();
}
