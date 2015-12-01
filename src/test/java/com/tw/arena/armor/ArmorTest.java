package com.tw.arena.armor;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArmorTest {

    @Test
    public void shouldReturnCorrectInfoAboutWoodShield() {
        Armor armor = new WoodShield("木盾", 10);


        assertThat(armor.getName(), is("木盾"));
        assertThat(armor.getDefense(), is(10));
    }

    @Test
    public void shouldReturnCorrectInfoAboutNoArmor() {
        Armor armor = NoArmor.getInstance();

        assertThat(armor.getName(), is(""));
        assertThat(armor.getDefense(), is(0));
    }
}
