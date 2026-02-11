package kirya.seversk.guns.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class GunItem extends Item {

    public GunProperties gunProperties = new GunProperties();

    public GunItem(Item.Properties properties) {
        super(properties);
    }

    public GunItem withGunProperties(GunProperties props) {
        gunProperties = props;
        return this;
    }

    @Override
    public boolean allowComponentsUpdateAnimation(@NonNull Player player, @NonNull InteractionHand hand, @NonNull ItemStack oldStack, @NonNull ItemStack newStack) {
        return false;
    }
}
