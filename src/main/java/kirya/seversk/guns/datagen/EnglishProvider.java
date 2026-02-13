package kirya.seversk.guns.datagen;

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

    }
}
