package world.libertaria.mercury.sdk;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class DAppSession {
    private final RustAPI rustApi;

    DAppSession(RustAPI rustApi) {
        this.rustApi = rustApi;
    }

    public void connectToSocket() {

    }

    public String getSelectedProfileId() {
        throw new UnsupportedOperationException();
    }

    public Observable<List<DAppContact>> getContacts() {
        throw new UnsupportedOperationException();
    }

    public Observable<List<DAppContact>> getContacts(String profileId) {
        throw new UnsupportedOperationException();
    }

    public Completable initiateContact(String withProfileId) {
        throw new UnsupportedOperationException();
    }

    public DAppStorage getStorage() {
        throw new UnsupportedOperationException();
    }
}
