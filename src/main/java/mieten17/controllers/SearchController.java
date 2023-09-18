package mieten17.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mieten17.models.Locality;
import mieten17.models.Obj;
import mieten17.repositories.LocalityRepository;
import mieten17.services.ObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private ObjService objService;

    @GetMapping("/autocomplete")
    @ResponseBody
    public String autocomplete(HttpServletRequest request) {
        /*
         * Получаем массив городов по части слова и преобразуем в строку
         */
        String locality = String.valueOf(request.getParameter("data"));
        Optional<List<Locality>> localities = localityRepository.findLocalitiesByLocalityStartsWith(locality);
        List<String> arr = new ArrayList();
        for (int i = 0; i < localities.get().size(); i++) {
            arr.add(String.valueOf(localities.get().get(i).getLocality()));
        }
        return String.join(",", arr);// Перевели массив в строку.;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam(name = "list", required = false) String list,
                         @RequestParam(name = "capacity", required = false) Integer capacity,
                         @RequestParam(name = "count_rooms", required = false) String countRooms,
                         @RequestParam(name = "price_from", required = false) Integer priceFrom,
                         @RequestParam(name = "price_to", required = false) Integer priceTo,
                         @RequestParam(name = "area_from", required = false) Integer areaFrom,
                         @RequestParam(name = "area_to", required = false) Integer areaTo,
                         @RequestParam(name = "balcony[]", required = false) List<String> balcony,
                         @RequestParam(name = "not_first", required = false) String notFirst,
                         @RequestParam(name = "not_end", required = false) String notEnd,
                         @RequestParam(name = "children", required = false) String children,
                         @RequestParam(name = "animals", required = false) String animals,
                         @RequestParam(name = "smoking", required = false) String smoking,
                         @RequestParam(name = "party", required = false) String party,
                         @RequestParam(name = "documents", required = false) String documents,
                         @RequestParam(name = "monthly", required = false) String monthly,
                         Model model, HttpSession session
    ) {

        List<Obj> objs = objService.getFilterObj(list,
                capacity, countRooms, priceFrom, priceTo,
                areaFrom, areaTo, balcony, notFirst, /*notEndDb,*/children,
                animals, smoking, party, documents, monthly, session);
        if (objs.size() < 1) {
            objs = null;
        }
        System.out.println(objs);

        model.addAttribute("data", objs);


        return "front";
    }
}
