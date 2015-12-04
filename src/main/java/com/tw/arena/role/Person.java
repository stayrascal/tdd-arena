package com.tw.arena.role;

import com.tw.arena.Constants;

import static java.lang.String.format;

public class Person {

    protected String name;
    protected int blood;
    protected int damage;
    protected int defense;
    protected int delay;

    public Person(String name, int blood, int damage, int defense, int delay) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
        this.defense = defense;
        this.delay = delay;
    }

    public Person(String name, int blood, int damage) {
        this(name, blood, damage, 0, 0);
    }

    public String getName() {
        return name;
    }

    public int getBlood() {
        return blood;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getDelay() {
        return delay;
    }

    public void beDelay(int delayTimes) {
        this.delay += delayTimes;
    }

    public void decreaseDelay(int delayTimes) {
        this.delay = delayTimes > this.delay ? 0 : this.delay - delayTimes;
    }

    public boolean isAlive() {
        return getBlood() >= 0;
    }

    public boolean isReadly() {
        return getDelay() == 0;
    }

    public String getRoleType() {
        return "普通人";
    }

    public String getRoleIdentity() {
        return getRoleType() + getName();
    }

    protected int blood(int damage) {
        return damage > getDefense() ? damage - getDefense() : 0;
    }

    public void beAttackedByWeaponEffect(int damage) {
        this.blood -= damage;
    }

    protected int getDamageByAttackStatus(SeniorRoler attacker, float probability) {
        attacker.setAttackStatus(attacker.getAttackStatus(), probability);
        int damage = blood(attacker.getDamage());
        this.blood -= damage;
        return damage;
    }

    public String beAttacked(Person attacker, float probability) {
        if (attacker instanceof SeniorRoler) {
            SeniorRoler seniorRoler = (SeniorRoler) attacker;
            int damage = getDamageByAttackStatus(seniorRoler, probability);
            int nowBlood = this.blood;
            String attackStatusEffect = seniorRoler.getAttackStatus().getStatusEffect(seniorRoler, probability);
            String propertyDamageEffect = seniorRoler.getWeapon().getWeaponProperty().getPropertyDamageEffect(this, probability);
            String propertyDamageDetail = seniorRoler.getWeapon().getWeaponProperty().getPropertyDamageDetail(this, probability);
            seniorRoler.cancelAttackStatus(seniorRoler.getAttackStatus(), probability);
            return format(Constants.NORMAL_BEEN_ATTACKED_BY_SENIOR_DETAIL,
                    seniorRoler.getRoleIdentity(),
                    seniorRoler.getAttackType(),
                    getRoleIdentity(),
                    attackStatusEffect,
                    getName(),
                    damage,
                    propertyDamageEffect,
                    getName(),
                    nowBlood,
                    propertyDamageDetail);
        } else {
            int damage = blood(attacker.getDamage());
            this.blood -= damage;
            return format(Constants.NORMAL_BEEN_ATTACKED_BY_NORMAL_DETAIL,
                    attacker.getRoleIdentity(),
                    getRoleIdentity(),
                    getName(),
                    damage,
                    getName(),
                    getBlood());
        }
    }
}
