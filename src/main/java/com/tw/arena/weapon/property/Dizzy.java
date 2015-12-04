package com.tw.arena.weapon.property;

import com.tw.arena.Constants;
import com.tw.arena.role.Player;

public class Dizzy extends BaseWeaponProperty {

    public Dizzy(int damage, int delayTime, float probability, String effect) {
        super(damage, delayTime, probability, effect);
    }


    @Override
    public String getPropertyDamageDetail(Player victim, float probability) {
        return getProbability() > probability ? String.format(Constants.DIZZY_PROPERTY_EFFECT, victim.getName(), victim.getDelay() - 1) : "";
    }
}
