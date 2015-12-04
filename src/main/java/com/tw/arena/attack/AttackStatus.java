package com.tw.arena.attack;


import com.tw.arena.role.SeniorRoler;

public interface AttackStatus {

    String getDescribe();

    int getMultiple();

    float getProbability();

    String getStatusEffect(SeniorRoler attacker, float probability);
}
