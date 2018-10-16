package mercury.connect.sdk.internal;

import mercury.connect.sdk.DAppContact;

import java.util.UUID;

class DAppContactImpl implements DAppContact {
    @Override
    public UUID getProfileId() {
        return null;
    }

    @Override
    public byte[] getRelationProof() {
        return new byte[0];
    }

    @Override
    public void call(String initPayload) {

    }
}
