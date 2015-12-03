package com.tw.arena.weapon;

import com.tw.arena.weapon.property.WeaponProperty;

public class Hammer extends BaseWeapon implements MiddleWeapon {

    public Hammer(String name, int damage, WeaponProperty weaponProperty) {
        super(name, damage, weaponProperty);
    }
}
