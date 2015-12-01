package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.weapon.Weapon;

public interface Role {

    String getName();

    int getBlood();

    int getDamage();

    int getDefense();

    int getDelay();

    Weapon getWeapon();

    Armor getArmor();

    AttackStatus getAttackStatus();

    void setAttackStatus(AttackStatus status);

    String getRoleType();

    String getRoleIdentity();

    String getAttackType();

    String getArmorType();

    boolean isAlive();

    boolean isReadly();

    String beAttacked(Role attacker, float probability);

    void beAttackedByWeaponEffect(int damage);

    void beDelay(int delayTimes);

    void decreaseDelay(int delayTimes);

    void cancelAttackStatus(AttackStatus status);


}
