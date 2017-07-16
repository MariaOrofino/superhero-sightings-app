package twrog.superhero.dao;

import java.time.LocalDate;
import java.util.List;
import twrog.superhero.dto.Sighting;

/**
 *
 * @author Travis Rogers
 */

public interface SightingDao {

    void addSighting(Sighting sighting);

    void deleteSightingByID(int sightingID);

    Sighting getSightingByID(int sightingID);
    
    List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsToLimit();

    List<Sighting> getSightingsByDate(LocalDate date);

    List<Sighting> getSightingsByLocationID(int locationID);

    void updateSighting(Sighting sighting);

}
