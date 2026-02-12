package kirya.seversk.guns.network;

import kirya.seversk.guns.SeverskGuns;
import kirya.seversk.guns.network.C2S.FireGunPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

/// listens for server events such as packet/payload arrival
public class ServerNetworkListener {

    public static void fireGunPayloadReceive(FireGunPayload payload, ServerPlayNetworking.Context context) {
        SeverskGuns.print("hello from server");
    }

}
