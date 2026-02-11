package kirya.seversk.guns.client;

import com.mojang.blaze3d.platform.InputConstants;
import kirya.seversk.guns.SeverskGuns;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class SeverskGunsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        KeyMapping.Category gunCategory = new KeyMapping.Category(
                Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, "gun")
        );

        KeyMapping reload = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.seversk-guns.reload",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_R,
                        gunCategory
                )
        );
    }
}
