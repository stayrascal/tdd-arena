package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.weapon.LongOrMiddleWeapon;
import com.tw.arena.weapon.Weapon;
import com.tw.arena.weapon.big.LongWeapon;

public class Knight extends Player {
    public Knight(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    public Knight(String name, int blood, int damage, LongWeapon weapon) {
        super(name, blood, damage, weapon);
    }

    public Knight(String name, int blood, int damage, LongWeapon weapon, Armor armor) {
        super(name, blood, damage, weapon, armor);
    }

    @Override
    public String getRoleType() {
        return "骑士";
    }

    @Override
    public void setWeapon(Weapon weapon) {
        if (!(weapon instanceof LongOrMiddleWeapon)) {
            throw new RuntimeException("骑士不能装备非中长型武器");
        }

        if (!(weapon instanceof LongWeapon)) {
            weapon.getWeaponProperty().setProbability(0.0f);
        }
        super.setWeapon(weapon);
    }
}
