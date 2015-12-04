package com.tw.arena.role;

import com.tw.arena.Constants;
import com.tw.arena.armor.Armor;
import com.tw.arena.armor.NoArmor;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.attack.NoAttackStatus;
import com.tw.arena.weapon.Weapon;
import com.tw.arena.weapon.base.NoWeapon;

import java.util.Objects;

import static java.lang.String.format;

public abstract class Player {

    private String name;

    private int blood;

    private int damage;

    private int defense;

    private int delay;

    private Weapon weapon;

    private Armor armor;

    private AttackStatus attackStatus;

    private Player(String name, int blood, int damage, int defense, int delay, Weapon weapon, Armor armor, AttackStatus attackStatus) {
        this.name = name;
        this.blood = blood;
        this.damage = damage + weapon.getDamage();
        this.defense = defense + armor.getDefense();
        this.delay = delay;
        this.weapon = weapon;
        this.armor = armor;
        this.attackStatus = attackStatus;
    }

    public Player(String name, int blood, int damage) {
        this(name, blood, damage, 0, 0, NoWeapon.getInstance(), NoArmor.getInstance(), NoAttackStatus.getInstance());
    }

    public Player(String name, int blood, int damage, Weapon weapon) {
        this(name, blood, damage, 0, 0, weapon, NoArmor.getInstance(), NoAttackStatus.getInstance());
    }

    public Player(String name, int blood, int damage, Armor armor) {
        this(name, blood, damage, 0, 0, NoWeapon.getInstance(), armor, NoAttackStatus.getInstance());
    }

    public Player(String name, int blood, int damage, Weapon weapon, Armor armor) {
        this(name, blood, damage, 0, 0, weapon, armor, NoAttackStatus.getInstance());
    }

    public Player(String name, int blood, int damage, Weapon weapon, Armor armor, AttackStatus attackStatus) {
        this(name, blood, damage, 0, 0, weapon, armor, attackStatus);
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public String getRoleIdentity() {
        return format("%s%s", getRoleType(), getName());
    }

    public boolean isAlive() {
        return getBlood() >= 0;
    }

    public boolean isReadly() {
        return getDelay() == 0;
    }

    public void beDelay(int delayTimes) {
        this.delay += delayTimes;
    }

    public void decreaseDelay(int delayTimes) {
        this.delay = delayTimes > this.delay ? 0 : this.delay - delayTimes;
    }

    public String getAttackType() {
        return Objects.equals(getWeapon().getName(), "") ? "" : format(Constants.ATTACK_TYPE, getWeapon().getName());
    }

    public String getArmorType() {
        return Objects.equals(getArmor().getName(), "") ? "" : format(Constants.ARMOR_TYPE, getArmor().getName());
    }

    public String beAttacked(Player attacker, float probability) {
        int damage = getDamageByAttackStatus(attacker, probability);
        int nowBlood = this.blood;
        String attackStatusEffect = attacker.getAttackStatus().getStatusEffect(attacker, probability);
        String propertyDamageEffect = attacker.getWeapon().getWeaponProperty().getPropertyDamageEffect(this, probability);
        String propertyDamageDetail = attacker.getWeapon().getWeaponProperty().getPropertyDamageDetail(this, probability);
        attacker.cancelAttackStatus(attacker.getAttackStatus(), probability);
        return format(Constants.BATTLE_DETAIL,
                attacker.getRoleIdentity(),
                attacker.getAttackType(),
                getArmorType(),
                getRoleIdentity(),
                attackStatusEffect,
                getName(),
                damage,
                propertyDamageEffect,
                getName(),
                nowBlood,
                propertyDamageDetail);
    }

    private int getDamageByAttackStatus(Player attacker, float probability) {
        attacker.setAttackStatus(attacker.getAttackStatus(), probability);
        int damage = blood(attacker.getDamage());
        this.blood -= damage;
        return damage;
    }

    private int blood(int damage) {
        return damage > getDefense() ? damage - getDefense() : 0;
    }

    public void beAttackedByWeaponEffect(int damage) {
        this.blood -= damage;
    }

    public AttackStatus getAttackStatus() {
        return attackStatus;
    }

    public void setAttackStatus(AttackStatus attackStatus, float probability) {
        if (attackStatus.getProbability() > probability) {
            this.attackStatus = attackStatus;
            this.damage *= attackStatus.getMultiple();
        }
    }

    public void cancelAttackStatus(AttackStatus attackStatus, float probability) {
        if (attackStatus.getProbability() > probability) {
            this.damage /= attackStatus.getMultiple();
        }
    }

    abstract String getRoleType();
}
