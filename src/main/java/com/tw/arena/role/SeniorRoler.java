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

public class SeniorRoler extends Person {

    private Weapon weapon;
    private Armor armor;
    private AttackStatus attackStatus;

    public SeniorRoler(String name, int blood, int damage, int defense, int delay, Weapon weapon, Armor armor) {
        this(name, blood, damage, defense, delay, weapon, armor, NoAttackStatus.getInstance());
    }

    public SeniorRoler(String name, int blood, int damage, int defense, int delay) {
        this(name, blood, damage, defense, delay, NoWeapon.getInstance(), NoArmor.getInstance(), NoAttackStatus.getInstance());
    }

    public SeniorRoler(String name, int blood, int damage) {
        this(name, blood, damage, 0, 0, NoWeapon.getInstance(), NoArmor.getInstance(), NoAttackStatus.getInstance());
    }

    public SeniorRoler(String name, int blood, int damage, int defense, int delay, Weapon weapon) {
        this(name, blood, damage, defense, delay, weapon, NoArmor.getInstance(), NoAttackStatus.getInstance());
    }

    public SeniorRoler(String name, int blood, int damage, Weapon weapon) {
        this(name, blood, damage, 0, 0, weapon);
    }

    public SeniorRoler(String name, int blood, int damage, int defense, int delay, Armor armor) {
        this(name, blood, damage, defense, delay, NoWeapon.getInstance(), armor, NoAttackStatus.getInstance());
    }

    public SeniorRoler(String name, int blood, int damage, Weapon weapon, Armor armor, AttackStatus status) {
        this(name, blood, damage, 0, 0, weapon, armor, status);
    }

    public SeniorRoler(String name, int blood, int damage, Weapon weapon, Armor armor) {
        this(name, blood, damage, 0, 0, weapon, armor);
    }

    public SeniorRoler(String name, int blood, int damage, Armor armor) {
        this(name, blood, damage, 0, 0, NoWeapon.getInstance(), armor);
    }

    public SeniorRoler(String name, int blood, int damage, int defense, int delay, Weapon weapon, Armor armor, AttackStatus attackStatus) {

        super(name, blood, damage + weapon.getDamage(), defense + armor.getDefense(), delay);
        this.weapon = weapon;
        this.armor = armor;
        this.attackStatus = attackStatus;
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

    public String getAttackType() {
        return Objects.equals(getWeapon().getName(), "") ? "" : format(Constants.ATTACK_TYPE, getWeapon().getName());
    }

    public String getArmorType() {
        return Objects.equals(getArmor().getName(), "") ? "" : format(Constants.ARMOR_TYPE, getArmor().getName());
    }

    public String beAttacked(SeniorRoler seniorRoler, float probability) {
        int damage = getDamageByAttackStatus(seniorRoler, probability);
        int nowBlood = this.blood;
        String attackStatusEffect = seniorRoler.getAttackStatus().getStatusEffect(seniorRoler, probability);
        String propertyDamageEffect = seniorRoler.getWeapon().getWeaponProperty().getPropertyDamageEffect(this, probability);
        String propertyDamageDetail = seniorRoler.getWeapon().getWeaponProperty().getPropertyDamageDetail(this, probability);
        seniorRoler.cancelAttackStatus(seniorRoler.getAttackStatus(), probability);
        return format(Constants.BATTLE_DETAIL,
                seniorRoler.getRoleIdentity(),
                seniorRoler.getAttackType(),
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

    public String beAttacked(Person attacker, float probability) {
        if (attacker instanceof SeniorRoler) {
            return beAttacked((SeniorRoler) attacker, probability);
        } else {
            int damage = blood(attacker.getDamage());
            this.blood -= damage;
            return format(Constants.ATTACKED_BY_NORMAL_DETAIL,
                    attacker.getRoleIdentity(),
                    getArmorType(),
                    getRoleIdentity(),
                    getName(),
                    damage,
                    getName(),
                    getBlood());
        }
    }


}
