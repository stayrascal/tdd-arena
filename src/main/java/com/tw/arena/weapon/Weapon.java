package com.tw.arena.weapon;

import com.tw.arena.weapon.property.WeaponProperty;


public interface Weapon {

    String getName();

    int getDamage();

    WeaponProperty getWeaponProperty();
}
