package mieten17.controllers;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер на главную страницу.
 */

@Controller
@AllArgsConstructor
public class FrontController {
    @GetMapping("/")
    public String front(HttpSession session, Model model) {

        String sess = (String) session.getAttribute("localityName");
        if (sess == null) {
            return "locality/locality";
        } return "front";

    }

}
