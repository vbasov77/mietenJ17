package mieten17.controllers;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mieten17.models.Filter;
import mieten17.models.Obj;
import mieten17.services.FilterService;
import mieten17.services.ObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


/**
 * Контроллер на главную страницу.
 */

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class FrontController {
    @Autowired
    private FilterService filterService;

    @Autowired
    private ObjService objService;

    @GetMapping("/")
    public String front(HttpSession session, Model model) {
        Filter filter = filterService.getFilter(session);
        List<Obj> objs = filterService.variablesByFilter(filter, session);
        if(objs.size() < 1){
            objs = null;
        }
        String img = objs.get(0).getPathStrOne();
        model.addAttribute("data", objs);
        model.addAttribute("filter", filter);
        return "front";
    }

}
