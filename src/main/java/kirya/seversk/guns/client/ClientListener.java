package kirya.seversk.guns.client;

import kirya.seversk.guns.items.GunItem;
import kirya.seversk.guns.items.ModComponents;
import kirya.seversk.guns.items.ammunition.AmmoItem;
import kirya.seversk.guns.items.ammunition.AmmoType;
import kirya.seversk.guns.items.ammunition.CaliberType;
import kirya.seversk.guns.items.ammunition.MagItem;
import kirya.seversk.guns.network.C2S.FireGunPayload;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/// Responsible for listening to client events (tick, tooltips etc.) and do stuff. Mainly input related.
public class ClientListener {

    public static void onStartTick(Minecraft client) {
    }

    public static void onEndTick(Minecraft client) {
        var player = client.player;

        if (player == null)
            return;

        gunFire(client, player);
    }
    public static void onTooltip(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> components) {

        if (itemStack.getItem() instanceof GunItem gunItem) {
            int ammo = itemStack.getOrDefault(ModComponents.GUN_AMMO, 0);

            components.add(Component.literal(String.format("Ammo: %d", ammo)));
        }

        if (itemStack.getItem() instanceof MagItem magItem) {
            var magComponent = magItem.getMagComponent(itemStack);

            if (magComponent.ammo() == 0) {

                components.add(
                        Component.literal(String.format("[%s]", Component.translatable("magtooltip.seversk-guns.empty").getString()))
                );
                components.add(Component.literal(""));

            } else {

                components.add(
                        Component.literal(String.format("%d / %d", magComponent.ammo(), magItem.maxCapacity))
                );
                components.add(
                        Component.literal(String.format("[%s]", AmmoItem.getTranslatedName(CaliberType.fromInt(magComponent.caliber()), AmmoType.fromInt(magComponent.ammoType())).getString()))
                );

                components.add(Component.literal(""));

            }

            components.add(
                    Component.translatable("magtooltip.seversk-guns.caliber")
                            .append(Component.translatable(CaliberType.getTranslationKey(CaliberType.fromInt(magComponent.caliber()))))
            );
        }
    }

    /// detect LMB and try to shoot when holding a gun
    private static void gunFire(Minecraft client, LocalPlayer player) {
        var cd = player.getCooldowns();
        var itemStack = player.getMainHandItem();

        if (itemStack.getItem() instanceof GunItem item) {
            if (client.options.keyAttack.isDown() && !cd.isOnCooldown(itemStack)) {

                var ammo = itemStack.getOrDefault(ModComponents.GUN_AMMO, 0);
                if (ammo <= 0)
                    return;

                if (!item.gunProperties.isAuto) {
                    client.options.keyAttack.setDown(false);
                }

                FireGunPayload payload = new FireGunPayload((byte)0);
                ClientPlayNetworking.send(payload);

                cd.addCooldown(itemStack, item.gunProperties.attackCooldownTicks);
                player.playSound(SoundEvents.CROSSBOW_SHOOT);
            }
        }
    }


}
