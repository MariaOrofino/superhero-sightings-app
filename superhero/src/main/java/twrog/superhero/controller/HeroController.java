package twrog.superhero.controller;

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
}
