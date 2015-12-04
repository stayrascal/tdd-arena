package com.tw.arena.role;


import static java.lang.String.format;

public abstract class Roler {

    private String name;
    private int blood;
    private int damage;
    private int defense;
    private int delay;


    public Roler(String name, int blood, int damage, int defense, int delay) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
        this.defense = defense;
        this.delay = delay;
    }

    public Roler(String name, int blood, int damage) {
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

    public boolean isAlive() {
        return getBlood() >= 0;
    }

    public boolean isReadly() {
        return getDelay() == 0;
    }

    public String getRoleIdentity() {
        return format("%s%s", getRoleType(), getName());
    }

    public void beDelay(int delayTimes) {
        this.delay += delayTimes;
    }

    public void decreaseDelay(int delayTimes) {
        this.delay = delayTimes > this.delay ? 0 : this.delay - delayTimes;
    }

    protected int blood(int damage) {
        return damage > getDefense() ? damage - getDefense() : 0;
    }

    abstract String beAttacked(Roler attacker, float probability);

    abstract String getRoleType();

    abstract String getArmorType();
}
