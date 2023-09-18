package mieten17.controllers;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mieten17.models.Obj;
import mieten17.models.User;
import mieten17.services.ObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Контроллер на главную страницу.
 */

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class FrontController {

    @Autowired
    private ObjService objService;

    @GetMapping("/")
    public String front(HttpSession session, Model model, @AuthenticationPrincipal User user) {
        String sess = (String) session.getAttribute("localityName");
        if (sess == null) {
            return "locality/locality";
        }
        Long localityId = (Long) session.getAttribute("localityId");
        Integer published = 1;
        List<Obj> objects = objService.getAllObj(localityId, published);
        if (objects.size() < 1)
            objects = null;
        model.addAttribute("data", objects);

        return "front";

    }

}
