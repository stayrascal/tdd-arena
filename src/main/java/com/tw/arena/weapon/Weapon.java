package com.tw.arena.weapon;

import com.tw.arena.weapon.property.WeaponProperty;

/**
 * Date: 2015/11/29
 * Time: 18:10
 *
 * @author Rascal
 */
public interface Weapon {

    String getName();

    int getDamage();

    WeaponProperty getWeaponProperty();
}
