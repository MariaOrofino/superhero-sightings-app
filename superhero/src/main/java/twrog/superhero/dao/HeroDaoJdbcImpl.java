/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package twrog.superhero.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import twrog.superhero.dto.Hero;
import twrog.superhero.dto.Location;
import twrog.superhero.dto.Organization;
import twrog.superhero.dto.Sighting;

/**
 *
 * @author Travis Rogers
 */
public class HeroDaoJdbcImpl {

    private static final String SQL_SELECT_ALL_HEROS = "select * from Hero";
    private static final String SQL_SELECT_HEROS_BY_LOCATION_ID =
            "select Hero.* from Hero" +
            "inner join HeroSighting on Hero.HeroID = HeroSighting.HeroID" +
            "inner join Sighting on Sighting.SightingID = HeroSighting.SightingID" +
            "inner join Location on Location.LocationID = Sighting.LocationID" +
            "where Location.LocationID = ?";
    private static final String SQL_SELECT_HEROS_BY_ORGANIZATION_ID =
            "select Hero.* from Hero" +
            "inner join HeroOrganization on HeroOrganization.HeroID = Hero.HeroID" +
            "inner join Organization on Organization.OrganizationID = HeroOrganization.OrganizationID" +
            "where Organization.OrganizationID = ?";


    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

    public List<Hero> getAllHeros() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROS, new HeroMapper());
    }
    public List<Hero> getHerosByLocationID(int locationID) {
        return jdbcTemplate.query(SQL_SELECT_HEROS_BY_LOCATION_ID, new HeroMapper(), locationID);
    }
    public List<Hero> getHerosByOrganizationID(int organizationID) {
        return jdbcTemplate.query(SQL_SELECT_HEROS_BY_ORGANIZATION_ID, new HeroMapper(), organizationID);
    }

    
    
    
    
    
    
    
    private static final class HeroMapper implements RowMapper<Hero> {
        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroID(rs.getInt("HeroID"));
            hero.setHeroName(rs.getString("HeroName"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }
    }
    
    private static final String SQL_INSERT_HERO_SUPER_POWER = "insert into HeroSuperPower (HeroID, SuperPowerID) values (?,?)";
    private static final String SQL_DELETE_HERO_SUPER_POWERS_BY_HERO_ID = "delete from HeroSuperPower where HeroID = ?";

    
    public void addHeroSuperPower(int heroID, int superPowerID) {
        jdbcTemplate.update(SQL_INSERT_HERO_SUPER_POWER, heroID, superPowerID);
    }
    public void deleteHeroSuperPowerByHeroID(int heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO_SUPER_POWERS_BY_HERO_ID, heroID);
    }

    
    private static final String SQL_INSERT_HERO_ORG = "insert into HeroOrganization (HeroID,OrganizationID) values (?,?)";
    private static final String SQL_DELETE_HERO_ORG_BY_HERO_ID = "delete from HeroOrganization where HeroID = ?";
    
    
    public void addHeroOrg(int heroID, int orgID) {
        jdbcTemplate.update(SQL_INSERT_HERO_ORG, heroID, orgID);
    }
    public void deleteHeroOrgByHeroID(int heroID) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORG_BY_HERO_ID, heroID);
    }

    private static final String SQL_DELETE_SIGHTINGS_BY_HERO_ID = "delete from Sighting where HeroID = ?";
    private void deleteSightingsByHeroID(int heroID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_HERO_ID, heroID);
    }
}
