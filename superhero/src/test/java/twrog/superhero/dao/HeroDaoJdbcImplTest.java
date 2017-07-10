/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import java.time.LocalDate;
import java.util.List;
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
public class HeroDaoJdbcImplTest {
    HeroDao heroDao;
    SightingDao sightingDao;
    LocationDao locationDao;
    
    public HeroDaoJdbcImplTest() {
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
    
    /**
     * Test of addHero and getHeroByID methods, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testAddGetHero() {
        System.out.println("addHero");
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        hero.setDescription("classic superhero");
        heroDao.addHero(hero);
        Hero result = heroDao.getHeroByID(hero.getHeroID());
        assertEquals(hero, result);
    }
    
    /**
     * Test of getAllHeros method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetAllHeros() {
        System.out.println("getAllHeros");
        Hero hero1 = new Hero();
        hero1.setHeroName("Wonder Woman");
        heroDao.addHero(hero1);
        Hero hero2 = new Hero();
        hero2.setHeroName("Spider Man");
        heroDao.addHero(hero2);
        int expResult = 2;
        int result = heroDao.getAllHeros().size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHerosByLocationID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetHerosByLocationID() {
        Hero hero1 = new Hero();
        hero1.setHeroName("Wonder Woman");
        heroDao.addHero(hero1);
        int heroId1 = hero1.getHeroID();
        Hero hero2 = new Hero();
        hero2.setHeroName("Spider Man");
        heroDao.addHero(hero2);
        int heroId2 = hero2.getHeroID();
        Hero hero3 = new Hero();
        hero3.setHeroName("Storm");
        heroDao.addHero(hero3);
        int heroId3 = hero3.getHeroID();
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
        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.parse("2017-02-03"));
        sighting2.setHero(hero2);
        sighting2.setLocation(location2);
        sightingDao.addSighting(sighting2);
        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.parse("2017-03-06"));
        sighting3.setHero(hero3);
        sighting3.setLocation(location2);
        sightingDao.addSighting(sighting3);
        List<Hero> result = heroDao.getHerosByLocationID(location2.getLocationID());
        assertTrue(result.stream().anyMatch(h -> h.getHeroID() == heroId2));
        assertTrue(result.stream().anyMatch(h -> h.getHeroID() == heroId3));
//        assertTrue(result.stream().anyMatch(s -> s.getSightingID() == id2));
//        assertTrue(result.stream().anyMatch(s -> s.getSightingID() == id3));
        assertTrue(result.size() == 2);
    }
    
    /**
     * Test of getHerosByOrganizationID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testGetHerosByOrganizationID() {
        
    }
    
    /**
     * Test of updateHero method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testUpdateHero() {
        System.out.println("updateHero");
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        hero.setDescription("classic superhero");
        heroDao.addHero(hero);
        hero.setDescription("has ability to fly");
        heroDao.updateHero(hero);
        Hero result = heroDao.getHeroByID(hero.getHeroID());
        assertEquals(hero, result);
    }
    
    /**
     * Test of deleteHeroByID method, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testDeleteHeroByID() {
        System.out.println("deleteHeroByID");
        Hero hero = new Hero();
        hero.setHeroName("Wonder Woman");
        heroDao.addHero(hero);
        int heroID = hero.getHeroID();
        Hero heroFromDao = heroDao.getHeroByID(heroID);
        assertEquals(hero,heroFromDao);
        heroDao.deleteHeroByID(heroID);
        assertNull(heroDao.getHeroByID(heroID));
    }
    
}
