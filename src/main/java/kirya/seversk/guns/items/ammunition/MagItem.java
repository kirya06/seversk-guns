package kirya.seversk.guns.items.ammunition;

import kirya.seversk.guns.items.ModComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

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
}
