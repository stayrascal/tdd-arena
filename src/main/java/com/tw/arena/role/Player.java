package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.armor.NoArmor;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.attack.NoAttackStatus;
import com.tw.arena.weapon.NoWeapon;
import com.tw.arena.weapon.Weapon;

import java.util.Objects;

import static java.lang.String.format;

public abstract class Player implements Role {

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
    public int getDelay() {
        return delay;
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
    public boolean isReadly() {
        return getDelay() == 0;
    }

    @Override
    public void beDelay(int delayTimes) {
        this.delay += delayTimes;
    }

    @Override
    public void decreaseDelay(int delayTimes) {
        this.delay = delayTimes > this.delay ? 0 : this.delay - delayTimes;
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
    public String beAttacked(Role attacker, float probability) {
        attacker.setAttackStatus(attacker.getAttackStatus(), probability);
        int damage = blood(attacker.getDamage());
        this.blood -= damage;
        int nowBlood = this.blood;
        String attackStatusEffect = attacker.getAttackStatus().getStatusEffect(attacker, probability);
        String propertyDamageEffect = probability < attacker.getWeapon().getWeaponProperty().getProbability() ? attacker.getWeapon().getWeaponProperty().getPropertyDamageEffect(this) : "";
        String propertyDamageDetail = probability < attacker.getWeapon().getWeaponProperty().getProbability() ? attacker.getWeapon().getWeaponProperty().getPropertyDamageDetail(this) : "";
        attacker.cancelAttackStatus(attacker.getAttackStatus(), probability);
        return format("%s%s攻击了%s%s,%s%s受到了%d点伤害,%s%s剩余生命: %d%s",
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

    private int blood(int damage) {
        return damage > getDefense() ? damage - getDefense() : 0;
    }

    @Override
    public void beAttackedByWeaponEffect(int damage) {
        this.blood -= damage;
    }

    @Override
    public AttackStatus getAttackStatus() {
        return attackStatus;
    }

    @Override
    public void setAttackStatus(AttackStatus attackStatus, float probability) {
        if (attackStatus.getProbability() > probability) {
            this.attackStatus = attackStatus;
            this.damage *= attackStatus.getMultiple();
        }
    }

    @Override
    public void cancelAttackStatus(AttackStatus attackStatus, float probability) {
        if (attackStatus.getProbability() > probability) {
            //this.attackStatus = NoAttackStatus.getInstance();
            this.damage /= attackStatus.getMultiple();
        }
    }
}
