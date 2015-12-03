package com.tw.arena.weapon;


import com.tw.arena.weapon.property.WeaponProperty;

public class EMeiStab extends BaseWeapon implements ShortWeapon {

    public EMeiStab(String name, int damage) {
        super(name, damage);
    }

    public EMeiStab(String name, int damage, WeaponProperty weaponProperty) {
        super(name, damage, weaponProperty);
    }
}
