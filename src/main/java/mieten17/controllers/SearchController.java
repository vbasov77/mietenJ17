package mieten17.controllers;


import jakarta.servlet.http.HttpServletRequest;
import mieten17.models.Locality;
import mieten17.repositories.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    private LocalityRepository localityRepository;

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
}
