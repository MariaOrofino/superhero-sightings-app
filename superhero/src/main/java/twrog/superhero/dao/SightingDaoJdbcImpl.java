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
import twrog.superhero.dto.HeroSighting;
import twrog.superhero.dto.Location;

/**
 *
 * @author Travis Rogers
 */
public class SightingDaoJdbcImpl {
    
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private static final String SQL_INSERT_SIGHTING = "insert into Sighting (LocationID,SightingDate) values (?,?)";
    private static final String SQL_INSERT_HEROSIGHTING = "insert into HeroSighting (SightingID,HeroID) values (?,?)";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE =
            "select Hero.*, Location.*, HeroSighting.SightingID from Location" +
            "inner join Sighting on Location.LocationID = Sighting.LocationID" +
            "inner join HeroSighting on Sighting.SightingID = HeroSighting.SightingID" +
            "inner join Hero on Hero.HeroID = HeroSighting.HeroID" +
            "where Sighting.SightingDate = ?";
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createHeroSighting(int locationID, LocalDate date, List<Integer> heroIDs) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING, locationID, date.toString());
        int sightingID = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        for (int heroID : heroIDs) {
            jdbcTemplate.update(SQL_INSERT_HEROSIGHTING, sightingID, heroID);
        }
    }
    public List<HeroSighting> getSightingsByDate(LocalDate date) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date.toString());
    }
    
    private static final class SightingMapper implements RowMapper<HeroSighting> {
        @Override
        public HeroSighting mapRow(ResultSet rs, int i) throws SQLException {
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
            location.setLatitude(rs.getDouble("Latitude"));
            location.setLongitude(rs.getDouble("Longitude"));
            HeroSighting sighting = new HeroSighting();
            sighting.setSightingID(rs.getInt("SightingID"));
            sighting.setHero(hero);
            sighting.setLocation(location);
            return sighting;
        }
    }
}
