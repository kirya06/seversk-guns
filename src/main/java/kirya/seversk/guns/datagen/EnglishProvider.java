package kirya.seversk.guns.datagen;

import kirya.seversk.guns.items.ammunition.AmmoType;
import kirya.seversk.guns.items.ammunition.CaliberType;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class EnglishProvider extends FabricLanguageProvider {

    public EnglishProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider wrapperLookup, TranslationBuilder translationBuilder) {
        /// items

        translationBuilder.add("item.seversk-guns.generic_pistol", "Generic Pistol");
        translationBuilder.add("item.seversk-guns.generic_ammo", "Generic Ammo");
        translationBuilder.add("item.seversk-guns.generic_mag", "Generic Magazine");

        /// keybinds

        translationBuilder.add("key.category.seversk-guns.gun", "Seversk Guns");
        translationBuilder.add("key.seversk-guns.reload", "Reload");

        generateCaliberAndAmmo(translationBuilder);

    }

    private void generateCaliberAndAmmo(TranslationBuilder translationBuilder) {

        /// calibers

        translationBuilder.add(CaliberType.getTranslationKey(CaliberType.GENERIC), "Unknown");
        translationBuilder.add(CaliberType.getTranslationKey(CaliberType.PISTOL), "9x18");
        translationBuilder.add(CaliberType.getTranslationKey(CaliberType.PISTOL_FOREIGN), "9x19");
        translationBuilder.add(CaliberType.getTranslationKey(CaliberType.PISTOL_ACP), ".45 ACP");
        translationBuilder.add(CaliberType.getTranslationKey(CaliberType.SHOTGUN), "12 Gauge");
        translationBuilder.add(CaliberType.getTranslationKey(CaliberType.AK), "5.45");


        /// ammo types
        translationBuilder.add(AmmoType.getTranslationKey(AmmoType.GENERIC), "Strange");
        translationBuilder.add(AmmoType.getTranslationKey(AmmoType.LOW_QUALITY), "Handicraft");
        translationBuilder.add(AmmoType.getTranslationKey(AmmoType.FMJ), "FMJ");
        translationBuilder.add(AmmoType.getTranslationKey(AmmoType.ARMOR_PIERCING), "Armor Piercing");
        translationBuilder.add(AmmoType.getTranslationKey(AmmoType.HOLLOW_POINT), "Hollow Point");


    }
}
