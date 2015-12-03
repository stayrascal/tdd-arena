package com.tw.arena.weapon.big;

import com.tw.arena.weapon.base.BaseWeapon;
import com.tw.arena.weapon.property.WeaponProperty;

public class Spear extends BaseWeapon implements LongWeapon {
    public Spear(String name, int damage) {
        super(name, damage);
    }

    public Spear(String name, int damage, WeaponProperty weaponProperty) {
        super(name, damage, weaponProperty);
    }
}
