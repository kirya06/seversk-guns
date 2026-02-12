package kirya.seversk.guns.items.ammunition;

import net.minecraft.world.item.Item;

public class AmmoItem extends Item {

    public CaliberType Caliber = CaliberType.Generic;

    public AmmoItem(Item.Properties properties) {
        super(properties);
    }

    public AmmoItem withAmmoType(CaliberType type) {
        Caliber = type;
        return this;
    }
}

