package world.libertaria.mercury.sdk;

public interface DAppEventReceiver {
    void onReceive(Event event, Object payload);
}
