package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.weapon.Weapon;

public interface Role {

    String getName();

    int getBlood();

    int getDamage();

    int getDefense();

    Weapon getWeapon();

    Armor getArmor();

    String getRoleType();

    String getRoleIdentity();

    String getAttackType();

    String getArmorType();

    String beAttacked(Role attacker);

    boolean isAlive();

}
