package com.tw.arena.weapon;


import com.tw.arena.weapon.property.NoWeaponProperty;
import com.tw.arena.weapon.property.Poison;
import com.tw.arena.weapon.property.WeaponProperty;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeaponTest {

    @Test
    public void shouldReturnCorrectInfoAboutPoisonSword() {
        WeaponProperty poison = new Poison(5, 0, 0.6f, "中毒了");
        Weapon poisonSword = new PoisonSword("毒剑", 10, poison);

        assertThat(poisonSword.getName(), is("毒剑"));
        assertThat(poisonSword.getDamage(), is(10));
        assertThat(poisonSword.getWeaponProperty(), is(poison));
    }

    @Test
    public void shouldReturnCorrectInfoAboutCudgel() {
        Weapon weapon = new Cudgel("优质木棒", 5);

        assertThat(weapon.getName(), is("优质木棒"));
        assertThat(weapon.getDamage(), is(5));
        assertThat(weapon.getWeaponProperty(), is(NoWeaponProperty.getInstance()));
    }

    @Test
    public void shouldReturnCorrectInfoAboutEMeiStab() {
        Weapon weapon = new Cudgel("峨眉刺", 5);

        assertThat(weapon.getName(), is("峨眉刺"));
        assertThat(weapon.getDamage(), is(5));
        assertThat(weapon.getWeaponProperty(), is(NoWeaponProperty.getInstance()));
    }

    @Test
    public void shouldReturnCorrectInfoAboutNoWeapon() {
        Weapon noWeapon = NoWeapon.getInstance();

        assertThat(noWeapon.getName(), is(""));
        assertThat(noWeapon.getDamage(), is(0));
        assertThat(noWeapon.getWeaponProperty(), is(NoWeaponProperty.getInstance()));
    }

}
