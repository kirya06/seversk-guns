package kirya.seversk.guns.client;

import com.mojang.blaze3d.platform.InputConstants;
import kirya.seversk.guns.SeverskGuns;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {

    public static final KeyMapping.Category CATEGORY = new KeyMapping.Category(
            Identifier.fromNamespaceAndPath(SeverskGuns.MOD_ID, "gun")
    );

    public static final KeyMapping KEY_RELOAD = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                    "key.seversk-guns.reload",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_R,
                    CATEGORY
            ));

    public static void initialize() {
    }
}
