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
public class heroDaoJdbcImpl {
    private static final String SQL_SELECT_ALL_LOCATIONS = "select * from Location";
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
    private static final String SQL_SELECT_LOCATIONS_BY_HERO_ID =
            "select Location.* from Location" +
            "inner join Sighting on Location.LocationID = Sighting.LocationID" +
            "inner join HeroSighting on Sighting.SightingID = HeroSighting.SightingID" +
            "inner join Hero on Hero.HeroID = HeroSighting.HeroID" +
            "where Hero.HeroID = ?";
    private static final String SQL_SELECT_ORGANIZATIONS_BY_HER0_ID =
            "select Organization.* from Organization" +
            "inner join HeroOrganization on HeroOrganization.OrganizationID = Organization.OrganizationID" +
            "inner join Hero on Hero.HeroID = HeroOrganization.HeroID" +
            "where Hero.HeroID = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE =
            "select Hero.*, Location.* from Location" +
            "inner join Sighting on Location.LocationID = Sighting.LocationID" +
            "inner join HeroSighting on Sighting.SightingID = HeroSighting.SightingID" +
            "inner join Hero on Hero.HeroID = HeroSighting.HeroID" +
            "where Sighting.SightingDate = ?";
    private static final String SQL_INSERT_SIGHTING = "insert into Sighting (LocationID,SightingDate) values (?,?)";
    private static final String SQL_INSERT_HEROSIGHTING = "insert into HeroSighting (SightingID,HeroID) values (?,?)";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createHeroSighting(int locationID, LocalDate date, List<Integer> heroIDs) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING, locationID, date.toString());
        int sightingID = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        for (int heroID : heroIDs) {
            jdbcTemplate.update(SQL_INSERT_HEROSIGHTING, sightingID, heroID);
        }
    }
    public List<Hero> getAllHeros() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HEROS, new HeroMapper());
    }
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }
    public List<Hero> getHerosByLocationID(int locationID) {
        return jdbcTemplate.query(SQL_SELECT_HEROS_BY_LOCATION_ID, new HeroMapper(), locationID);
    }
    public List<Hero> getHerosByOrganizationID(int organizationID) {
        return jdbcTemplate.query(SQL_SELECT_HEROS_BY_ORGANIZATION_ID, new HeroMapper(), organizationID);
    }
    public List<Location> getLocationsByHeroID(int heroID) {
        return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_HERO_ID, new LocationMapper(), heroID);
    }
    public List<Organization> getOrganizationsByHeroID(int heroID) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_HER0_ID, new OrganizationMapper(), heroID);
    }
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date.toString());
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
    private static final class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("LocationName"));
            location.setDescription(rs.getString("Description"));
            location.setStreetAddress(rs.getString("StreetAddress"));
            location.setCity(rs.getString("City"));
            location.setState(rs.getString("State"));
            location.setZipcode(rs.getString("Zipcode"));
            location.setLatitude(String.valueOf(rs.getDouble("Latitude")));
            location.setLongitude(String.valueOf(rs.getDouble("Longitude")));
            return location;
        }
    }
    private static final class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationID(rs.getInt("OrganizationID"));
            org.setOrgName(rs.getString("OrgName"));
            org.setDescription(rs.getString("Description"));
            org.setStreetAddress(rs.getString("StreetAddress"));
            org.setCity(rs.getString("City"));
            org.setState(rs.getString("State"));
            org.setZipcode(rs.getString("Zipcode"));
            return org;
        }
    }
    private static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroID(rs.getInt("HeroID"));
            hero.setHeroName(rs.getString("HeroName"));
            hero.setDescription(rs.getString("Description"));
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("LocationName"));
            location.setDescription(rs.getString("Description"));
            location.setStreetAddress(rs.getString("StreetAddress"));
            location.setCity(rs.getString("City"));
            location.setState(rs.getString("State"));
            location.setZipcode(rs.getString("Zipcode"));
            location.setLatitude(String.valueOf(rs.getDouble("Latitude")));
            location.setLongitude(String.valueOf(rs.getDouble("Longitude")));
            Sighting sighting = new Sighting();
            sighting.setHero(hero);
            sighting.setLocation(location);
            return sighting;
        }
    }
}
