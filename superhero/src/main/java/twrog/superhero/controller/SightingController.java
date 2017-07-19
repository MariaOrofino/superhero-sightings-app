package twrog.superhero.controller;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twrog.superhero.dao.HeroDao;
import twrog.superhero.dao.LocationDao;
import twrog.superhero.dao.SightingDao;
import twrog.superhero.dto.Hero;
import twrog.superhero.dto.Location;
import twrog.superhero.dto.Sighting;

/**
 *
 * @author Travis Rogers
 */

@Controller
public class SightingController {
    SightingDao sightingDao;
    LocationDao locationDao;
    HeroDao heroDao;

    @Inject
    public SightingController(SightingDao sightingDao, LocationDao locationDao, HeroDao heroDao) {
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
        this.heroDao = heroDao;
    }
    public SightingController() {}
    
    @RequestMapping(value="sighting", method=RequestMethod.GET)
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("sightings", sightings);
        return "/sighting";
    }
    @RequestMapping(value="reportSighting", method=RequestMethod.GET)
    public String reportSighting(Model model) {
        List<Hero> heros = heroDao.getAllHeros();
        List<Location> locations = locationDao.getAllLocations();
        LocalDate today = LocalDate.now();
        model.addAttribute("heros", heros);
        model.addAttribute("locations", locations);
        model.addAttribute("today", today);
        return "/reportSighting";
    }
    @RequestMapping(value="addSighting", method=RequestMethod.POST)
    public String addSighting(int heroID, int locationID, String sightingDate) {
        LocalDate date;
        try {
        date = LocalDate.parse(sightingDate);
        } catch (DateTimeParseException e) {
            return "redirect:/sighting";
        }
        Hero hero = new Hero();
        hero.setHeroID(heroID);
        Location location = new Location();
        location.setLocationID(locationID);
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);
        return "redirect:/sighting";
    }
    @RequestMapping(value="editSighting/{sightingID}", method=RequestMethod.GET)
    public String editSighting(@PathVariable("sightingID") int sightingID, Model model) {
        Sighting sighting = sightingDao.getSightingByID(sightingID);
        List<Hero> heros = heroDao.getAllHeros();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("heros", heros);
        model.addAttribute("locations", locations);
        model.addAttribute("sightingToEdit", sighting);
        return "/editSighting";
    }
    @RequestMapping(value="updateSighting", method=RequestMethod.POST)
    public String updateSighting(String sightingDate, int heroID, int locationID, int sightingID) {
        LocalDate date;
        try {
        date = LocalDate.parse(sightingDate);
        } catch (DateTimeParseException e) {
            return "redirect:/sighting";
        }
        Hero hero = new Hero();
        hero.setHeroID(heroID);
        Location location = new Location();
        location.setLocationID(locationID);
        Sighting sighting = new Sighting();
        sighting.setSightingID(sightingID);
        sighting.setDate(date);
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDao.updateSighting(sighting);
        return "redirect:/sighting";
    }
    @RequestMapping(value="deleteSighting/{sightingID}", method=RequestMethod.GET)
    public String deleteSighting(@PathVariable("sightingID") int id) {
        sightingDao.deleteSightingByID(id);
        return "redirect:/sighting";
    }    
}
