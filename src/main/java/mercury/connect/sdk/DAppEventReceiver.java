package mercury.connect.sdk;


public interface DAppEventReceiver {
    void onReceive(Event event, Object payload);
}
