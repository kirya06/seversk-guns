package kirya.seversk.guns.items.ammunition;

import kirya.seversk.guns.items.ModComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MagItem extends Item {

    public int maxCapacity = 1;

    public MagItem(Properties properties) {
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

    /// returns true if the ammo was successfully consumed.
    public boolean consumeAmmo(ItemStack mag) {
        return true;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack mag, ItemStack otherItemStack, Slot slot, ClickAction click, Player player, SlotAccess slotAccess) {

        if (otherItemStack.getItem() instanceof AmmoItem ) {

            if (!canBeLoadedIntoMag(mag, otherItemStack))
                return false;

            if (click == ClickAction.PRIMARY) {
                otherItemStack.setCount(0);
            }

            if (click == ClickAction.SECONDARY) {
                otherItemStack.shrink(1);
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack itemStack, Slot slot, ClickAction click, Player player) {
        ItemStack slotStack = slot.getItem();

        if (slotStack.getItem() instanceof AmmoItem) {

            if (!canBeLoadedIntoMag(itemStack, slotStack))
                return false;

            // consume the whole ammo stack
            if (click == ClickAction.SECONDARY) {
                slotStack.setCount(0);
                return true;
            }

        }

        return false;
        // return super.overrideStackedOnOther(itemStack, slot, clickAction, player);
    }

    /// adding ammo into the mag. Amount specifies how much ammo we want to add, use -1 to consume the whole stack if possible.
    ///
    /// returns the amount of ammo we grabbed from the stack
    private int addToMag(ItemStack mag, ItemStack ammo, int amount) {
        return 0;
    }

    private MagComponent getMagComponent(ItemStack mag) {
        if (!(mag.getItem() instanceof MagItem))
            throw new  IllegalArgumentException(mag.toString());

        return mag.get(ModComponents.MAG);
    }

    /// if ammo caliber equals the caliber of mag - return true
    private boolean isCaliberEqual(ItemStack mag, ItemStack ammo) {
        if (!(mag.getItem() instanceof MagItem))
            return false;
        if (!(ammo.getItem() instanceof AmmoItem))
            return false;

        CaliberType magCaliber = getMagComponent(mag).getCaliberType();
        CaliberType ammoCaliber = ((AmmoItem) ammo.getItem()).caliber;

        return magCaliber == ammoCaliber;
    }

    private boolean canBeLoadedIntoMag(ItemStack mag, ItemStack ammo) {
        if (!isCaliberEqual(mag, ammo))
            return false;

        var magComponent = getMagComponent(mag);

        if (magComponent.ammo() == 0)
            return true;

        return magComponent.getAmmoType() == ((AmmoItem)(ammo.getItem())).ammoType;
    }
}
