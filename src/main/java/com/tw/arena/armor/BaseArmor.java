package com.tw.arena.armor;

/**
 * Date: 2015/11/30
 * Time: 14:00
 *
 * @author Rascal
 */
public abstract class BaseArmor implements Armor {

    private String name;

    private int defense;

    public BaseArmor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDefense() {
        return defense;
    }
}
