/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twrog.superhero.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import twrog.superhero.dto.SuperPower;

/**
 *
 * @author 7ravis
 */
public class SuperPowerDaoJdbcImplTest {
    
    public SuperPowerDaoJdbcImplTest() {
    }

    /**
     * Test of addSuperPower and getSuperPowerByID methods, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testAddGetSuperPower() {
        System.out.println("addSuperPower");
        SuperPowerDaoJdbcImpl instance = new SuperPowerDaoJdbcImpl();
        SuperPower sp = new SuperPower();
        sp.setDescription("super-speed");
        instance.addSuperPower(sp);
        int superPowerID = sp.getSuperPowerID();
        SuperPower result = instance.getSuperPowerByID(superPowerID);
        assertEquals(sp, result);
    }

    /**
     * Test of getAllSuperPowers method, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testGetAllSuperPowers() {
        System.out.println("getAllSuperPowers");
        SuperPowerDaoJdbcImpl instance = new SuperPowerDaoJdbcImpl();
        SuperPower sp1 = new SuperPower();
        sp1.setDescription("super-speed");
        instance.addSuperPower(sp1);
        SuperPower sp2 = new SuperPower();
        sp2.setDescription("telepathy");
        instance.addSuperPower(sp2);
        int expResult = 2;
        int result = instance.getAllSuperPowers().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateSuperPower method, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testUpdateSuperPower() {
        System.out.println("updateSuperPower");
        SuperPowerDaoJdbcImpl instance = new SuperPowerDaoJdbcImpl();
        SuperPower sp = new SuperPower();
        sp.setDescription("super-speed");
        instance.addSuperPower(sp);
        int superPowerID = sp.getSuperPowerID();
        sp.setDescription("flight");
        instance.updateSuperPower(sp);
        SuperPower result = instance.getSuperPowerByID(superPowerID);
        assertEquals(sp, result);
    }

    /**
     * Test of deleteSuperPower method, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testDeleteSuperPower() {
        System.out.println("deleteSuperPower");
        SuperPowerDaoJdbcImpl instance = new SuperPowerDaoJdbcImpl();
        SuperPower sp = new SuperPower();
        sp.setDescription("super-speed");
        instance.addSuperPower(sp);
        int superPowerID = sp.getSuperPowerID();
        SuperPower result = instance.getSuperPowerByID(superPowerID);
        assertEquals(sp, result);
        instance.deleteSuperPower(superPowerID);
        assertNull(instance.getSuperPowerByID(superPowerID));
    }
    
}
