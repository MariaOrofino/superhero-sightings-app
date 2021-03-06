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
 * @author Travis Rogers
 */

public class SightingDaoJdbcImplTest {
    
    SightingDao sightingDao;
    LocationDao locationDao;
    HeroDao heroDao;
    
    public SightingDaoJdbcImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
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
     * Test of addSighting and getSightingByID methods, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testAddGetSighting() {
        System.out.println("addSighting");        
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        heroDao.addHero(hero);
        Location location = new Location();
        location.setLocationName("Destruction Lab");
        location.setDescription("hello");
        locationDao.addLocation(location);
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);
        int sightingID = sighting.getSightingID();
        Sighting result = sightingDao.getSightingByID(sightingID);
        assertEquals(sighting, result);
    }
    
    /**
     * Test of getAllSightings method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testGetAllSightings() {
        System.out.println("getAllSightings");
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        heroDao.addHero(hero);
        Location location = new Location();
        location.setLocationName("Destruction Lab");
        locationDao.addLocation(location);
        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.now());
        sighting1.setHero(hero);
        sighting1.setLocation(location);
        sightingDao.addSighting(sighting1);
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("2017-02-03"));
        sighting2.setHero(hero);
        sighting2.setLocation(location);
        sightingDao.addSighting(sighting2);
        int expResult = 2;
        int result = sightingDao.getAllSightings().size();
        assertEquals(expResult,result);
    }
    
    /**
     * Test of getSightingsByDate method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testGetSightingsByDate() {
        System.out.println("getSightingsByDate");
        Hero hero1 = new Hero();
        hero1.setHeroName("Wonder Woman");
        heroDao.addHero(hero1);
        Hero hero2 = new Hero();
        hero2.setHeroName("Spider Man");
        heroDao.addHero(hero2);
        Location location1 = new Location();
        location1.setLocationName("The White House");
        locationDao.addLocation(location1);
        Location location2 = new Location();
        location2.setLocationName("Destruction Lab");
        locationDao.addLocation(location2);
        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2017-06-11"));
        sighting1.setHero(hero1);
        sighting1.setLocation(location1);
        sightingDao.addSighting(sighting1);
        int id1 = sighting1.getSightingID();
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("2017-02-03"));
        sighting2.setHero(hero2);
        sighting2.setLocation(location2);
        sightingDao.addSighting(sighting2);
        int id2 = sighting2.getSightingID();
        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.parse("2017-02-03"));
        sighting3.setHero(hero1);
        sighting3.setLocation(location1);
        sightingDao.addSighting(sighting3);
        int id3 = sighting3.getSightingID();
        List<Sighting> result = sightingDao.getSightingsByDate(LocalDate.parse("2017-02-03"));
        assertTrue(result.stream().anyMatch(s -> s.getSightingID() == id2));
        assertTrue(result.stream().anyMatch(s -> s.getSightingID() == id3));
        assertTrue(result.size() == 2);
    }
    
    /**
     * Test of getSightingsByLocationID method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testGetSightingsByLocationID() {
        System.out.println("getSightingsByLocationID");
        Hero hero1 = new Hero();
        hero1.setHeroName("Wonder Woman");
        heroDao.addHero(hero1);
        Hero hero2 = new Hero();
        hero2.setHeroName("Spider Man");
        heroDao.addHero(hero2);
        Location location1 = new Location();
        location1.setLocationName("The White House");
        locationDao.addLocation(location1);
        Location location2 = new Location();
        location2.setLocationName("Destruction Lab");
        locationDao.addLocation(location2);
        Sighting sighting1 = new Sighting();
        sighting1.setDate(LocalDate.parse("2017-06-11"));
        sighting1.setHero(hero1);
        sighting1.setLocation(location1);
        sightingDao.addSighting(sighting1);
        int id1 = sighting1.getSightingID();
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("2017-02-03"));
        sighting2.setHero(hero2);
        sighting2.setLocation(location2);
        sightingDao.addSighting(sighting2);
        int id2 = sighting2.getSightingID();
        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.parse("2017-03-06"));
        sighting3.setHero(hero1);
        sighting3.setLocation(location2);
        sightingDao.addSighting(sighting3);
        int id3 = sighting3.getSightingID();
        List<Sighting> result = sightingDao.getSightingsByLocationID(location2.getLocationID());
        assertTrue(result.stream().anyMatch(s -> s.getSightingID() == id2));
        assertTrue(result.stream().anyMatch(s -> s.getSightingID() == id3));
        assertTrue(result.size() == 2);
    }
    
    /**
     * Test of updateSighting method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testUpdateSighting() {
        System.out.println("updateSighting");
        Hero hero1 = new Hero();
        hero1.setHeroName("Wonder Woman");
        heroDao.addHero(hero1);
        Hero hero2 = new Hero();
        hero2.setHeroName("Spider Man");
        heroDao.addHero(hero2);
        Location location1 = new Location();
        location1.setLocationName("The White House");
        locationDao.addLocation(location1);
        Location location2 = new Location();
        location2.setLocationName("Destruction Lab");
        locationDao.addLocation(location2);
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHero(hero1);
        sighting.setLocation(location1);
        sightingDao.addSighting(sighting);
        int sightingID = sighting.getSightingID();
        sighting.setDate(LocalDate.parse("2017-02-03"));
        sighting.setHero(hero2);
        sighting.setLocation(location2);
        sightingDao.updateSighting(sighting);
        Sighting result = sightingDao.getSightingByID(sightingID);
        assertEquals(sighting, result);
    }
    
    /**
     * Test of deleteSightingByID method, of class SightingDaoJdbcImpl.
     */
    @Test
    public void testDeleteSightingByID() {
        System.out.println("deleteSightingByID");
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        heroDao.addHero(hero);
        Location location = new Location();
        location.setLocationName("Destruction Lab");
        locationDao.addLocation(location);
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);
        int sightingID = sighting.getSightingID();
        Sighting result = sightingDao.getSightingByID(sightingID);
        assertEquals(sighting, result);
        sightingDao.deleteSightingByID(sightingID);
        assertNull(sightingDao.getSightingByID(sightingID));
    }
    
}
