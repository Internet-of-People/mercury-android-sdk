package mercury.connect.sdk;

import java.util.List;
import java.util.UUID;

public class DAppSession {
    public List<DAppContact> getContacts() {
        throw new UnsupportedOperationException();
    }

    public void initiateContact(UUID withProfileId) {
        throw new UnsupportedOperationException();
    }

    public void registerEventReceiver(Event[] filter, DAppEventReceiver receiver) {
        throw new UnsupportedOperationException();
    }

    public DAppStorage getStorage() {
        throw new UnsupportedOperationException();
    }
}
