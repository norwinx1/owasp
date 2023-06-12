package ch.bbw.owasp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Slf4j
public class OwaspController {
    @RequestMapping("/")
    String index(Principal principal) {
        log.info("Serving index page for user: {}", principal.getName());
        return "index";
    }

    @RequestMapping("/login")
    String login() {
        log.info("Serving login page");
        return "login";
    }
}
