package com.tw.arena.attack;


import com.tw.arena.role.Role;

public interface AttackStatus {

    String getDescribe();

    int getMultiple();

    float getProbability();

    String getStatusEffect(Role attacker);
}
