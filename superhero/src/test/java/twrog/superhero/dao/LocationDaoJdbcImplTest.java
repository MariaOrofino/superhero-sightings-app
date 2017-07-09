/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import twrog.superhero.dto.Location;

/**
 *
 * @author 7ravis
 */
public class LocationDaoJdbcImplTest {
    
    public LocationDaoJdbcImplTest() {
    }
    
    /**
     * Test of addLocation and getLocationByID methods, of class LocationDaoJdbcImpl.
     */
    @Test
    public void testAddGetLocation() {
        System.out.println("addLocation");
        LocationDaoJdbcImpl instance = new LocationDaoJdbcImpl();
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
        LocationDaoJdbcImpl instance = new LocationDaoJdbcImpl();
        Location location1 = new Location();
        location1.setLocationName("Destruction Lab");
        location1.setDescription("villain lab for quantum physics experiments");
        location1.setStreetAddress("1234 Hello St.");
        location1.setCity("Williamsburg");
        location1.setState("VA");
        location1.setZipcode("55410");
        location1.setLatitude(45.13);
        location1.setLongitude(81.99);
        instance.addLocation(location1);
        Location location2 = new Location();
        location2.setLocationName("Destruction Lab");
        location2.setDescription("villain lab for quantum physics experiments");
        location2.setStreetAddress("1234 Hello St.");
        location2.setCity("Williamsburg");
        location2.setState("VA");
        location2.setZipcode("55410");
        location2.setLatitude(45.13);
        location2.setLongitude(81.99);
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
        LocationDaoJdbcImpl instance = new LocationDaoJdbcImpl();
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
        LocationDaoJdbcImpl instance = new LocationDaoJdbcImpl();
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
        instance.deleteLocationByID(locationID);
        assertNull(instance.getLocationByID(locationID));
    }
    
}
