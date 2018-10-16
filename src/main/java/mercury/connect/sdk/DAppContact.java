package mercury.connect.sdk;

import java.util.UUID;

public interface DAppContact {
    UUID getProfileId();

    byte[] getRelationProof();

    void call(String initPayload);
}
