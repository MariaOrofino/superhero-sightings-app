package twrog.superhero.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twrog.superhero.dao.HeroDao;
import twrog.superhero.dao.LocationDao;
import twrog.superhero.dao.OrgDao;
import twrog.superhero.dao.SightingDao;
import twrog.superhero.dao.SuperPowerDao;
import twrog.superhero.dto.Hero;
import twrog.superhero.dto.Location;
import twrog.superhero.dto.Organization;
import twrog.superhero.dto.Sighting;

/**
 *
 * @author Travis Rogers
 */

@Controller
public class HeroController {
    HeroDao heroDao;
    LocationDao locationDao;
    OrgDao orgDao;
    SightingDao sightingDao;
    SuperPowerDao superPowerDao;
    
    @Inject
    public HeroController(HeroDao heroDao, LocationDao locationDao, OrgDao orgDao, SightingDao sightingDao, SuperPowerDao superPowerDao) {
        this.heroDao = heroDao;
        this.locationDao = locationDao;
        this.orgDao = orgDao;
        this.sightingDao = sightingDao;
        this.superPowerDao = superPowerDao;
    }
    public HeroController() {
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightingsToLimit();
        model.addAttribute("sightings", sightings);
        return "index";
    }
    @RequestMapping(value="hero", method=RequestMethod.GET)
    public String displayHeros(Model model) {
        List<Hero> heros = heroDao.getAllHeros();
        model.addAttribute("heros", heros);
        return "hero";
    }
    @RequestMapping(value="addHero", method=RequestMethod.POST)
    public String addHero(String heroName, String heroDescription) {
        Hero hero = new Hero();
        hero.setHeroName(heroName);
        hero.setDescription(heroDescription);
        heroDao.addHero(hero);
        return "redirect:hero";
    }
    @RequestMapping(value="/editHero/{heroID}", method=RequestMethod.GET)
    public String editHero(@PathVariable("heroID") int id, Model model) {
        Hero hero = heroDao.getHeroByID(id);
        model.addAttribute("hero", hero);
        return "/editHero";
    }
    @RequestMapping(value="updateHero", method=RequestMethod.POST)
    public String updateHero(String heroID, String heroName, String heroDescription) {
        Hero hero = new Hero();
        hero.setHeroID(Integer.parseInt(heroID));
        hero.setHeroName(heroName);
        hero.setDescription(heroDescription);
        heroDao.updateHero(hero);
        return "redirect:hero";
    }
    @RequestMapping(value="/deleteHero/{heroID}", method=RequestMethod.GET)
    public String deleteHero(@PathVariable("heroID") int id) {
        heroDao.deleteHeroByID(id);
        return "redirect:/hero";
    }
    @RequestMapping(value="organization", method=RequestMethod.GET)
    public String displayOrgs(Model model) {
        List<Organization> orgs = orgDao.getAllOrgs();
        model.addAttribute("orgs", orgs);
        return "organization";
    }
    @RequestMapping(value="addOrg", method=RequestMethod.POST)
    public String addOrg(String orgName, String orgDescription, String orgStreetAddress, String orgCity, String orgState, String orgZipcode) {
        Organization org = new Organization();
        org.setOrgName(orgName);
        org.setDescription(orgDescription);
        org.setStreetAddress(orgStreetAddress);
        org.setCity(orgCity);
        org.setState(orgState);
        org.setZipcode(orgZipcode);
        orgDao.addOrg(org);
        return "redirect:organization";
    }
    @RequestMapping(value="deleteOrg/{orgID}", method=RequestMethod.GET)
    public String deleteOrg(@PathVariable("orgID") int id) {
        orgDao.deleteOrg(id);
        return "redirect:/organization";
    }
    @RequestMapping(value="sighting", method=RequestMethod.GET)
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        model.addAttribute("sightings", sightings);
        return "sighting";
    }
    @RequestMapping(value="reportSighting", method=RequestMethod.GET)
    public String reportSighting(Model model) {
        List<Hero> heros = heroDao.getAllHeros();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("heros", heros);
        model.addAttribute("locations", locations);
        return "reportSighting";
    }
    @RequestMapping(value="addSighting", method=RequestMethod.POST)
    public String addSighting(int heroID, int locationID, String sightingDate) {                
        Hero hero = new Hero();
        hero.setHeroID(heroID);
        Location location = new Location();
        location.setLocationID(locationID);
        Sighting sighting = new Sighting();
        LocalDate date = LocalDate.parse(sightingDate);
        sighting.setDate(date);
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);
        return "redirect:sighting";
    }
    @RequestMapping(value="editSighting/{sightingID}", method=RequestMethod.GET)
    public String editSighting(@PathVariable("sightingID") int sightingID, Model model) {
        Sighting sighting = sightingDao.getSightingByID(sightingID);
        List<Hero> heros = heroDao.getAllHeros();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("heros", heros);
        model.addAttribute("locations", locations);
        model.addAttribute("sightingToEdit", sighting);
        return "editSighting";
    }
    @RequestMapping(value="deleteSighting/{sightingID}", method=RequestMethod.GET)
    public String deleteSighting(@PathVariable("sightingID") int id) {
        sightingDao.deleteSightingByID(id);
        return "redirect:/sighting";
    }
    @RequestMapping(value="location", method=RequestMethod.GET)
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "location";
    }
    @RequestMapping(value="addLocation", method=RequestMethod.POST)
    public String addLocation(String locationName, String locationDescription, String locationStreetAddress, String locationCity, String locationState, String locationZipcode, String locationLatitude, String locationLongitude) {
        Location location = new Location();
        if (!(locationName.trim().length() == 0)) {
            location.setLocationName(locationName);
        }
        if (!(locationDescription.trim().length() == 0)) {
            location.setDescription(locationDescription);
        }
        if (!(locationStreetAddress.trim().length() == 0)) {
            location.setStreetAddress(locationStreetAddress);
        }
        if (!(locationCity.trim().length() == 0)) {
            location.setCity(locationCity);
        }
        if (!(locationState.trim().length() == 0)) {
            location.setState(locationState);
        }
        if (!(locationZipcode.trim().length() == 0)) {
            location.setZipcode(locationZipcode);
        }
        if (!(locationLatitude.trim().length() == 0)) {
            try {
            location.setLatitude(new BigDecimal(locationLatitude));
            } catch (NumberFormatException e) {}
        }
        if (!(locationLongitude.trim().length() == 0)) {
            try {
            location.setLongitude(new BigDecimal(locationLongitude));
            } catch (NumberFormatException e) {}
        }                                        
        locationDao.addLocation(location);
        return "redirect:/location";
    }
    @RequestMapping(value="deleteLocation/{locationID}", method=RequestMethod.GET)
    public String deleteLocation(@PathVariable("locationID") int id) {
        locationDao.deleteLocationByID(id);
        return "redirect:/location";
    }
}
