package com.tw.arena.role;


import com.tw.arena.armor.Armor;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.weapon.ShortOrMiddleWeapon;
import com.tw.arena.weapon.Weapon;
import com.tw.arena.weapon.small.ShortWeapon;

public class Assassin extends Player {

    public Assassin(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    public Assassin(String name, int blood, int damage, ShortOrMiddleWeapon weapon) {
        super(name, blood, damage, weapon);
    }

    public Assassin(String name, int blood, int damage, ShortWeapon weapon, Armor armor, AttackStatus attackStatus) {
        super(name, blood, damage, weapon, armor, attackStatus);
    }

    @Override
    public String getRoleType() {
        return "刺客";
    }

    @Override
    public void setWeapon(Weapon weapon) {
        if (!(weapon instanceof ShortOrMiddleWeapon)) {
            throw new RuntimeException("刺客不能装备非中短型武器");
        }
        if (!(weapon instanceof ShortWeapon)) {
            weapon.getWeaponProperty().setProbability(0.0f);
        }
        super.setWeapon(weapon);

    }
}
