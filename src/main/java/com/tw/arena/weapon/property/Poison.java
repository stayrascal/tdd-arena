package com.tw.arena.weapon.property;

import com.tw.arena.Constants;
import com.tw.arena.role.Role;

public class Poison extends BaseWeaponProperty {


    public Poison(int damage, int delayTime, float probability, String effect) {
        super(damage, delayTime, probability, effect);
    }

    @Override
    public String getPropertyDamageDetail(Role victim, float probability) {
        if (probability < getProbability()) {
            victim.beAttackedByWeaponEffect(getPropertyDamage());
            return String.format(Constants.POISON_PROPERTY_EFFECT, victim.getName(),
                    getPropertyDamage(), victim.getName(), victim.getBlood());
        }

        return "";
    }
}
