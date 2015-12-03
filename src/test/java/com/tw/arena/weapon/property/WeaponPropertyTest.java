package com.tw.arena.weapon.property;


import com.tw.arena.role.Role;
import com.tw.arena.role.Solider;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeaponPropertyTest {

    @Test
    public void shouldReturnCorrectInfoAboutPosion() {
        WeaponProperty poison = new Poison(5, 0, 0.6f, "中毒了");
        Role victim = new Solider("张三", 100, 10);

        assertThat(poison.getProbability(), is(0.6f));
        assertThat(poison.getDelayTimes(), is(0));
        assertThat(poison.getPropertyDamage(), is(5));

        assertThat(victim.getDelay(), is(0));
        assertThat(poison.getPropertyDamageEffect(victim, 0.5f), is("张三中毒了,"));
        assertThat(victim.getDelay(), is(0));

        assertThat(poison.getPropertyDamageDetail(victim, 0.5f), is("张三受到了5点毒性伤害,张三剩余生命: 95"));
    }

    @Test
    public void shouldReturnCorrectInfoAboutDizzy() {
        WeaponProperty dizzy = new Dizzy(0, 2, 0.6f, "晕倒了");
        Role victim = new Solider("张三", 100, 10);

        assertThat(dizzy.getDelayTimes(), is(2));
        assertThat(dizzy.getPropertyDamage(), is(0));
        assertThat(dizzy.getProbability(), is(0.6f));

        assertThat(victim.getDelay(), is(0));
        assertThat(dizzy.getPropertyDamageEffect(victim, 0.5f), is("张三晕倒了,"));
        assertThat(victim.getDelay(), is(2));

        assertThat(dizzy.getPropertyDamageDetail(victim, 0.5f), is("张三晕倒了,无法攻击,眩晕还剩: 1轮"));
    }

    @Test
    public void shouldReturnCorrectInfoAboutFrozen() {
        WeaponProperty frozen = new Frozen(0, 1, 0.6f, "冻僵了");
        Role victim = new Solider("张三", 100, 10);

        assertThat(frozen.getDelayTimes(), is(1));
        assertThat(frozen.getPropertyDamage(), is(0));
        assertThat(frozen.getProbability(), is(0.6f));

        assertThat(victim.getDelay(), is(0));
        assertThat(frozen.getPropertyDamageEffect(victim, 0.5f), is("张三冻僵了,"));
        assertThat(victim.getDelay(), is(1));

        assertThat(frozen.getPropertyDamageDetail(victim, 0.5f), is("张三冻僵了,无法攻击,冰冻还剩: 0轮"));
    }

    @Test
    public void shouldReturnCorrectInfoAboutNoWeaponProperty() {
        WeaponProperty noWeaponProperty = NoWeaponProperty.getInstance();
        Role victim = new Solider("张三", 100, 10);

        assertThat(noWeaponProperty.getPropertyDamage(), is(0));
        assertThat(noWeaponProperty.getProbability(), is(0f));
        assertThat(noWeaponProperty.getDelayTimes(), is(0));
        assertThat(noWeaponProperty.getPropertyDamageEffect(victim, 0.5f), is(""));
        assertThat(noWeaponProperty.getPropertyDamageDetail(victim, 0.5f), is(""));
    }
}
