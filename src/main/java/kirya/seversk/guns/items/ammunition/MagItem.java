package kirya.seversk.guns.items.ammunition;

import kirya.seversk.guns.items.ModComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.ARGB;

public class MagItem extends Item {

    public static final int ITEM_BAR_COLOR = ARGB.color(167, 206, 201);

    public int maxCapacity;

    public MagItem(Properties properties, CaliberType caliber, int capacity) {

        super(
                properties
                        .component(ModComponents.MAG, new MagComponent(0, caliber.ordinal(), capacity))
                        .stacksTo(1)
                        .modelId(Identifier.parse("minecraft:bundle")) // placeholder
        );

        maxCapacity = capacity;
    }

    public MagComponent getMagComponent(ItemStack mag) {
        if (!(mag.getItem() instanceof MagItem))
            throw new  IllegalArgumentException(mag.toString());

        return mag.get(ModComponents.MAG);
    }

    /// consume an ammo like it was shot out the gun
    ///
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
                otherItemStack.shrink(
                        addToMag(mag, otherItemStack, -1)
                );
            }

            if (click == ClickAction.SECONDARY) {
                otherItemStack.shrink(
                        addToMag(mag, otherItemStack, 1)
                );
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack mag, Slot slot, ClickAction click, Player player) {
        ItemStack slotStack = slot.getItem();

        if (slotStack.getItem() instanceof AmmoItem) {

            if (!canBeLoadedIntoMag(mag, slotStack))
                return false;

            // consume the whole ammo stack
            if (click == ClickAction.PRIMARY) {

                slotStack.shrink(
                        addToMag(mag, slotStack, -1)
                );

                return true;
            }

        }

        return false;
        // return super.overrideStackedOnOther(itemStack, slot, clickAction, player);
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        var magComponent = getMagComponent(itemStack);

        return magComponent.ammo() > 0;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        var magComponent = getMagComponent(itemStack);
        return Math.min(13 * magComponent.ammo() / maxCapacity, 13);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return ITEM_BAR_COLOR;
    }

    /// adding ammo into the mag. Amount specifies how much ammo we want to add, use -1 to consume the whole stack if possible.
    ///
    /// returns the amount of ammo we grabbed from the stack
    private int addToMag(ItemStack mag, ItemStack ammo, int amount) {
        if (amount < 0)
            amount = ammo.getCount();

        var ammoItem = (AmmoItem)ammo.getItem();
        var magItem = (MagItem)mag.getItem();
        var magComponent = getMagComponent(mag);

        int ammoToInsert = Math.min(magItem.maxCapacity - magComponent.ammo(), amount);

        mag.set(ModComponents.MAG, new MagComponent(
                magComponent.ammo() + ammoToInsert,
                magComponent.caliber(),
                ammoItem.ammoType.ordinal()
        ));
        return ammoToInsert;
    }

    /// empty the mag into the specified slot
    ///
    /// returns the amount of ammo it grabbed from the mag
    private int emptyTheMag(ItemStack mag, Slot slot) {
        return 0;
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
