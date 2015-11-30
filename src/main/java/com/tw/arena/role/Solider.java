package com.tw.arena.role;

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


    @Override
    public String getRoleType() {
        return "战士";
    }
}
