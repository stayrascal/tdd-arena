package com.tw.arena.weapon.property;

import com.tw.arena.role.Player;

public interface WeaponProperty {

    int getPropertyDamage();

    int getDelayTimes();

    float getProbability();

    void setProbability(float probability);

    String getPropertyDamageEffect(Player victim, float probability);

    String getPropertyDamageDetail(Player victim, float probability);
}
