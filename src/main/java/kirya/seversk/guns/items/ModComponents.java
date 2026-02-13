package kirya.seversk.guns.items;

import kirya.seversk.guns.SeverskGuns;
import kirya.seversk.guns.items.ammunition.MagComponent;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

public class ModComponents {

    public static final DataComponentType<Integer> GUN_AMMO = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, "ammo"),
            DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).build()
    );

    public static final DataComponentType<MagComponent> MAG = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, "mag"),
            DataComponentType.<MagComponent>builder().persistent(MagComponent.CODEC).build()
    );

    public static void initialize() {}

}
