package com.tw.arena;

import com.tw.arena.role.Normal;
import com.tw.arena.role.Role;
import com.tw.arena.role.Solider;
import com.tw.arena.weapon.Cudgel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

/**
 * Date: 2015/11/29
 * Time: 22:27
 *
 * @author Rascal
 */
public class GameTest {

    private Game game;
    private ConsolePrinter printer;
    private InOrder inOrder;

    @Before
    public void setUp(){
        printer = mock(ConsolePrinter.class);
        inOrder = inOrder(printer);
        game = new Game(printer);
    }

    @Test
    public void shouldReturnTheDetailOfBattleAboutZhangSanAndLiSi(){
        Role zhangSan = new Solider("张三", 20, 10, new Cudgel("优质木棒", 5));
        Role liSi = new Normal("李四", 44, 5);

        game.battle(zhangSan, liSi);
        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了普通人李四,李四受到了15点伤害,李四剩余生命: 29");
        inOrder.verify(printer, times(1)).print("普通人李四攻击了战士张三,张三受到了5点伤害,张三剩余生命: 15");
        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了普通人李四,李四受到了15点伤害,李四剩余生命: 14");
        inOrder.verify(printer, times(1)).print("普通人李四攻击了战士张三,张三受到了5点伤害,张三剩余生命: 10");
        inOrder.verify(printer, times(1)).print("战士张三用优质木棒攻击了普通人李四,李四受到了15点伤害,李四剩余生命: -1");
        inOrder.verify(printer, times(1)).print("李四被打败了");
    }
}
