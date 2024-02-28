package mieten17.services;

import mieten17.models.Country;
import mieten17.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Long getCountryId(String countryName) {
        Country country = countryRepository.findIdByCountry(countryName);
        if (country == null) {
            Country newCountry = new Country();
            newCountry.setCountry(countryName);
            countryRepository.save(newCountry);
            return countryRepository.findIdByCountry(countryName).getId();
        }
        return country.getId();
    }
}
