package com.tw.arena.weapon.property;

import com.tw.arena.role.Role;

/**
 * Date: 2015/12/1
 * Time: 9:31
 *
 * @author Rascal
 */
public interface WeaponProperty {

    int getPropertyDamage();

    int getDelayTimes();

    float getProbability();

    String getPropertyDamageEffect(Role victim);

    String getPropertyDamageDetail(Role victim);
}
