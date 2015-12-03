package com.tw.arena.weapon.middle;

import com.tw.arena.weapon.base.BaseWeapon;
import com.tw.arena.weapon.property.WeaponProperty;

public class PoisonSword extends BaseWeapon implements MiddleWeapon {

    public PoisonSword(String name, int damage, WeaponProperty property) {
        super(name, damage, property);
    }
}
