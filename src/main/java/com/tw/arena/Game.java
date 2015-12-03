package com.tw.arena;

import com.tw.arena.role.Role;

import java.util.Random;

public class Game {

    private ConsolePrinter printer;
    private Random random;

    public Game(ConsolePrinter printer, Random random) {
        this.printer = printer;
        this.random = random;
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
