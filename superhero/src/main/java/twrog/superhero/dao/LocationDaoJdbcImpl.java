/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package twrog.superhero.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import twrog.superhero.dto.Location;

/**
 *
 * @author Travis Rogers
 */
public class LocationDaoJdbcImpl implements LocationDao {
    
    private static final String SQL_INSERT_LOCATION = "insert into Location "
            + "(LocationName, Description, StreetAddress, City, State, Zipcode, Latitude, Longitude) "
            + "values (?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_LOCATION_BY_ID = "select * from Location where LocationID = ?";
    private static final String SQL_SELECT_ALL_LOCATIONS = "select * from Location";
    private static final String SQL_SELECT_LOCATIONS_BY_HERO_ID =
            "select Location.* from Location" +
            " inner join Sighting on Location.LocationID = Sighting.LocationID" +
            " inner join Hero on Hero.HeroID = Sighting.HeroID" +
            " where Hero.HeroID = ?";
    private static final String SQL_UPDATE_LOCATION_BY_ID = "update Location "
            + "set LocationName = ?, Description = ?, StreetAddress = ?, City = ?, State = ?, Zipcode = ?, Latitude = ?, Longitude = ? "
            + "where LocationID = ?";
    private static final String SQL_DELETE_LOCATION_BY_ID = "delete from Location where LocationID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getStreetAddress(),
                location.getCity(),
                location.getState(),
                location.getZipcode(),
                location.getLatitude(),
                location.getLongitude()
        );
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        location.setLocationID(id);
    }
    @Override
    public Location getLocationByID(int locationID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ID, new LocationMapper(), locationID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }
    @Override
    public List<Location> getLocationsByHeroID(int heroID) {
        return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_HERO_ID, new LocationMapper(), heroID);
    }
    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION_BY_ID,
                location.getLocationName(),
                location.getDescription(),
                location.getStreetAddress(),
                location.getCity(),
                location.getState(),
                location.getZipcode(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID()
        );
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocationByID(int locationID) {
        deleteSightingsByLocationID(locationID);
        jdbcTemplate.update(SQL_DELETE_LOCATION_BY_ID, locationID);
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
            location.setLatitude(rs.getDouble("Latitude"));
            location.setLongitude(rs.getDouble("Longitude"));
            return location;
        }
    }
    
    private static final String SQL_DELETE_SIGHTINGS_BY_LOCATION_ID = "delete from Sighting where LocationID = ?";
    private void deleteSightingsByLocationID(int locationID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_LOCATION_ID, locationID);
    }
}
