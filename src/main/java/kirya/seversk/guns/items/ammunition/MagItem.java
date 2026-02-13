package kirya.seversk.guns.items.ammunition;

import kirya.seversk.guns.SeverskGuns;
import kirya.seversk.guns.items.ModComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MagItem extends Item {

    public CaliberType caliber = CaliberType.Generic;
    public int maxCapacity = 1;

    public MagItem(Item.Properties properties) {
        super(
                properties
                        .stacksTo(1)
                        .modelId(Identifier.parse("minecraft:bundle")) // placeholder
        );
    }

    public MagItem withCapacity(int capacity) {
        maxCapacity = capacity;
        return this;
    }

    public MagComponent getMagComponent(ItemStack mag) {
        if (!(mag.getItem() instanceof MagItem))
            throw new  IllegalArgumentException(mag.toString());

        return mag.get(ModComponents.MAG);
    }

    /// returns true if the ammo was successfully consumed.
    public boolean consumeAmmo(ItemStack mag) {
        return true;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack mag, ItemStack otherItemStack, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        if (otherItemStack.getItem() instanceof AmmoItem) {
            otherItemStack.setCount(0);
            return true;
        }

        return false;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack itemStack, Slot slot, ClickAction click, Player player) {
        ItemStack slotStack = slot.getItem();

        if (slotStack.getItem() instanceof AmmoItem) {

            // consume the whole ammo stack
            if (click == ClickAction.SECONDARY) {
                slotStack.setCount(0);
                return true;
            }
        }

        return false;
        // return super.overrideStackedOnOther(itemStack, slot, clickAction, player);
    }

    private int addToMag(ItemStack mag, ItemStack ammo) {
        return 0;
    }
}
