package com.tw.arena;


import com.tw.arena.armor.Armor;
import com.tw.arena.armor.WoodShield;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.attack.Fury;
import com.tw.arena.role.Assassin;
import com.tw.arena.role.Knight;
import com.tw.arena.role.Role;
import com.tw.arena.weapon.big.LongWeapon;
import com.tw.arena.weapon.big.Spear;
import com.tw.arena.weapon.property.Dizzy;
import com.tw.arena.weapon.property.Poison;
import com.tw.arena.weapon.property.WeaponProperty;
import com.tw.arena.weapon.small.EMeiStab;
import com.tw.arena.weapon.small.ShortWeapon;

import java.util.Random;

public class Arena {

    public static void main(String[] args) {

        Game game = new Game(new ConsolePrinter(), new Random());

        /*AttackStatus fury = new Fury("全力一击", 3, 0.5f);
        WeaponProperty dizzy = new Dizzy(0, 2, 0.4f, "晕倒了");
        MiddleWeapon hammer = new Hammer("晕锤", 10, dizzy);
        Armor armor = new WoodShield("木盾", 10);
        Role solider = new Solider("张三", 100, 12, hammer, armor, fury);

        WeaponProperty poison = new Poison(5, 0, 0.5f, "中毒了");
        MiddleWeapon poisonSword = new PoisonSword("毒剑", 10, poison);
        Role normal = new Solider("李四", 2000, 10, poisonSword);

        game.battle(solider, normal);*/


        AttackStatus fury = new Fury("全力一击", 3, 0.5f);

        Armor armor = new WoodShield("黑皇杖", 10);
        WeaponProperty dizzy = new Dizzy(0, 2, 0.4f, "晕倒了");
        LongWeapon spear = new Spear("深渊之刃", 20, dizzy);
        Role knight = new Knight("龙", 1000, 20, spear, armor);

        WeaponProperty poison = new Poison(5, 0, 0.5f, "中毒了");
        ShortWeapon eMeiStab = new EMeiStab("蝴蝶", 20, poison);
        Armor armour = new WoodShield("强袭胸甲", 10);
        Role assassin = new Assassin("幻影", 2000, 20, eMeiStab, armour, fury);

        game.battle(knight, assassin);
    }

}
