package world.libertaria.mercury.sdk;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class DAppStorage<K, V> {
    public Maybe<V> get(K key) {
        throw new UnsupportedOperationException();
    }

    public Single<Boolean> put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Single<Boolean> remove(K key) {
        throw new UnsupportedOperationException();
    }
}
