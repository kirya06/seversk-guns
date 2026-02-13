package kirya.seversk.guns.items;

import kirya.seversk.guns.SeverskGuns;
import kirya.seversk.guns.items.ammunition.AmmoItem;
import kirya.seversk.guns.items.ammunition.CaliberType;
import kirya.seversk.guns.items.ammunition.MagComponent;
import kirya.seversk.guns.items.ammunition.MagItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class ModItems {

    public static final GunItem GENERIC_PISTOL = register("generic_pistol", GunItem::new, new Item.Properties())
            .withGunProperties(GunProperties.getGenericGun());

    public static final AmmoItem GENERIC_AMMO = register("generic_ammo", AmmoItem::new, new Item.Properties())
            .withAmmoType(CaliberType.Generic);

    public static final MagItem GENERIC_MAG = register("generic_mag", MagItem::new, new Item.Properties()
            .component(ModComponents.MAG, new MagComponent(0, (byte)CaliberType.Generic.ordinal(), "")))
            .withCapacity(10);

    // creative tab
    public static final ResourceKey<CreativeModeTab> SEVERSK_CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, "creative_tab"));
    public static final CreativeModeTab SEVERSK_CREATIVE_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.GENERIC_PISTOL))
            .title(Component.literal("Seversk Guns"))

            .displayItems((params, output) -> {

                output.accept(ModItems.GENERIC_PISTOL);
                output.accept(ModItems.GENERIC_AMMO);
                output.accept(ModItems.GENERIC_MAG);

            })

            .build();

    public static void initialize() {
        // add items to creative tab
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SEVERSK_CREATIVE_TAB_KEY, SEVERSK_CREATIVE_TAB);
    }

    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {

        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, name));
        GenericItem item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return  item;
    }
}
