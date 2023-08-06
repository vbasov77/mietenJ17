package mieten17.controllers;

import jakarta.servlet.http.HttpSession;
import mieten17.models.Locality;
import mieten17.repositories.LocalityRepository;
import mieten17.services.LocalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class LocalityController {
    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private LocalityService localityService;

    @PostMapping("/get_city")
    public String getCityId(String list, HttpSession session, Model model) {
        Optional<Locality> loc = localityRepository.findByLocality(list);
        if (loc.isEmpty()) {
            model.addAttribute("message", "Мы не нашли объявлений по вашему городу." +
                    " Измените местоположение...");
            return "locality/locality";
        } else {
            session.setAttribute("localityId", loc.get().getId());
            session.setAttribute("localityName", list);
            String name = (String) session.getAttribute("localityName");
            model.addAttribute("locality", name);
            return "redirect:/";
        }
    }

    @GetMapping("/update_location")
    public String updateLocation(HttpSession session) {
        session.removeAttribute("localityId");
        session.removeAttribute("localityName");
        return "redirect:/";
    }

    @GetMapping("/get_locality_id")
    @ResponseBody
    public Long getLocalityId(String locality, Long countryId) {
        Long id = localityService.getLocalityId(locality, countryId);
        return id;
    }


}


