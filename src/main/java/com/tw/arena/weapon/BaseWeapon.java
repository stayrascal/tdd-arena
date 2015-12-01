package com.tw.arena.weapon;

import com.tw.arena.weapon.property.NoWeaponProperty;
import com.tw.arena.weapon.property.WeaponProperty;

public abstract class BaseWeapon implements Weapon {

    private String name;

    private int damage;

    private WeaponProperty property;


    public BaseWeapon(String name, int damage) {
        this(name, damage, NoWeaponProperty.getInstance());
    }

    public BaseWeapon(String name, int damage, WeaponProperty property) {
        this.name = name;
        this.damage = damage;
        this.property = property;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public WeaponProperty getWeaponProperty() {
        return property;
    }
}
