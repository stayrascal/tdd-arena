package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.armor.NoArmor;
import com.tw.arena.armor.WoodShield;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.attack.Fury;
import com.tw.arena.attack.NoAttackStatus;
import com.tw.arena.weapon.Weapon;
import com.tw.arena.weapon.base.NoWeapon;
import com.tw.arena.weapon.big.Spear;
import com.tw.arena.weapon.middle.Cudgel;
import com.tw.arena.weapon.middle.Hammer;
import com.tw.arena.weapon.middle.MiddleWeapon;
import com.tw.arena.weapon.middle.PoisonSword;
import com.tw.arena.weapon.property.Dizzy;
import com.tw.arena.weapon.property.Poison;
import com.tw.arena.weapon.property.WeaponProperty;
import com.tw.arena.weapon.small.EMeiStab;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RoleTest {

    private Random random;

    @Before
    public void setUp() {
        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.5f);
    }

    @Test
    public void shoudReturnCorrectBasicInfoAboutNormal() {
        Person person = new Person("张三", 100, 5);

        assertThat(person.getName(), is("张三"));
        assertThat(person.getBlood(), is(100));
        assertThat(person.getDamage(), is(5));
        assertThat(person.getDefense(), is(0));
        assertThat(person.getDelay(), is(0));
        assertThat(person.getRoleType(), is("普通人"));
        assertThat(person.getRoleIdentity(), is("普通人张三"));
        assertThat(person.isAlive(), is(true));
        assertThat(person.isReadly(), is(true));
    }

    @Test
    public void shouldGetCorrectInfoAboutSoliderEquipWeaponButWithoutArmor() {
        MiddleWeapon weapon = new Cudgel("优质木棒", 5);
        Solider solider = new Solider("张三", 100, 10, weapon);

        assertThat(solider.getName(), is("张三"));
        assertThat(solider.getBlood(), is(100));
        assertThat(solider.getDamage(), is(15));
        assertThat(solider.getDefense(), is(0));
        assertThat(solider.getDelay(), is(0));
        assertThat(solider.getWeapon(), is(weapon));
        assertThat(solider.getAttackStatus(), is(NoAttackStatus.getInstance()));
        assertThat(solider.getRoleType(), is("战士"));
        assertThat(solider.getRoleIdentity(), is("战士张三"));
        assertThat(solider.getAttackType(), is("用优质木棒"));
        assertThat(solider.getArmor(), is(NoArmor.getInstance()));
        assertThat(solider.isReadly(), is(true));
        assertThat(solider.isAlive(), is(true));
    }


    @Test
    public void shouldGetCorrectInfoAboutSoliderEquipArmorButWithoutWeapon() {
        Armor armor = new WoodShield("木盾", 5);
        Solider solider = new Solider("张三", 100, 10, armor);

        assertThat(solider.getName(), is("张三"));
        assertThat(solider.getBlood(), is(100));
        assertThat(solider.getDamage(), is(10));
        assertThat(solider.getDefense(), is(5));
        assertThat(solider.getDelay(), is(0));
        assertThat(solider.getWeapon(), is(NoWeapon.getInstance()));
        assertThat(solider.getAttackStatus(), is(NoAttackStatus.getInstance()));
        assertThat(solider.getRoleType(), is("战士"));
        assertThat(solider.getRoleIdentity(), is("战士张三"));
        assertThat(solider.getAttackType(), is(""));
        assertThat(solider.getArmor(), is(armor));
        assertThat(solider.isAlive(), is(true));
        assertThat(solider.isReadly(), is(true));
    }

    @Test
    public void shouldRsturnCorrectInfoAboutRoleEquipArmorAndWeapon() {
        MiddleWeapon weapon = new Cudgel("优质木棒", 5);
        Armor armor = new WoodShield("木盾", 5);
        Solider solider = new Solider("张三", 100, 10, weapon, armor);

        assertThat(solider.getName(), is("张三"));
        assertThat(solider.getBlood(), is(100));
        assertThat(solider.getDamage(), is(15));
        assertThat(solider.getDefense(), is(5));
        assertThat(solider.getDelay(), is(0));
        assertThat(solider.getWeapon(), is(weapon));
        assertThat(solider.getAttackStatus(), is(NoAttackStatus.getInstance()));
        assertThat(solider.getRoleType(), is("战士"));
        assertThat(solider.getRoleIdentity(), is("战士张三"));
        assertThat(solider.getAttackType(), is("用优质木棒"));
        assertThat(solider.getArmor(), is(armor));
        assertThat(solider.isAlive(), is(true));
        assertThat(solider.isReadly(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenBloodisLessThan0() {
        Solider solider = new Solider("张三", -1, 10);

        assertThat(solider.isAlive(), is(false));
    }

    @Test
    public void shouldReturnTrueWhenBloodIs0(){
        Solider solder = new Solider("张三", 0, 10);

        assertThat(solder.isAlive(), is(true));
    }

    @Test
    public void shoudReturnDetailAboutZhangSanAttackLiSi() {
        MiddleWeapon weapon = new Cudgel("优质木棒", 5);
        Solider solider = new Solider("张三", 100, 3, weapon);
        Person person = new Person("李四", 20, 2);

        String detail = person.beAttacked(solider, random.nextFloat());
        assertThat(detail, is("战士张三用优质木棒攻击了普通人李四,李四受到了8点伤害,李四剩余生命: 12"));
    }

    @Test
    public void shouldReturn0WhenDamageLessThanDefense() {
        Armor armor = new WoodShield("木盾", 5);
        Solider solider = new Solider("张三", 100, 3, armor);
        Person person = new Person("李四", 20, 2);

        String detail = solider.beAttacked(person, random.nextFloat());

        assertThat(detail, is("普通人李四攻击了装备了木盾的战士张三,张三受到了0点伤害,张三剩余生命: 100"));
    }

    @Test
    public void shouldReturnCorrectDamageWhenDamageMoreThanDefense() {
        Armor armor = new WoodShield("木盾", 5);
        Solider solider = new Solider("张三", 100, 3, armor);
        Person person = new Person("李四", 20, 7);

        String detail = solider.beAttacked(person, random.nextFloat());

        assertThat(detail, is("普通人李四攻击了装备了木盾的战士张三,张三受到了2点伤害,张三剩余生命: 98"));
    }

    @Test
    public void shoudReturnWeaponInfoOfPlayerWhoEquipPoisonSword() {
        WeaponProperty poison = new Poison(2, 0, 0.6f, "中毒了");
        MiddleWeapon poisonSword = new PoisonSword("优质毒剑", 10, poison);
        Solider solider = new Solider("张三", 100, 10, poisonSword);
        Person victim = new Person("李四", 100, 5);

        assertThat(solider.getWeapon().getWeaponProperty().getPropertyDamage(), is(2));
        assertThat(victim.getDelay(), is(0));
        assertThat(poison.getDelayTimes(), is(0));
        assertThat(poisonSword.getWeaponProperty().getPropertyDamageEffect(victim, random.nextFloat()), is("李四中毒了,"));
        assertThat(poisonSword.getWeaponProperty().getPropertyDamageDetail(victim, random.nextFloat()), is("李四受到了2点毒性伤害,李四剩余生命: 98"));
    }

    @Test
    public void shoudReturnWeaponInfoOfPlayerWhoEquipHammer() {
        WeaponProperty dizzy = new Dizzy(0, 1, 0.6f, "晕倒了");
        MiddleWeapon dizzyHammer = new Hammer("晕锤", 10, dizzy);
        Solider solider = new Solider("张三", 100, 10, dizzyHammer);
        Person victim = new Person("李四", 100, 5);

        assertThat(solider.getWeapon().getWeaponProperty().getPropertyDamage(), is(0));
        assertThat(dizzy.getDelayTimes(), is(1));
        assertThat(victim.getDelay(), is(0));
        assertThat(dizzyHammer.getWeaponProperty().getPropertyDamageEffect(victim, random.nextFloat()), is("李四晕倒了,"));
        assertThat(victim.getDelay(), is(1));
        assertThat(dizzyHammer.getWeaponProperty().getPropertyDamageDetail(victim, random.nextFloat()), is("李四晕倒了,无法攻击,眩晕还剩: 0轮"));
    }

    @Test
    public void shouldReturnCorrectInfoWhenUseHammerAttackTwoTimes() {
        WeaponProperty dizzy = new Dizzy(0, 1, 0.6f, "晕倒了");
        MiddleWeapon dizzyHammer = new Hammer("晕锤", 10, dizzy);
        Solider solider = new Solider("张三", 100, 10, dizzyHammer);
        Person victim = new Person("李四", 100, 5);

        assertThat(solider.getWeapon().getWeaponProperty().getPropertyDamage(), is(0));
        assertThat(dizzy.getDelayTimes(), is(1));
        assertThat(victim.getDelay(), is(0));
        assertThat(dizzyHammer.getWeaponProperty().getPropertyDamageEffect(victim, random.nextFloat()), is("李四晕倒了,"));
        assertThat(victim.getDelay(), is(1));
        assertThat(dizzyHammer.getWeaponProperty().getPropertyDamageDetail(victim, random.nextFloat()), is("李四晕倒了,无法攻击,眩晕还剩: 0轮"));
        assertThat(dizzyHammer.getWeaponProperty().getPropertyDamageEffect(victim, random.nextFloat()), is("李四晕倒了,"));
        assertThat(victim.getDelay(), is(2));
        assertThat(dizzyHammer.getWeaponProperty().getPropertyDamageDetail(victim, random.nextFloat()), is("李四晕倒了,无法攻击,眩晕还剩: 1轮"));
    }

    @Test
    public void theDelayOfPlayerShouldBeRightAfterBeDelayedOrDecreaseDelay() {
        Person person = new Person("张三", 100, 10);
        person.beDelay(1);

        assertThat(person.getDelay(), is(1));

        person.decreaseDelay(10);

        assertThat(person.getDelay(), is(0));
    }

    @Test
    public void shouldReturnCorrectInfoWhenSoliderWithFuryStatus() {
        Solider solider = new Solider("张三", 100, 10);
        AttackStatus fury = new Fury("全力一击", 3, 0.6f);

        assertThat(solider.getAttackStatus(), is(NoAttackStatus.getInstance()));

        solider.setAttackStatus(fury, random.nextFloat());

        assertThat(solider.getAttackStatus(), is(fury));
        assertThat(solider.getDamage(), is(30));

        solider.cancelAttackStatus(fury, random.nextFloat());
        assertThat(solider.getDamage(), is(10));
    }

    @Test
    public void shouldLostBloodWhenDamageByWeaponEffect() {
        Solider solider = new Solider("张三", 100, 10);

        solider.beAttackedByWeaponEffect(10);

        assertThat(solider.getBlood(), is(90));
    }

    @Test(expected = RuntimeException.class)
    public void shouldReturnRunTimeExceptionWhenAssassinEquipLongWeapon() {
        Assassin assassin = new Assassin("刺客", 100, 10);
        Weapon spear = new Spear("长枪", 20);

        assassin.setWeapon(spear);
    }

    @Test(expected = RuntimeException.class)
    public void shouldReturnRunTimeExceptionWhenSoliderEquipLongWeapon() {
        Solider solider = new Solider("张三", 100, 10);
        Weapon spear = new Spear("长枪", 20);

        solider.setWeapon(spear);
    }

    @Test(expected = RuntimeException.class)
    public void shouldReturnRunTimeExceptionWhenSoliderEquipShortWeapon() {
        Solider solider = new Solider("张三", 100, 10);
        Weapon eMeiStab = new EMeiStab("峨眉刺", 12);

        solider.setWeapon(eMeiStab);
    }

    @Test(expected = RuntimeException.class)
    public void shouldReturnRunTimeExceptionWhenKnightEquipShortWeapon() {
        Knight knight = new Knight("张三", 100, 10);
        Weapon eMeiStab = new EMeiStab("峨眉刺", 12);

        knight.setWeapon(eMeiStab);
    }

    @Test
    public void theProbabilityOfWeaponPropertyshouldBe0WhenAssassinEquipMiddleWeapon() {
        Assassin assassin = new Assassin("刺客", 100, 10);
        WeaponProperty poison = new Poison(5, 0, 0.6f, "中毒了");
        Weapon poisonSword = new PoisonSword("毒剑", 10, poison);
        assassin.setWeapon(poisonSword);

        assertThat(assassin.getWeapon().getWeaponProperty().getProbability(), is(0f));
    }

    @Test
    public void theProbabilityOfWeaponPropertyshouldBeRightWhenAssassinEquipShortWeapon() {
        Assassin assassin = new Assassin("刺客", 100, 10);
        WeaponProperty poison = new Poison(5, 0, 0.6f, "中毒了");
        Weapon eMeiStab = new EMeiStab("峨眉刺", 12, poison);
        assassin.setWeapon(eMeiStab);

        assertThat(assassin.getWeapon().getWeaponProperty().getProbability(), is(0.6f));
    }

    @Test
    public void theProbabilityOfWeaponPropertyshouldBe0WhenKnightEquipMiddleWeapon() {
        Knight knight = new Knight("骑士", 100, 10);
        WeaponProperty poison = new Poison(5, 0, 0.6f, "中毒了");
        Weapon poisonSword = new PoisonSword("毒剑", 10, poison);
        knight.setWeapon(poisonSword);

        assertThat(knight.getWeapon().getWeaponProperty().getProbability(), is(0f));
    }

    @Test
    public void theProbabilityOfWeaponPropertyshouldBeRightWhenKnightEquipLongWeapon() {
        Knight knight = new Knight("骑士", 100, 10);
        WeaponProperty poison = new Poison(5, 0, 0.6f, "中毒了");
        Weapon spear = new Spear("毒枪", 10, poison);
        knight.setWeapon(spear);

        assertThat(knight.getWeapon().getWeaponProperty().getProbability(), is(0.6f));
    }



}
