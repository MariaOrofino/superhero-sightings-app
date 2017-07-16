package twrog.superhero.dao;

import java.util.List;
import twrog.superhero.dto.Location;

/**
 *
 * @author Travis Rogers
 */

public interface LocationDao {

    void addLocation(Location location);

    void deleteLocationByID(int locationID);

    List<Location> getAllLocations();

    Location getLocationByID(int locationID);

    List<Location> getLocationsByHeroID(int heroID);

    void updateLocation(Location location);

}
