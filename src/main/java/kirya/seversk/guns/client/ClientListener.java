package kirya.seversk.guns.client;

import kirya.seversk.guns.items.GunItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;

/// Responsible for listening to client tick events and do stuff. Mainly input related.
public class ClientListener {

    public static void onStartTick(Minecraft client) {
    }

    public static void onEndTick(Minecraft client) {
        var player = client.player;

        if (player == null)
            return;

        gunFire(client, player);
    }

    /// detect LMB and try to shoot when holding a gun
    private static void gunFire(Minecraft client, LocalPlayer player) {
        var cd = player.getCooldowns();
        var itemStack = player.getMainHandItem();

        if (itemStack.getItem() instanceof GunItem item) {
            if (client.options.keyAttack.isDown() && !cd.isOnCooldown(itemStack)) {

                if (!item.gunProperties.isAuto) {
                    client.options.keyAttack.setDown(false);
                }

                player.playSound(SoundEvents.ARROW_SHOOT);
                cd.addCooldown(itemStack, item.gunProperties.attackCooldownTicks);
            }
        }
    }
}
