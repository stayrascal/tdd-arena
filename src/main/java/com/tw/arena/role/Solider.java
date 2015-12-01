package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.weapon.Weapon;

/**
 * Date: 2015/11/29
 * Time: 18:08
 *
 * @author Rascal
 */
public class Solider extends Player {

    public Solider(String name, int blood, int damage, Weapon weapon) {
        super(name, blood, damage, weapon);
    }

    public Solider(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    public Solider(String name, int bllod, int damage, Weapon weapon, Armor armor) {
        super(name, bllod, damage, weapon, armor);
    }

    public Solider(String name, int blood, int damage, Armor armor) {
        super(name, blood, damage, armor);
    }

    public Solider(String name, int blood, int damage, Weapon weapon, Armor armor, AttackStatus attackStatus) {
        super(name, blood, damage, weapon, armor, attackStatus);
    }


    @Override
    public String getRoleType() {
        return "战士";
    }
}
