/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twrog.superhero.dto.Hero;
import twrog.superhero.dto.Location;
import twrog.superhero.dto.Sighting;

/**
 *
 * @author 7ravis
 */
public class LocationDaoJdbcImplTest {
    LocationDao locationDao;
    HeroDao heroDao;
    SightingDao sightingDao;
    
    public LocationDaoJdbcImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingByID(sighting.getSightingID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationByID(location.getLocationID());
        }
    }
    
    @After
    public void tearDown() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingByID(sighting.getSightingID());
        }
        List<Hero> heros = heroDao.getAllHeros();
        for (Hero hero : heros) {
            heroDao.deleteHeroByID(hero.getHeroID());
        }
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationByID(location.getLocationID());
        }
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
        locationDao.addLocation(location);
        int locationID = location.getLocationID();
        Location result = locationDao.getLocationByID(locationID);
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
        locationDao.addLocation(location1);
        Location location2 = new Location();
        location2.setLocationName("Destruction Lab");
        locationDao.addLocation(location2);
        int expResult = 2;
        int result = locationDao.getAllLocations().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLocationsByHeroID method, of class LocationDaoJdbcImpl.
     */
    @Test
    public void testGetLocationsByHeroID() {
        System.out.println("getLocationsByHeroID");
        Hero hero1 = new Hero();
        hero1.setHeroName("Storm");
        heroDao.addHero(hero1);
        int heroId1 = hero1.getHeroID();
        Hero hero2 = new Hero();
        hero2.setHeroName("Spider Man");
        heroDao.addHero(hero2);
        int heroId2 = hero2.getHeroID();
        Location location1 = new Location();
        location1.setLocationName("The White House");
        locationDao.addLocation(location1);
        int locId1 = location1.getLocationID();
        Location location2 = new Location();
        location2.setLocationName("Destruction Lab");
        locationDao.addLocation(location2);
        int locId2 = location2.getLocationID();
        Location location3 = new Location();
        location3.setLocationName("Mount Everest");
        locationDao.addLocation(location3);
        int locId3 = location3.getLocationID();
        
        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2017-06-11"));
        sighting1.setHero(hero1);
        sighting1.setLocation(location1);
        sightingDao.addSighting(sighting1);
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("2017-02-03"));
        sighting2.setHero(hero2);
        sighting2.setLocation(location2);
        sightingDao.addSighting(sighting2);
        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.parse("2017-03-06"));
        sighting3.setHero(hero2);
        sighting3.setLocation(location3);
        sightingDao.addSighting(sighting3);
        List<Location> locations = locationDao.getLocationsByHeroID(heroId2);
        assertTrue(locations.size() == 2);
        assertTrue(locations.stream().anyMatch(l -> l.getLocationID() == locId2));
        assertTrue(locations.stream().anyMatch(l -> l.getLocationID() == locId3));
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
        locationDao.addLocation(location);
        int locationID = location.getLocationID();
        location.setLocationName("Destruction Laboratory");
        location.setDescription("lab for quantum physics experiments");
        location.setStreetAddress("1234 Hello St");
        location.setCity("Minneapolis");
        location.setState("MN");
        location.setZipcode("55410");
        location.setLatitude(42.13);
        location.setLongitude(70.99);
        locationDao.updateLocation(location);
        Location result = locationDao.getLocationByID(locationID);
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
        locationDao.addLocation(location);
        int locationID = location.getLocationID();
        Location result = locationDao.getLocationByID(locationID);
        assertEquals(location, result);
        locationDao.deleteLocationByID(locationID);
        assertNull(locationDao.getLocationByID(locationID));
    }
    
}
