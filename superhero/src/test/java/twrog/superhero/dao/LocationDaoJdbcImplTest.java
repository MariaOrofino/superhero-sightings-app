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
import twrog.superhero.dto.Location;

/**
 *
 * @author 7ravis
 */
public class LocationDaoJdbcImplTest {
    LocationDao instance;
    
    public LocationDaoJdbcImplTest() {
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
        instance = ctx.getBean("locationDao", LocationDao.class);
        List<Location> locations = instance.getAllLocations();
        for (Location location : locations) {
            instance.deleteLocationByID(location.getLocationID());
        }
    }
    
    @After
    public void tearDown() {        
    }
    
    /**
     * Test of addLocation and getLocationByID methods, of class LocationDaoJdbcImpl.
     */
    @Test
    public void testAddGetLocation() {
        System.out.println("addLocation");
        Location location = new Location();
        location.setLocationName("Destruction Lab");
        location.setDescription("villain lab for quantum physics experiments");
        location.setStreetAddress("1234 Hello St.");
        location.setCity("Williamsburg");
        location.setState("VA");
        location.setZipcode("55410");
        location.setLatitude(45.13);
        location.setLongitude(81.99);
        instance.addLocation(location);
        int locationID = location.getLocationID();
        Location result = instance.getLocationByID(locationID);
        assertEquals(location, result);
    }
    
    /**
     * Test of getAllLocations method, of class LocationDaoJdbcImpl.
     */
    @Test
    public void testGetAllLocations() {
        System.out.println("getAllLocations");
        Location location1 = new Location();
        location1.setLocationName("The White House");
        instance.addLocation(location1);
        Location location2 = new Location();
        location2.setLocationName("Destruction Lab");
        instance.addLocation(location2);
        int expResult = 2;
        int result = instance.getAllLocations().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of updateLocation method, of class LocationDaoJdbcImpl.
     */
    @Test
    public void testUpdateLocation() {
        System.out.println("updateLocation");
        Location location = new Location();
        location.setLocationName("Destruction Lab");
        location.setDescription("villain lab for quantum physics experiments");
        location.setStreetAddress("1234 Hello St.");
        location.setCity("Williamsburg");
        location.setState("VA");
        location.setZipcode("55410");
        location.setLatitude(45.13);
        location.setLongitude(81.99);
        instance.addLocation(location);
        int locationID = location.getLocationID();
        location.setLocationName("Destruction Laboratory");
        location.setDescription("lab for quantum physics experiments");
        location.setStreetAddress("1234 Hello St");
        location.setCity("Minneapolis");
        location.setState("MN");
        location.setZipcode("55410");
        location.setLatitude(42.13);
        location.setLongitude(70.99);
        instance.updateLocation(location);
        Location result = instance.getLocationByID(locationID);
        assertEquals(location, result);
    }
    
    /**
     * Test of deleteLocationByID method, of class LocationDaoJdbcImpl.
     */
    @Test
    public void testDeleteLocationByID() {
        System.out.println("deleteLocationByID");
        Location location = new Location();
        location.setLocationName("Destruction Lab");
        instance.addLocation(location);
        int locationID = location.getLocationID();
        Location result = instance.getLocationByID(locationID);
        assertEquals(location, result);
        instance.deleteLocationByID(locationID);
        assertNull(instance.getLocationByID(locationID));
    }
    
}
