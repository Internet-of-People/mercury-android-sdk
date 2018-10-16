package mercury.connect.sdk.internal;

import mercury.connect.sdk.DAppContact;
import mercury.connect.sdk.DAppEventReceiver;
import mercury.connect.sdk.DAppSession;
import mercury.connect.sdk.DAppStorage;
import mercury.connect.sdk.Event;

import java.util.List;
import java.util.UUID;

class DAppSessionImpl implements DAppSession {
    @Override
    public List<DAppContact> getContacts() {
        return null;
    }

    @Override
    public void initiateContact(UUID withProfileId) {

    }

    @Override
    public void registerEventReceiver(Event[] filter, DAppEventReceiver receiver) {

    }

    @Override
    public DAppStorage getStorage() {
        return null;
    }
}
