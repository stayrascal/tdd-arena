package com.tw.arena.weapon;

/**
 * Date: 2015/11/29
 * Time: 19:15
 *
 * @author Rascal
 */
public abstract class BaseWeapon implements Weapon {

    protected String name;

    protected int damage;

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return name;
    }

    public BaseWeapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }
}
