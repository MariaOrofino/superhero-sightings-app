/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twrog.superhero.dao;

import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import twrog.superhero.dto.Sighting;

/**
 *
 * @author 7ravis
 */
public class SightingDaoJdbcImplTest {
    
    public SightingDaoJdbcImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
        System.out.println("setJdbcTemplate");
        JdbcTemplate jdbcTemplate = null;
        SightingDaoJdbcImpl instance = new SightingDaoJdbcImpl();
        instance.setJdbcTemplate(jdbcTemplate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSighting method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testAddSighting() {
        System.out.println("addSighting");
        Sighting sighting = null;
        SightingDaoJdbcImpl instance = new SightingDaoJdbcImpl();
        instance.addSighting(sighting);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSightingByID method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testGetSightingByID() {
        System.out.println("getSightingByID");
        int sightingID = 0;
        SightingDaoJdbcImpl instance = new SightingDaoJdbcImpl();
        Sighting expResult = null;
        Sighting result = instance.getSightingByID(sightingID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSightingsByDate method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testGetSightingsByDate() {
        System.out.println("getSightingsByDate");
        LocalDate date = null;
        SightingDaoJdbcImpl instance = new SightingDaoJdbcImpl();
        List<Sighting> expResult = null;
        List<Sighting> result = instance.getSightingsByDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSightingsByLocationID method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testGetSightingsByLocationID() {
        System.out.println("getSightingsByLocationID");
        int locationID = 0;
        SightingDaoJdbcImpl instance = new SightingDaoJdbcImpl();
        List<Sighting> expResult = null;
        List<Sighting> result = instance.getSightingsByLocationID(locationID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSighting method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testUpdateSighting() {
        System.out.println("updateSighting");
        int locationID = 0;
        LocalDate date = null;
        int heroID = 0;
        int sightingID = 0;
        SightingDaoJdbcImpl instance = new SightingDaoJdbcImpl();
        instance.updateSighting(locationID, date, heroID, sightingID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSightingByID method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testDeleteSightingByID() {
        System.out.println("deleteSightingByID");
        int sightingID = 0;
        SightingDaoJdbcImpl instance = new SightingDaoJdbcImpl();
        instance.deleteSightingByID(sightingID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
