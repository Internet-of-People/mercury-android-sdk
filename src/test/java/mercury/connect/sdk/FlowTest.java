package mercury.connect.sdk;

import org.junit.jupiter.api.Test;

import java.util.UUID;

class FlowTest {
    @Test
    void test() {
        DAppEndpoint endpoint = new DAppEndpoint(null, null);
        endpoint.connect(UUID.randomUUID());
    }
}
