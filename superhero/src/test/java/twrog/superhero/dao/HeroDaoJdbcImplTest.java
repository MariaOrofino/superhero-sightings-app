/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import twrog.superhero.dto.Hero;

/**
 *
 * @author 7ravis
 */
public class HeroDaoJdbcImplTest {
    HeroDao instance;
    
    public HeroDaoJdbcImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        instance = ctx.getBean("heroDao", HeroDao.class);
        List<Hero> heros = instance.getAllHeros();
        for (Hero hero : heros) {
            instance.deleteHeroByID(hero.getHeroID());
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
        instance.addHero(hero);
        Hero result = instance.getHeroByID(hero.getHeroID());
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
        instance.addHero(hero1);
        Hero hero2 = new Hero();
        hero2.setHeroName("Spider Man");
        instance.addHero(hero2);
        int expResult = 2;
        int result = instance.getAllHeros().size();
        assertEquals(expResult, result);
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
        instance.addHero(hero);
        hero.setDescription("has ability to fly");
        instance.updateHero(hero);
        Hero result = instance.getHeroByID(hero.getHeroID());
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
        instance.addHero(hero);
        int heroID = hero.getHeroID();
        Hero heroFromDao = instance.getHeroByID(heroID);
        assertEquals(hero,heroFromDao);
        instance.deleteHeroByID(heroID);
        assertNull(instance.getHeroByID(heroID));
    }
    
}
