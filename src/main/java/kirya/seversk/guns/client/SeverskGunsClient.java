package kirya.seversk.guns.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class SeverskGunsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModKeybinds.initialize();

        ClientTickEvents.START_CLIENT_TICK.register(ClientListener::onStartTick);
        ClientTickEvents.END_CLIENT_TICK.register(ClientListener::onEndTick);
    }
}
