package kirya.seversk.guns;

import kirya.seversk.guns.items.ModComponents;
import kirya.seversk.guns.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeverskGuns implements ModInitializer {
	public static final String MOD_ID = "seversk-guns";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void print(Object msg) {
		LOGGER.info(msg.toString());
	}

	@Override
	public void onInitialize() {

		ModComponents.initialize();
		ModItems.initialize();

		print("initialization finished");
	}
}