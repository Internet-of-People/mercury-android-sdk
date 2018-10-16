package mercury.connect.sdk;

import java.util.UUID;

public enum Event {
    CALL(DAppCall.class),
    CONTACT_INITIATION_FAILED(UUID.class),
    CONTACT_INITIATED(DAppContact.class);

    private Class payloadClass;

    Event(Class payloadClass) {
        this.payloadClass = payloadClass;
    }

    public Class getPayloadClass() {
        return payloadClass;
    }
}
