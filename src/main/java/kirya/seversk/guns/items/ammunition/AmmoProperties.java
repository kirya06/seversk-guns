package kirya.seversk.guns.items.ammunition;

/// describes various properties an ammo can have.
public class AmmoProperties {

    public float damageMultiplier = 1;
    public float penMultiplier = 1;
    public float recoilMultiplier = 1;
    public float weaponWearMultiplier = 1;

    public int bulletCount = 1;

    private AmmoProperties() { }

    public static AmmoProperties generic() {
        return new AmmoProperties();
    }

    public static AmmoProperties fullMetalJacket() {
        var prop = new AmmoProperties();

        prop.damageMultiplier = 1.1f;
        prop.penMultiplier = 1.1f;

        return prop;
    }

    public static AmmoProperties armorPiercing() {
        var prop = new AmmoProperties();

        prop.penMultiplier = 1.15f;

        return prop;
    }

    public static AmmoProperties hollowPoint() {
        var prop = new AmmoProperties();

        prop.damageMultiplier = 1.2f;

        return prop;
    }
}
