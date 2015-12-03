package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.weapon.MiddleWeapon;
import com.tw.arena.weapon.Weapon;
public class Solider extends Player {

    public Solider(String name, int blood, int damage, MiddleWeapon weapon) {
        super(name, blood, damage, weapon);
    }

    public Solider(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    public Solider(String name, int bllod, int damage, MiddleWeapon weapon, Armor armor) {
        super(name, bllod, damage, weapon, armor);
    }

    public Solider(String name, int blood, int damage, Armor armor) {
        super(name, blood, damage, armor);
    }

    public Solider(String name, int blood, int damage, MiddleWeapon weapon, Armor armor, AttackStatus attackStatus) {
        super(name, blood, damage, weapon, armor, attackStatus);
    }


    @Override
    public String getRoleType() {
        return "战士";
    }

    @Override
    public void setWeapon(Weapon weapon) {
        if (!(weapon instanceof MiddleWeapon)) {
            throw new RuntimeException("战士不能装备非中武器");
        }

        super.setWeapon(weapon);
    }
}
