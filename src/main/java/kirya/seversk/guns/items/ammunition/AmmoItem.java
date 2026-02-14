package kirya.seversk.guns.items.ammunition;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class AmmoItem extends Item {

    public CaliberType caliber = CaliberType.GENERIC;
    public AmmoType ammoType = AmmoType.GENERIC;

    public AmmoItem(Item.Properties properties) {
        super(properties.modelId(Identifier.parse("minecraft:arrow")));
    }

    public AmmoItem withCaliber(CaliberType type) {
        caliber = type;
        return this;
    }

    public AmmoItem withAmmoType(AmmoType type) {
        ammoType = type;
        return this;
    }
}

