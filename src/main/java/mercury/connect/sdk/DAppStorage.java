package mercury.connect.sdk;

import java.util.Map;
import java.util.Optional;

public class DAppStorage<K, V> {
    public Optional<V> get(K key) {
        return Optional.empty();
    }

    public Map<K, Optional<V>> getAll(K... keys) {
        return null;
    }

    public boolean put(K key, V value) {
        return false;
    }

    public boolean remove(K key) {
        return false;
    }
}
