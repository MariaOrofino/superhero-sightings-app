/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twrog.superhero.dao;

import java.util.List;
import twrog.superhero.dto.Hero;

/**
 *
 * @author Travis Rogers
 */
public interface HeroDao {

    void addHero(Hero hero);

    void addHeroOrg(int heroID, int orgID);

    void addHeroSuperPower(int heroID, int superPowerID);

    void deleteHeroByID(int heroID);

    void deleteHeroOrg(int heroID, int orgID);

    void deleteHeroSuperPower(int heroID, int superPowerID);

    List<Hero> getAllHeros();

    Hero getHeroByID(int heroID);

    List<Hero> getHerosByLocationID(int locationID);

    List<Hero> getHerosByOrganizationID(int organizationID);

    void updateHero(Hero hero);

}
