package kirya.seversk.guns.items.ammunition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;

/// record for "mag" component that holds info about magazine
///
/// ---
///
/// ammo -> ammo count that is loaded into magazine
///
/// caliber -> byte representation of CaliberType
///
/// ammoType -> byte representation of AmmoType
///
public record MagComponent(int ammo, int caliber, int ammoType) {

    public static final Codec<MagComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("ammo").forGetter(MagComponent::ammo),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("caliber").forGetter(MagComponent::caliber),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("ammo_type").forGetter(MagComponent::ammoType)
        ).apply(builder, MagComponent::new);
    });

    public CaliberType getCaliberType() {
        return CaliberType.fromInt(this.caliber);
    }

    public AmmoType getAmmoType() {
        return AmmoType.fromInt(this.ammoType);
    }
}
