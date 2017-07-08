package twrog.superhero.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Travis Rogers
 */

@Controller
public class HeroController {
    
    public HeroController() {
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Map<String, Object> model) {
        model.put("message", "Hello from the controller" );
        return "index";
    }
}
