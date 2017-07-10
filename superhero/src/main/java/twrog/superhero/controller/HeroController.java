package twrog.superhero.controller;

import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twrog.superhero.service.Service;

/**
 *
 * @author Travis Rogers
 */

@Controller
public class HeroController {
    Service service;
    
    @Inject
    public HeroController(Service service) {
        this.service = service;
    }
    public HeroController() {
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Map<String, Object> model) {
        model.put("message", "Hello from the controller" );
        return "index";
    }
}
