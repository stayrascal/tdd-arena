package com.tw.arena.attack;


import com.tw.arena.role.Role;
import com.tw.arena.role.Solider;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AttackStatusTest {

    @Test
    public void shouldReturnCorrectInfoAboutFury() {
        AttackStatus fury = new Fury("全力一击", 3, 0.6f);
        Role solider = new Solider("张三", 100, 10);

        assertThat(fury.getDescribe(), is("全力一击"));
        assertThat(fury.getMultiple(), is(3));
        assertThat(fury.getProbability(), is(0.6f));
        assertThat(fury.getStatusEffect(solider, 0.5f), is("张三发动了全力一击,"));
    }

    @Test
    public void shouldReturnCorrectInofAboutNoAttackStatus() {
        AttackStatus noAttackStatus = NoAttackStatus.getInstance();
        Role solider = new Solider("张三", 100, 10);

        assertThat(noAttackStatus.getProbability(), is(0f));
        assertThat(noAttackStatus.getDescribe(), is(""));
        assertThat(noAttackStatus.getMultiple(), is(1));
        assertThat(noAttackStatus.getStatusEffect(solider, 0.5f), is(""));
    }
}
