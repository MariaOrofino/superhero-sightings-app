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
import twrog.superhero.dto.Hero;
import twrog.superhero.dto.Sighting;
import twrog.superhero.dto.Location;

/**
 *
 * @author Travis Rogers
 */
public class SightingDaoJdbcImpl {
    private static final String SQL_INSERT_SIGHTING = "insert into Sighting (LocationID, SightingDate, HeroID) values (?,?,?)";
    private static final String SQL_SELECT_SIGHTING_BY_ID =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            "inner join Sighting on Location.LocationID = Sighting.LocationID" +
            "inner join Hero on Hero.HeroID = Sighting.HeroID" +
            "where Sighting.SightingID = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            "inner join Sighting on Location.LocationID = Sighting.LocationID" +
            "inner join Hero on Hero.HeroID = Sighting.HeroID" +
            "where Sighting.SightingDate = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID =
            "select Hero.*, Location.*, Sighting.SightingID, Sighting.SightingDate from Location" +
            "inner join Sighting on Location.LocationID = Sighting.LocationID" +
            "inner join Hero on Hero.HeroID = Sighting.HeroID" +
            "where Location.LocationID = ?";
    private static final String SQL_UPDATE_SIGHTING_BY_ID = "update Sighting set LocationID = ?, SightingDate = ?, HeroID = ? where SightingID = ?";
    private static final String SQL_DELETE_SIGHTING_BY_ID = "delete from Sighting where SightingID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void addSighting(int locationID, LocalDate date, int heroID) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING, locationID, date.toString(), heroID);
    }
    public List<Sighting> getSightingByID(int sightingID) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingID);
    }
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new SightingMapper(), date.toString());
    }
    public List<Sighting> getSightingsByLocationID(int locationID) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), locationID);
    }
    public void updateSighting(int locationID, LocalDate date, int heroID, int sightingID) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING_BY_ID, locationID, date.toString(), heroID, sightingID);
    }
    public void deleteSightingByID(int sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_ID, sightingID);
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
            location.setLatitude(rs.getDouble("Latitude"));
            location.setLongitude(rs.getDouble("Longitude"));
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("SightingID"));
            sighting.setHero(hero);
            sighting.setLocation(location);
            sighting.setDate(LocalDate.parse(rs.getDate("SightingDate").toString()));
            return sighting;
        }
    }
}