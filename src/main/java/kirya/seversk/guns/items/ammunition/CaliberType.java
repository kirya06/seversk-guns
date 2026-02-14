package kirya.seversk.guns.items.ammunition;

public enum CaliberType {
    /// mainly for testing purposes
    GENERIC,

    /// 9x18 caliber
    PISTOL,
    /// 9x19 caliber
    PISTOL_FOREIGN,
    /// .45 ACP
    PISTOL_ACP,

    /// 12 Gauge
    SHOTGUN,

    /// 5.45 caliber
    AK;

    public static CaliberType fromInt(int i) {
        var values = CaliberType.values();

        if(i > values.length - 1)
            return GENERIC;

        return values[i];
    }
}
