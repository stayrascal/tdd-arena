package com.tw.arena.role;

import com.tw.arena.armor.Armor;
import com.tw.arena.armor.WoodShield;
import com.tw.arena.weapon.Cudgel;
import com.tw.arena.weapon.Weapon;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Date: 2015/11/29
 * Time: 17:58
 *
 * @author Rascal
 */
public class RoleTest {

    @Test
    public void shoudReturnCorrectBasicInfoAboutNormal() {
        Role role = new Normal("张三", 100, 5);

        assertThat(role.getName(), is("张三"));
        assertThat(role.getBlood(), is(100));
        assertThat(role.getDamage(), is(5));
        assertThat(role.getDefense(), is(0));
        assertThat(role.isAlive(), is(true));
        assertThat(role.getAttackType(), is(""));
        assertThat(role.getWeapon().getName(), is(""));
        assertThat(role.getWeapon().getDamage(), is(0));
        assertThat(role.getArmor().getName(), is(""));
        assertThat(role.getArmor().getDefense(), is(0));
        assertThat(role.getRoleType(), is("普通人"));
        assertThat(role.getRoleIdentity(), is("普通人张三"));
    }

    @Test
    public void shouldGetCorrectInfoAboutSoliderEquipWeaponButWithoutArmor() {
        Weapon weapon = new Cudgel("优质木棒", 5);
        Role role = new Solider("张三", 100, 10, weapon);

        assertThat(role.getName(), is("张三"));
        assertThat(role.getBlood(), is(100));
        assertThat(role.getDamage(), is(15));
        assertThat(role.getDefense(), is(0));
        assertThat(role.isAlive(), is(true));
        assertThat(role.getAttackType(), is("用优质木棒"));
        assertThat(role.getWeapon().getName(), is("优质木棒"));
        assertThat(role.getArmor().getName(), is(""));
        assertThat(role.getArmor().getDefense(), is(0));
        assertThat(role.getWeapon().getDamage(), is(5));
        assertThat(role.getRoleType(), is("战士"));
        assertThat(role.getRoleIdentity(), is("战士张三"));
    }

    @Test
    public void shouldGetCorrectInfoAboutSoliderEquipArmorButWithoutWeapon() {
        Armor armor = new WoodShield("木盾", 5);
        Role role = new Solider("张三", 100, 10, armor);

        assertThat(role.getName(), is("张三"));
        assertThat(role.getBlood(), is(100));
        assertThat(role.getDamage(), is(10));
        assertThat(role.getDefense(), is(5));
        assertThat(role.isAlive(), is(true));
        assertThat(role.getAttackType(), is(""));
        assertThat(role.getWeapon().getName(), is(""));
        assertThat(role.getWeapon().getDamage(), is(0));
        assertThat(role.getArmor().getName(), is("木盾"));
        assertThat(role.getArmor().getDefense(), is(5));
        assertThat(role.getRoleType(), is("战士"));
        assertThat(role.getRoleIdentity(), is("战士张三"));
    }

    @Test
    public void shouldRsturnCorrectInfoAboutRoleEquipArmorAndWeapon() {
        Weapon weapon = new Cudgel("优质木棒", 5);
        Armor armor = new WoodShield("木盾", 5);
        Role role = new Solider("张三", 100, 10, weapon, armor);

        assertThat(role.getName(), is("张三"));
        assertThat(role.getBlood(), is(100));
        assertThat(role.getDamage(), is(15));
        assertThat(role.getDefense(), is(5));
        assertThat(role.isAlive(), is(true));
        assertThat(role.getAttackType(), is("用优质木棒"));
        assertThat(role.getWeapon().getName(), is("优质木棒"));
        assertThat(role.getWeapon().getDamage(), is(5));
        assertThat(role.getArmor().getName(), is("木盾"));
        assertThat(role.getArmor().getDefense(), is(5));
        assertThat(role.getRoleType(), is("战士"));
        assertThat(role.getRoleIdentity(), is("战士张三"));

    }

    @Test
    public void shoudReturnDetailAboutZhangSanAttackLiSi(){
        Weapon weapon = new Cudgel("优质木棒", 5);
        Role solider = new Solider("张三", 100, 3, weapon);
        Role normal = new Normal("李四", 20, 2);

        String detail = normal.beAttacked(solider);
        assertThat(detail, is("战士张三用优质木棒攻击了普通人李四,李四受到了8点伤害,李四剩余生命: 12"));
    }

    @Test
    public void shouldReturnTrueWhenBloodIs0(){
        Role solder = new Solider("张三", 0, 10);

        assertThat(solder.isAlive(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenBloodisLessThan0(){
        Role solider = new Solider("张三", -1, 10);

        assertThat(solider.isAlive(), is(false));
    }

    @Test
    public void shouldReturn0WhenDamageLessThanDefense() {
        Armor armor = new WoodShield("木盾", 5);
        Role solider = new Solider("张三", 100, 3, armor);
        Role normal = new Normal("李四", 20, 2);

        String detail = solider.beAttacked(normal);

        assertThat(detail, is("普通人李四攻击了装备了木盾的战士张三,张三受到了0点伤害,张三剩余生命: 100"));
    }
}
