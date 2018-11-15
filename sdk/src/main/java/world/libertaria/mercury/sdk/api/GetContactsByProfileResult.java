package world.libertaria.mercury.sdk.api;

import java.util.List;

public class GetContactsByProfileResult extends SocketRPCResult {
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }
}
