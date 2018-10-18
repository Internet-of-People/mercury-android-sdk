package world.libertaria.mercury.sdk;

import java.util.Map;
import java.util.Optional;

public class DAppStorage<K, V> {
    public Optional<V> get(K key) {
        throw new UnsupportedOperationException();
    }

    public Map<K, Optional<V>> getAll(K... keys) {
        throw new UnsupportedOperationException();
    }

    public boolean put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(K key) {
        throw new UnsupportedOperationException();
    }
}
