package cosmic.comix;

/**
 * Created by NSchneier on 4/15/2015.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "comix";
    }
}