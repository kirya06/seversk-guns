package kirya.seversk.guns.items.ammunition;

import net.minecraft.world.item.Item;

public class MagItem extends Item {

    public CaliberType caliber = CaliberType.Generic;
    public int maxCapacity = 1;

    public MagItem(Item.Properties properties) {
        super(properties);
    }

    public MagItem withAmmoType(CaliberType type) {
        caliber = type;
        return this;
    }

    public MagItem withCapacity(int capacity) {
        maxCapacity = capacity;
        return this;
    }
}
