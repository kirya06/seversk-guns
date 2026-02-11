package kirya.seversk.guns.items;

import net.minecraft.world.item.Item;

public class GunItem extends Item {

    public GunProperties gunProperties = new GunProperties();

    public GunItem(Item.Properties properties) {
        super(properties);
    }

    public GunItem withGunProperties(GunProperties props) {
        gunProperties = props;
        return this;
    }
}
