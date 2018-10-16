package mercury.connect.sdk;

import java.util.Map;
import java.util.Optional;

public interface DAppStorage<K,V> {
    Optional<V> get(K key);

    Map<K,Optional<V>> getAll(K... keys);

    boolean put(K key, V value);

    boolean remove(K key);
}
