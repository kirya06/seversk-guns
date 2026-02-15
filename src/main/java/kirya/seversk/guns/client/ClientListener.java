package kirya.seversk.guns.client;

import kirya.seversk.guns.items.ammunition.AmmoItem;
import kirya.seversk.guns.items.ammunition.AmmoType;
import kirya.seversk.guns.items.ammunition.CaliberType;
import kirya.seversk.guns.items.ammunition.MagItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/// Responsible for listening to client events (tick, tooltips etc.) and do stuff. Mainly input related.
public class ClientListener {

    public static void onStartTick(Minecraft client) {
    }

    public static void onEndTick(Minecraft client) {
    }

    public static void onTooltip(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> components) {
        if (itemStack.getItem() instanceof MagItem magItem) {
            var magComponent = magItem.getMagComponent(itemStack);

            if (magComponent.ammo() == 0) {

                components.add(
                        Component.literal(String.format("[%s]", Component.translatable("magtooltip.seversk-guns.empty").getString()))
                                .withColor(ARGB.color(170, 170, 170))
                );
                components.add(Component.literal(""));

            } else {

                components.add(
                        Component.literal(String.format("%d / %d", magComponent.ammo(), magItem.maxCapacity))
                                .withColor(ARGB.color(170, 170, 170))
                );

                components.add(
                        Component.literal(String.format("[%s]", AmmoItem.getTranslatedName(CaliberType.fromInt(magComponent.caliber()), AmmoType.fromInt(magComponent.ammoType())).getString()))
                                .withColor(ARGB.color(170, 170, 170))
                );

                components.add(Component.literal(""));

            }

            components.add(
                    Component.translatable("magtooltip.seversk-guns.caliber")
                            .append(Component.translatable(CaliberType.getTranslationKey(CaliberType.fromInt(magComponent.caliber()))))
            );
        }
    }
}
