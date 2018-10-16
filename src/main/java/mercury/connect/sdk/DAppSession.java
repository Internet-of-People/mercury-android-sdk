package mercury.connect.sdk;

import java.util.List;
import java.util.UUID;

public interface DAppSession {
    List<DAppContact> getContacts();

    void initiateContact(UUID withProfileId);

    void registerEventReceiver(Event[] filter, DAppEventReceiver receiver);

    DAppStorage getStorage();
}
