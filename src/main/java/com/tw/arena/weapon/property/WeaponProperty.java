package com.tw.arena.weapon.property;

import com.tw.arena.role.Role;

public interface WeaponProperty {

    int getPropertyDamage();

    int getDelayTimes();

    float getProbability();

    void setProbability(float probability);

    String getPropertyDamageEffect(Role victim);

    String getPropertyDamageDetail(Role victim);
}
