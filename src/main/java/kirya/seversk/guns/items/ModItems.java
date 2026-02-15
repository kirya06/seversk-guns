package kirya.seversk.guns.items;

import kirya.seversk.guns.SeverskGuns;
import kirya.seversk.guns.items.ammunition.*;
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
import org.jspecify.annotations.Nullable;

import java.util.Locale;
import java.util.function.Function;

public class ModItems {

    public static final GunItem GENERIC_PISTOL = register("generic_pistol", GunItem::new, new Item.Properties())
            .withGunProperties(GunProperties.getGenericGun());

    public static final AmmoItem GENERIC_AMMO = registerAmmo(CaliberType.GENERIC, AmmoType.GENERIC, AmmoProperties.generic());
    public static final AmmoItem AMMO_PISTOL_FMJ = registerAmmo(CaliberType.PISTOL, AmmoType.FMJ, AmmoProperties.fullMetalJacket());
    public static final AmmoItem AMMO_PISTOL_LOW_QUALITY = registerAmmo(CaliberType.PISTOL, AmmoType.LOW_QUALITY, AmmoProperties.generic());

    public static final MagItem MAG_GENERIC = registerMag("test", CaliberType.GENERIC, 8);
    public static final MagItem MAG_MAKAROV = registerMag("makarov", CaliberType.PISTOL, 8);


    // creative tab
    public static final ResourceKey<CreativeModeTab> SEVERSK_CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, "creative_tab"));
    public static final CreativeModeTab SEVERSK_CREATIVE_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.GENERIC_PISTOL))
            .title(Component.literal("Seversk Guns"))

            .displayItems((params, output) -> {

                output.accept(MAG_MAKAROV);
                output.accept(AMMO_PISTOL_FMJ);
                output.accept(AMMO_PISTOL_LOW_QUALITY);

            })

            .build();

    public static void initialize() {
        // add items to creative tab
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SEVERSK_CREATIVE_TAB_KEY, SEVERSK_CREATIVE_TAB);
    }

    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties properties) {

        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, name));
        GenericItem item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return  item;
    }

    public static MagItem registerMag(String name, CaliberType caliber, int maxCapacity) {

        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, String.format("mag_%s_%s", name, caliber.name().toLowerCase()))
        );

        MagItem item = new MagItem(new Item.Properties().setId(itemKey), caliber, maxCapacity);
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    /// where
    ///
    /// x -> caliber
    ///
    /// y -> ammoType
    private static AmmoItem[][] ammoRegistry;

    private static void initializeAmmoRegistry() {
        var caliberCount = CaliberType.values().length;
        var ammoCount = AmmoType.values().length;

        ammoRegistry = new AmmoItem[caliberCount][ammoCount];
    }

    public static AmmoItem registerAmmo(CaliberType caliber, AmmoType ammoType, AmmoProperties ammoProperties) {
        if (ammoRegistry == null)
            initializeAmmoRegistry();

        var caliberInt = caliber.ordinal();
        var ammoInt = ammoType.ordinal();

        var caliberName = caliber.name().toLowerCase();
        var ammoName = ammoType.name().toLowerCase(Locale.ROOT);

        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, String.format("ammo_%s_%s", caliberName, ammoName)));

        AmmoItem item = new AmmoItem(new Item.Properties().setId(itemKey))
                .withCaliber(caliber)
                .withAmmoType(ammoType)
                .withAmmoProperties(ammoProperties);

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        ammoRegistry[caliberInt][ammoInt] = item;
        SeverskGuns.print(String.format("%d %d %s", caliberInt, ammoInt, ammoName));

        return item;
    }

    @Nullable
    public static AmmoItem getAmmoItem(CaliberType caliber, AmmoType ammo) {
        return ammoRegistry[caliber.ordinal()][ammo.ordinal()];
    }
}
