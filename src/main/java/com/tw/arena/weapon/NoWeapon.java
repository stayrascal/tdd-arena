package com.tw.arena.weapon;

import com.tw.arena.weapon.property.NoWeaponProperty;
import com.tw.arena.weapon.property.WeaponProperty;

public class NoWeapon implements Weapon {

    private static NoWeapon INSTANCE = new NoWeapon();

    private NoWeapon() {
    }

    public static NoWeapon getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public WeaponProperty getWeaponProperty() {
        return NoWeaponProperty.getInstance();
    }

}
