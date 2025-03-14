import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactForwardingController {

    @GetMapping({"/", "/add-coupon", "/claim-coupon"})
    public String forwardToReact() {
        return "forward:/index.html";
    }
}
