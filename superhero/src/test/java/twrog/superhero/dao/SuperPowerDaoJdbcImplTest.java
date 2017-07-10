/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twrog.superhero.dto.SuperPower;

/**
 *
 * @author 7ravis
 */
public class SuperPowerDaoJdbcImplTest {
    SuperPowerDao instance;
    
    public SuperPowerDaoJdbcImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        instance = ctx.getBean("superPowerDao", SuperPowerDao.class);
        List<SuperPower> superPowers = instance.getAllSuperPowers();
        for (SuperPower sp : superPowers) {
            instance.deleteSuperPower(sp.getSuperPowerID());
        }
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of addSuperPower and getSuperPowerByID methods, of class SuperPowerDaoJdbcImpl.
     */
    @Test
    public void testAddGetSuperPower() {
        System.out.println("addSuperPower");
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
