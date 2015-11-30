package com.tw.arena.role;

import com.tw.arena.weapon.NoWeapon;
import com.tw.arena.weapon.Weapon;

import java.util.Objects;

import static java.lang.String.format;

/**
 * Date: 2015/11/29
 * Time: 18:13
 *
 * @author Rascal
 */
public abstract class Player implements Role {

    protected String name;

    protected int blood;

    protected int damage;

    protected Weapon weapon;

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
        this.weapon = NoWeapon.getInstance();
    }

    public Player(String name, int blood, int damage, Weapon weapon) {
        this.name = name;
        this.blood = blood;
        this.damage = damage + weapon.getDamage();
        this.weapon = weapon;
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
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public boolean isAlive() {
        return getBlood() >= 0;
    }

    @Override
    public String getRoleIdentity() {
        return format("%s%s", getRoleType(), getName());
    }

    @Override
    public String getAttackType() {
        return Objects.equals(getWeapon().getName(), "") ? "" : format("用%s", getWeapon().getName());
    }

    @Override
    public String beAttacked(Role attacker) {
        this.blood -= attacker.getDamage();
        return format("%s%s攻击了%s,%s受到了%d点伤害,%s剩余生命: %d", attacker.getRoleIdentity(), attacker.getAttackType(),
                getRoleIdentity(), getName(), attacker.getDamage(), getName(), getBlood());
    }
}
