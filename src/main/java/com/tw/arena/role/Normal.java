package com.tw.arena.role;

/**
 * Date: 2015/11/29
 * Time: 19:47
 *
 * @author Rascal
 */
public class Normal extends Player {
    public Normal(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    @Override
    public String getRoleType() {
        return "普通人";
    }
}
