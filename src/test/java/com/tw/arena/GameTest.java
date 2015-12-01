package com.tw.arena;

import com.tw.arena.armor.Armor;
import com.tw.arena.armor.WoodShield;
import com.tw.arena.attack.AttackStatus;
import com.tw.arena.attack.Fury;
import com.tw.arena.role.Normal;
import com.tw.arena.role.Role;
import com.tw.arena.role.Solider;
import com.tw.arena.weapon.Cudgel;
import com.tw.arena.weapon.Hammer;
import com.tw.arena.weapon.PoisonSword;
import com.tw.arena.weapon.Weapon;
import com.tw.arena.weapon.property.Dizzy;
import com.tw.arena.weapon.property.Poison;
import com.tw.arena.weapon.property.WeaponProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Random;

import static org.mockito.Mockito.*;

/**
 * Date: 2015/11/29
 * Time: 22:27
 *
 * @author Rascal
 */
public class GameTest {

    private Game game;
    private Random random;
    private ConsolePrinter printer;
    private InOrder inOrder;

    @Before
    public void setUp() {
        printer = mock(ConsolePrinter.class);
        inOrder = inOrder(printer);

        random = mock(Random.class);
        when(random.nextFloat()).thenReturn(0.5f);
        game = new Game(printer, random);
    }

    @Test
    public void shouldReturnTheDetailOfBattleBetweenSoliderAndNormal() {
        Role solider = new Solider("张三", 20, 10, new Cudgel("优质木棒", 5), new WoodShield("草帽", 3));
        Role normal = new Normal("李四", 44, 5);

        game.battle(solider, normal);
        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了普通人李四,李四受到了15点伤害,李四剩余生命: 29");
        inOrder.verify(printer, times(1)).print("普通人李四攻击了装备了草帽的战士张三,张三受到了2点伤害,张三剩余生命: 18");
        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了普通人李四,李四受到了15点伤害,李四剩余生命: 14");
        inOrder.verify(printer, times(1)).print("普通人李四攻击了装备了草帽的战士张三,张三受到了2点伤害,张三剩余生命: 16");
        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了普通人李四,李四受到了15点伤害,李四剩余生命: -1");
        inOrder.verify(printer, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldReturnTheDetailOfBattleBetweenNormalAndNormal() {
        Role liSi = new Normal("李四", 20, 11);
        Role zhangSan = new Normal("张三", 20, 20);

        game.battle(liSi, zhangSan);

        inOrder.verify(printer, times(1)).print("普通人李四攻击了普通人张三,张三受到了11点伤害,张三剩余生命: 9");
        inOrder.verify(printer, times(1)).print("普通人张三攻击了普通人李四,李四受到了20点伤害,李四剩余生命: 0");
        inOrder.verify(printer, times(1)).print("普通人李四攻击了普通人张三,张三受到了11点伤害,张三剩余生命: -2");
        inOrder.verify(printer, times(1)).print("张三被打败了");
    }

    @Test
    public void shouldReturnTheDetailOfBattleBetweenSoliderAndSolider() {
        Weapon weapon = new Cudgel("优质木棒", 5);
        Armor armor = new WoodShield("木盾", 10);
        Role zhangSan = new Solider("张三", 20, 20, weapon, armor);
        Role liSi = new Solider("李四", 20, 20, weapon, armor);

        game.battle(zhangSan, liSi);

        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了装备了木盾的战士李四,李四受到了15点伤害,李四剩余生命: 5");
        inOrder.verify(printer, times(1)).print("战士李四用优质木棒攻击了装备了木盾的战士张三,张三受到了15点伤害,张三剩余生命: 5");
        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了装备了木盾的战士李四,李四受到了15点伤害,李四剩余生命: -10");
        inOrder.verify(printer, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldReturnTheDetailOfBattleBetweenSoliderEquipedPoisonSwordAndNormal() {
        WeaponProperty poison = new Poison(5, 0, 0.6f, "中毒了");
        Weapon sword = new PoisonSword("优质毒剑", 10, poison);
        Armor armor = new WoodShield("木盾", 10);
        Role solider = new Solider("张三", 20, 10, sword, armor);
        Role normal = new Normal("李四", 30, 5);

        game.battle(solider, normal);

        inOrder.verify(printer, times(1)).print("战士张三用优质毒剑攻击了普通人李四,李四受到了20点伤害,李四中毒了,李四剩余生命: 10李四受到了5点毒性伤害,李四剩余生命: 5");
        inOrder.verify(printer, times(1)).print("普通人李四攻击了装备了木盾的战士张三,张三受到了0点伤害,张三剩余生命: 20");
        inOrder.verify(printer, times(1)).print("战士张三用优质毒剑攻击了普通人李四,李四受到了20点伤害,李四中毒了,李四剩余生命: -15李四受到了5点毒性伤害,李四剩余生命: -20");
        inOrder.verify(printer, times(1)).print("李四被打败了");
    }

    @Test
    public void shoudReturnTheDetailOfBattleBetweenSoliderEquipDizzyHammerAndNormal() {
        WeaponProperty dizzy = new Dizzy(0, 2, 0.6f, "晕倒了");
        Weapon hammer = new Hammer("晕锤", 10, dizzy);
        Armor armor = new WoodShield("木盾", 10);
        Role solider = new Solider("张三", 20, 10, hammer, armor);
        Role normal = new Normal("李四", 30, 5);

        game.battle(solider, normal);

        inOrder.verify(printer, times(1)).print("战士张三用晕锤攻击了普通人李四,李四受到了20点伤害,李四晕倒了,李四剩余生命: 10李四晕倒了,无法攻击,眩晕还剩: 1轮");
        inOrder.verify(printer, times(1)).print("战士张三用晕锤攻击了普通人李四,李四受到了20点伤害,李四晕倒了,李四剩余生命: -10李四晕倒了,无法攻击,眩晕还剩: 2轮");
        inOrder.verify(printer, times(1)).print("李四被打败了");
    }

    @Test
    public void shoudCauseThreeTimesDamageWhenStruggleToBattle() {
        AttackStatus fury = new Fury("全力一击", 3, 0.6f);
        WeaponProperty dizzy = new Dizzy(0, 2, 0.6f, "晕倒了");
        Weapon hammer = new Hammer("晕锤", 10, dizzy);
        Armor armor = new WoodShield("木盾", 10);
        Role solider = new Solider("张三", 20, 10, hammer, armor, fury);
        Role normal = new Normal("李四", 30, 5);

        game.battle(solider, normal);

        inOrder.verify(printer, times(1)).print("战士张三用晕锤攻击了普通人李四,张三发动了全力一击,李四受到了60点伤害,李四晕倒了,李四剩余生命: -30李四晕倒了,无法攻击,眩晕还剩: 1轮");
    }
}
