/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twrog.superhero.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import twrog.superhero.dto.Hero;

/**
 *
 * @author 7ravis
 */
public class HeroDaoJdbcImplTest {
    
    public HeroDaoJdbcImplTest() {
    }

    /**
     * Test of addHero method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testAddHero() {
        System.out.println("addHero");
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        hero.setDescription("classic superhero");
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        instance.addHero(hero);

    }

    /**
     * Test of getHeroByID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetHeroByID() {
        System.out.println("getHeroByID");
        int heroID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        Hero expResult = null;
        Hero result = instance.getHeroByID(heroID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllHeros method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetAllHeros() {
        System.out.println("getAllHeros");
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        List<Hero> expResult = null;
        List<Hero> result = instance.getAllHeros();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHerosByLocationID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetHerosByLocationID() {
        System.out.println("getHerosByLocationID");
        int locationID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        List<Hero> expResult = null;
        List<Hero> result = instance.getHerosByLocationID(locationID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHerosByOrganizationID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetHerosByOrganizationID() {
        System.out.println("getHerosByOrganizationID");
        int organizationID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        List<Hero> expResult = null;
        List<Hero> result = instance.getHerosByOrganizationID(organizationID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateHero method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testUpdateHero() {
        System.out.println("updateHero");
        Hero hero = null;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        instance.updateHero(hero);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteHeroByID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testDeleteHeroByID() {
        System.out.println("deleteHeroByID");
        int heroID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        instance.deleteHeroByID(heroID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addHeroSuperPower method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testAddHeroSuperPower() {
        System.out.println("addHeroSuperPower");
        int heroID = 0;
        int superPowerID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        instance.addHeroSuperPower(heroID, superPowerID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteHeroSuperPower method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testDeleteHeroSuperPower() {
        System.out.println("deleteHeroSuperPower");
        int heroID = 0;
        int superPowerID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        instance.deleteHeroSuperPower(heroID, superPowerID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addHeroOrg method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testAddHeroOrg() {
        System.out.println("addHeroOrg");
        int heroID = 0;
        int orgID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        instance.addHeroOrg(heroID, orgID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteHeroOrg method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testDeleteHeroOrg() {
        System.out.println("deleteHeroOrg");
        int heroID = 0;
        int orgID = 0;
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
        instance.deleteHeroOrg(heroID, orgID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
