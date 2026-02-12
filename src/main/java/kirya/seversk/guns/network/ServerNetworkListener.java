package kirya.seversk.guns.network;

import kirya.seversk.guns.items.GunItem;
import kirya.seversk.guns.items.ModComponents;
import kirya.seversk.guns.network.C2S.FireGunPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.sounds.SoundEvents;

/// listens for server events such as packet/payload arrival
public class ServerNetworkListener {

    public static void fireGunPayloadReceive(FireGunPayload payload, ServerPlayNetworking.Context context) {
        var player = context.player();
        var itemStack = player.getMainHandItem();

        if (itemStack.getItem() instanceof GunItem) {
            int ammo = itemStack.getOrDefault(ModComponents.GUN_AMMO, 0);

            if (ammo <= 0)
                return;

            itemStack.set(ModComponents.GUN_AMMO, Math.clamp(ammo - 1, 0, Integer.MAX_VALUE));
        }
    }

}
