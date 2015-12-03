package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.weapon.Weapon;


public class Normal extends Player {
    public Normal(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    @Override
    public String getRoleType() {
        return "普通人";
    }

    @Override
    public void setWeapon(Weapon weapon) {
        throw new RuntimeException("普通人不能装备武器");
    }

    @Override
    public void setArmor(Armor armor) {
        throw new RuntimeException("普通人不能装备防具");
    }
}
