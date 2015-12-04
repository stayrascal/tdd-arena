package com.tw.arena.weapon.property;

import com.tw.arena.role.Person;


public abstract class BaseWeaponProperty implements WeaponProperty {

    private int propertyDamage;

    private int delayTimes;

    private float probability;

    private String propertyDamageEffect;

    public BaseWeaponProperty(int propertyDamage, int delayTimes, float probability, String propertyDamageEffect) {
        this.propertyDamage = propertyDamage;
        this.delayTimes = delayTimes;
        this.probability = probability;
        this.propertyDamageEffect = propertyDamageEffect;
    }

    public BaseWeaponProperty(int propertyDamage, int delayTimes, String propertyDamageEffect) {
        this(propertyDamage, delayTimes, 0, propertyDamageEffect);
    }

    @Override
    public int getPropertyDamage() {
        return propertyDamage;
    }

    @Override
    public int getDelayTimes() {
        return delayTimes;
    }

    @Override
    public float getProbability() {
        return probability;
    }

    @Override
    public void setProbability(float probability) {
        this.probability = probability;
    }

    @Override
    public String getPropertyDamageEffect(Person victim, float probability) {
        if (getProbability() > probability) {
            victim.beDelay(getDelayTimes());
            return victim.getName() + propertyDamageEffect + ",";
        }
        return "";
    }
}
