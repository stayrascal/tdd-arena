package com.tw.arena.attack;


import com.tw.arena.role.Player;

public interface AttackStatus {

    String getDescribe();

    int getMultiple();

    float getProbability();

    String getStatusEffect(Player attacker, float probability);
}
