package kirya.seversk.guns.items;

public class GunProperties {

    public int attackCooldownTicks = 3;
    public boolean isAuto = false;


    ///
    /// Premade gun properties
    ///

    public static  GunProperties getGenericGun() {
        return new GunProperties();
    }
}
