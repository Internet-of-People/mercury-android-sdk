package mercury.connect.sdk.internal;

import mercury.connect.sdk.DAppStorage;

import java.util.Map;
import java.util.Optional;

class DAppStorageImpl<K,V> implements DAppStorage<K,V> {
    @Override
    public Optional<V> get(K key) {
        return Optional.empty();
    }

    @Override
    public Map<K, Optional<V>> getAll(K... keys) {
        return null;
    }

    @Override
    public boolean put(K key, V value) {
        return false;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }
}
