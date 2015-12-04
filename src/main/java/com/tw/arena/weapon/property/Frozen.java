package com.tw.arena.weapon.property;

import com.tw.arena.Constants;
import com.tw.arena.role.Person;

public class Frozen extends BaseWeaponProperty {

    public Frozen(int damage, int delay, float probability, String describe) {
        super(damage, delay, probability, describe);
    }

    @Override
    public String getPropertyDamageDetail(Person victim, float probability) {
        return getProbability() > probability ? String.format(Constants.FROZEN_PROPERTY_EFFECT, victim.getName(), victim.getDelay() - 1) : "";
    }
}
