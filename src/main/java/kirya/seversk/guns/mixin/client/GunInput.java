package kirya.seversk.guns.mixin.client;

import kirya.seversk.guns.items.GunItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class GunInput {
    @Nullable
    @Shadow
    public LocalPlayer player;

    /// do not initiate vanilla attacking when holding GunItem
    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    public void beforeUseItem(CallbackInfoReturnable<Boolean> cir) {
        assert this.player != null;

        var item = this.player.getMainHandItem().getItem();

        if (item instanceof GunItem) {
            cir.setReturnValue(false);
        }
    }

}
