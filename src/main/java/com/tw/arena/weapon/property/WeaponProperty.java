package com.tw.arena.weapon.property;

import com.tw.arena.role.Person;

public interface WeaponProperty {

    int getPropertyDamage();

    int getDelayTimes();

    float getProbability();

    void setProbability(float probability);

    String getPropertyDamageEffect(Person victim, float probability);

    String getPropertyDamageDetail(Person victim, float probability);
}
