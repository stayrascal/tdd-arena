package com.tw.arena.weapon.property;

import com.tw.arena.role.Role;

public class NoWeaponProperty implements WeaponProperty {

    private static NoWeaponProperty INSTANCE = new NoWeaponProperty();

    private NoWeaponProperty() {
    }

    public static NoWeaponProperty getInstance() {
        return INSTANCE;
    }

    @Override
    public int getPropertyDamage() {
        return 0;
    }

    @Override
    public int getDelayTimes() {
        return 0;
    }

    @Override
    public float getProbability() {
        return 0;
    }

    @Override
    public String getPropertyDamageEffect(Role victim) {
        return "";
    }

    @Override
    public String getPropertyDamageDetail(Role victim) {
        return "";
    }
}
