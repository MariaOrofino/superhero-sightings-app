package twrog.superhero.controller;

import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addSighting(String heroSightingSelection, String locationSightingSelection, String sightingDate) {
        LocalDate date = LocalDate.parse(sightingDate);
        int heroID = Integer.parseInt(heroSightingSelection);
        int locationID = Integer.parseInt(locationSightingSelection);
        //REMOVE THE FOLLOWING SECTION ONCE ADDSIGHTING DAO METHOD UPDATED TO USE IDS INSTEAD OF OBJECTS
        Hero hero = new Hero();
        hero.setHeroID(heroID);
        Location location = new Location();
        location.setLocationID(locationID);
        //
        Sighting sighting = new Sighting();
        sighting.setDate(date);
        sighting.setHero(hero);
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);
        return "redirect:sighting";
    }
    @RequestMapping(value="location", method=RequestMethod.GET)
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "location";
    }
    @RequestMapping(value="addLocation", method=RequestMethod.POST)
    public String addLocation(String locationName, String locationDescription, String locationStreetAddress, String locationCity, String locationState, String locationZipcode, int locationLatitude, int locationLongitude) {
        Location location = new Location();
        location.setLocationName(locationName);
        location.setDescription(locationDescription);
        location.setStreetAddress(locationStreetAddress);
        location.setCity(locationCity);
        location.setState(locationState);
        location.setZipcode(locationZipcode);
        location.setLatitude(locationLatitude);
        location.setLongitude(locationLongitude);
        locationDao.addLocation(location);
        return "redirect/location";
    }
}
