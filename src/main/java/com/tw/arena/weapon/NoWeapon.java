package com.tw.arena.weapon;

/**
 * Date: 2015/11/29
 * Time: 19:51
 *
 * @author Rascal
 */
public class NoWeapon implements Weapon {

    private static NoWeapon INSTANCE = new NoWeapon();

    private NoWeapon() {
    }

    public static NoWeapon getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getDamage() {
        return 0;
    }
}
