package kirya.seversk.guns;

import kirya.seversk.guns.datagen.EnglishProvider;
import kirya.seversk.guns.datagen.ModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SeverskGunsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		// language
		pack.addProvider(EnglishProvider::new);

		// generating models
		pack.addProvider(ModelProvider::new);
	}
}
