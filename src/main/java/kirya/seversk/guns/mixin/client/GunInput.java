package kirya.seversk.guns.mixin.client;

import kirya.seversk.guns.SeverskGuns;
import kirya.seversk.guns.items.GunItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class GunInput {
    @Nullable
    @Shadow
    public LocalPlayer player;

    @Inject(method = "handleKeybinds", at = @At("TAIL"))
    public void handleInputEvents(CallbackInfo ci) {
        assert this.player != null;

        if (this.player.getMainHandItem().getItem() instanceof GunItem item) {
            if (Minecraft.getInstance().options.keyAttack.isDown()) {

            }
        }
    }

}
