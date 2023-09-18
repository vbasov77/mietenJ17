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
        /**
         * Поиск по городу
         */

        Long localityId = (Long) session.getAttribute("localityId");
        if (list != null) {
            // Получим данные по выбранному городу и запишем всё в сессию localityId localityName.
            Optional<Locality> loc = localityRepository.findByLocality(list);
            session.setAttribute("localityId", loc.get().getId());
            session.setAttribute("localityName", list);
            localityId = loc.get().getId();
        }

        /**
         * Поиск по количеству комнат
         */
        String countRoomsDb = "%";
        if (countRooms != "") {
            countRoomsDb = (countRooms.equals("студия")) ? "%студия%" : countRooms;
        }

        /**
         * Поиск по количеству человек
         */
        Integer capacityDb = (capacity != null) ? capacity : 0;

        /**
         * Поиск по цене
         */
        Integer priceFromDb = (priceFrom != null) ? priceFrom : 0; // по цене от
        Integer priceToDb = (priceTo != null) ? priceTo : Integer.MAX_VALUE; // по цене до


        /**
         * Поиск по площади недвижимости
         */

        Integer areaFromDb = (areaFrom != null) ? areaFrom : 0; // площадь от
        Integer areaToDb = (areaTo != null) ? areaTo : Integer.MAX_VALUE; // площадь до

        /**
         * Поиск по наличию балкона - лоджии
         */

        String balconyDb = (balcony != null) ? "%" + String.join(",", balcony) + "%" : "%";

        /**
         * Поиск не первый и не последний этажи
         */
        Integer notFirstDb = (notFirst != null) ? 1 : Integer.MAX_VALUE; // не первый
//        String notEndDb = (notEnd != null) ? " d.floors" : null; // не последний -----> НЕ РАБОТАЕТ!!!!!!!!!!!!!!!!!!

        /**
         * Поиск - можно с детьми
         */
        String childrenDb = (children != null) ? "да" : "%";

        /**
         * Поиск - можно с животными
         */
        String animalsDb = (animals != null) ? "да" : "%";

        /**
         * Поиск можно курить
         */
        String smokingDb = (smoking != null) ? "да" : "%";

        /**
         * Поиск - разрешены вечеринки
         */
        String partyDb = (party != null) ? "да" : "%";

        /**
         * Поиск - есть отчётные документы
         */
        String documentsDb = (documents != null) ? "да" : "%";

        /**
         * Возможно ли помесячно
         */
        String monthlyDb = (monthly != null) ? "да" : "%";

        System.out.println(localityId + "\n");
        System.out.println(countRoomsDb + "\n");
        System.out.println(priceFromDb + "\n");
        System.out.println(priceToDb + "\n");
        System.out.println(areaFromDb + "\n");
        System.out.println(areaTo + "\n");
        System.out.println(areaToDb + "\n");
        System.out.println(balconyDb + "\n");
        System.out.println(notFirstDb + "\n");
        System.out.println(childrenDb + "\n");
        System.out.println(animalsDb + "\n");
        System.out.println(smokingDb + "\n");
        System.out.println(partyDb + "\n");
        System.out.println(documentsDb + "\n");
        System.out.println(localityId + "\n");
        List<Obj> objs = objService.getFilterObj(localityId,
                capacityDb, countRoomsDb, priceFromDb, priceToDb,
                areaFromDb, areaToDb, balconyDb, notFirstDb, /*notEndDb,*/childrenDb,
                animalsDb, smokingDb, partyDb, documentsDb, monthlyDb);
        if (objs.size() < 1) {
            objs = null;
        }
        System.out.println(objs);

        model.addAttribute("data", objs);


        return "front";
    }
}
