package com.tw.arena.attack;

import com.tw.arena.role.SeniorRoler;


public class NoAttackStatus implements AttackStatus {

    private static NoAttackStatus INSTANCE = new NoAttackStatus();

    private NoAttackStatus() {
    }

    public static NoAttackStatus getInstance() {
        return INSTANCE;
    }

    @Override
    public String getDescribe() {
        return "";
    }

    @Override
    public int getMultiple() {
        return 1;
    }

    @Override
    public float getProbability() {
        return 0;
    }

    @Override
    public String getStatusEffect(SeniorRoler attacker, float probability) {
        return "";
    }
}
