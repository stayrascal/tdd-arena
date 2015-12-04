package com.tw.arena.attack;


import com.tw.arena.Constants;
import com.tw.arena.role.SeniorRoler;

public abstract class BaseAttackStatus implements AttackStatus {

    private String describe;
    private int multiple;
    private float probability;

    public BaseAttackStatus(String describe, int multiple, float probability) {
        this.describe = describe;
        this.multiple = multiple;
        this.probability = probability;
    }

    @Override
    public String getDescribe() {
        return describe;
    }

    @Override
    public float getProbability() {
        return probability;
    }

    @Override
    public int getMultiple() {
        return multiple;
    }

    @Override
    public String getStatusEffect(SeniorRoler attacker, float probability) {
        return probability < getProbability() ? String.format(Constants.ATTACK_STATUS_EFFECT, attacker.getName(), getDescribe()) : "";
    }
}
