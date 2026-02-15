package kirya.seversk.guns.items.ammunition;

public enum AmmoType {

    GENERIC,

    LOW_QUALITY,
    FMJ,
    ARMOR_PIERCING,
    HOLLOW_POINT;

    public static AmmoType fromInt(int i) {
        var values = AmmoType.values();

        if(i > values.length - 1)
            return GENERIC;

        return values[i];
    }

    public static String getTranslationKey(AmmoType type) {
        return String.format("ammo_type.seversk-guns.%s", type.toString().toLowerCase());
    }
}
