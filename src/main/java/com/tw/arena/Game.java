package com.tw.arena;

import com.tw.arena.armor.Armor;
import com.tw.arena.armor.WoodShield;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.attack.Fury;
import com.tw.arena.role.Role;
import com.tw.arena.role.Solider;
import com.tw.arena.weapon.Hammer;
import com.tw.arena.weapon.PoisonSword;
import com.tw.arena.weapon.Weapon;
import com.tw.arena.weapon.property.Dizzy;
import com.tw.arena.weapon.property.Poison;
import com.tw.arena.weapon.property.WeaponProperty;

import java.util.Random;

public class Game {

    private ConsolePrinter printer;
    private Random random;

    public Game(ConsolePrinter printer, Random random) {
        this.printer = printer;
        this.random = random;
    }

    public static void main(String[] args) {
        /*WeaponProperty dizzy = new Dizzy(0, 2, 0.35f, "晕倒了");
        Weapon hammer = new Hammer("晕锤", 10, dizzy);
        Armor armor = new WoodShield("木盾", 10);
        Role solider = new Solider("张三", 20, 10, hammer, armor);
        Role normal = new Normal("李四", 230, 5);*/

        AttackStatus fury = new Fury("全力一击", 3, 0.5f);
        WeaponProperty dizzy = new Dizzy(0, 2, 0.4f, "晕倒了");
        Weapon hammer = new Hammer("晕锤", 10, dizzy);
        Armor armor = new WoodShield("木盾", 10);
        Role solider = new Solider("张三", 100, 12, hammer, armor, fury);

        WeaponProperty poison = new Poison(5, 0, 0.5f, "中毒了");
        Weapon poisonSword = new PoisonSword("毒剑", 10, poison);
        Role normal = new Solider("李四", 2000, 10, poisonSword);
        Game game = new Game(new ConsolePrinter(), new Random());
        game.battle(solider, normal);
    }

    public void battle(Role playerA, Role palyerB) {
        Role attacker = playerA;
        Role victim = palyerB;
        Role loser = attacker;
        while (attacker.isAlive()) {
            if (attacker.isReadly()) {
                printer.print(victim.beAttacked(attacker, random.nextFloat()));
            } else {
                attacker.decreaseDelay(1);
            }
            loser = victim;
            victim = attacker;
            attacker = loser;
        }

        printer.print(String.format("%s被打败了", loser.getName()));
    }
}
