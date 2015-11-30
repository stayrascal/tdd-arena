package com.tw.arena.role;

import com.tw.arena.weapon.Weapon;

/**
 * Date: 2015/11/29
 * Time: 18:04
 *
 * @author Rascal
 */
public interface Role {

    String getName();

    int getBlood();

    int getDamage();

    Weapon getWeapon();

    boolean isAlive();

    String getRoleType();

    String beAttacked(Role attacker);

    String getRoleIdentity();

    String getAttackType();

}
