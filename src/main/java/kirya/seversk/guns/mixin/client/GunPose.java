package kirya.seversk.guns.mixin.client;

import kirya.seversk.guns.SeverskGuns;
import kirya.seversk.guns.items.GunItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AvatarRenderer.class)
public class GunPose {

    @Inject(method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At("TAIL"), cancellable = true)
    private static void gunPose(Avatar avatar, ItemStack itemStack, InteractionHand interactionHand, CallbackInfoReturnable<ArmPose> ci) {
        if (avatar.getMainHandItem().getItem() instanceof GunItem) {
            ci.setReturnValue(ArmPose.BOW_AND_ARROW);
            return;
        }

        ci.setReturnValue(ArmPose.ITEM);
    }
}
