package kirya.seversk.guns.items.ammunition;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class AmmoItem extends Item {

    public CaliberType caliber = CaliberType.GENERIC;
    public AmmoType ammoType = AmmoType.GENERIC;

    public AmmoProperties ammoProperties;

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

    public AmmoItem withAmmoProperties(AmmoProperties props) {
        ammoProperties = props;
        return this;
    }

    public static Component getTranslatedName(CaliberType caliber, AmmoType ammo) {
        return Component.translatable(AmmoType.getTranslationKey(ammo)).append(" ").append(Component.translatable(CaliberType.getTranslationKey(caliber)));
    }

    @Override
    public @NonNull Component getName(@NonNull ItemStack itemStack) {
        return getTranslatedName(caliber, ammoType);
    }
}

