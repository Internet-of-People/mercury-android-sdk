package world.libertaria.mercury.sdk;

import java.io.IOException;
import java.util.List;

public class RustAPI {
    private static native void nsendMessage(final String pattern);

    private static native void nstart();

    private static native void nstop();


    public void sendMessage(String message) {
        nsendMessage(message);
    }

    void start() throws IOException {
        nstart();


    }

    void stop() {
        nstop();
    }

    public List<DAppContact> getContacts() {
        throw new UnsupportedOperationException();
    }

    public List<DAppContact> getContacts(String profileId) {
        throw new UnsupportedOperationException();
    }

    private synchronized void writeSocket() {
        // TODO
    }
}
