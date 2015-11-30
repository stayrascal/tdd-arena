package com.tw.arena.armor;

/**
 * Date: 2015/11/30
 * Time: 14:05
 *
 * @author Rascal
 */
public class NoArmor implements Armor {

    private static NoArmor INSTANCE = new NoArmor();

    private NoArmor() {
    }

    public static NoArmor getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getDefense() {
        return 0;
    }
}
