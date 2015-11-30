package com.tw.arena;

import com.tw.arena.role.Role;

/**
 * Date: 2015/11/29
 * Time: 22:33
 *
 * @author Rascal
 */
public class Game {

    private ConsolePrinter printer;

    public Game(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void battle(Role playerA, Role palyerB) {
        Role attacker = playerA;
        Role victim = palyerB;
        Role loser = attacker;
        while (attacker.isAlive()){
            printer.print(victim.beAttacked(attacker));
            loser = victim;
            victim = attacker;
            attacker = loser;
        }
        printer.print(String.format("%s被打败了", loser.getName()));
    }
}
