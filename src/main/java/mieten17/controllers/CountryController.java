package mieten17.controllers;

import mieten17.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;


    @GetMapping(value = "/get_country_id")
    @ResponseBody
    public Long getCountryId(String country) {
        Long id = countryService.getCountryId(country);
        return id;
    }
}
