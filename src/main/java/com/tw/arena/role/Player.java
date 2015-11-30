package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.armor.NoArmor;
import com.tw.arena.weapon.NoWeapon;
import com.tw.arena.weapon.Weapon;

import java.util.Objects;

import static java.lang.String.format;

public abstract class Player implements Role {

    private String name;

    private int blood;

    private int damage;

    private int defense;

    private Weapon weapon;

    private Armor armor;

    private Player(String name, int blood, int damage, int defense, Weapon weapon, Armor armor) {
        this.name = name;
        this.blood = blood;
        this.damage = damage + weapon.getDamage();
        this.defense = defense + armor.getDefense();
        this.weapon = weapon;
        this.armor = armor;
    }

    public Player(String name, int blood, int damage) {
        this(name, blood, damage, 0, NoWeapon.getInstance(), NoArmor.getInstance());
    }

    public Player(String name, int blood, int damage, Weapon weapon) {
        this(name, blood, damage, 0, weapon, NoArmor.getInstance());
    }

    public Player(String name, int blood, int damage, Armor armor) {
        this(name, blood, damage, 0, NoWeapon.getInstance(), armor);
    }

    public Player(String name, int blood, int damage, Weapon weapon, Armor armor) {
        this(name, blood, damage, 0, weapon, armor);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBlood() {
        return blood;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public Armor getArmor() {
        return armor;
    }

    @Override
    public String getRoleIdentity() {
        return format("%s%s", getRoleType(), getName());
    }

    @Override
    public boolean isAlive() {
        return getBlood() >= 0;
    }

    @Override
    public String getAttackType() {
        return Objects.equals(getWeapon().getName(), "") ? "" : format("用%s", getWeapon().getName());
    }

    @Override
    public String getArmorType() {
        return Objects.equals(getArmor().getName(), "") ? "" : format("装备了%s的", getArmor().getName());
    }

    @Override
    public String beAttacked(Role attacker) {
        this.blood -= blood(attacker.getDamage());
        return format("%s%s攻击了%s%s,%s受到了%d点伤害,%s剩余生命: %d", attacker.getRoleIdentity(), attacker.getAttackType(),
                getArmorType(), getRoleIdentity(), getName(), blood(attacker.getDamage()), getName(), getBlood());
    }

    private int blood(int damage) {
        return damage > getDefense() ? damage - getDefense() : 0;
    }
}
