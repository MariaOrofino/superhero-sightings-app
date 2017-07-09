/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import twrog.superhero.dto.Hero;

/**
 *
 * @author 7ravis
 */
public class HeroDaoJdbcImplTest {
    
    public HeroDaoJdbcImplTest() {
    }
    
    /**
     * Test of addHero and getHeroByID methods, of class HeroDaoJdbcImpl.
     */
    @Test
    public void testAddGetHero() {
        System.out.println("addHero");
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
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
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
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
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
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
        HeroDaoJdbcImpl instance = new HeroDaoJdbcImpl();
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
