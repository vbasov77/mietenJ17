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
            Country countr = new Country();
            countr.setCountry(countryName);
            countryRepository.save(countr);
            Long newCountryId = countryRepository.findIdByCountry(countryName).getId();
            return newCountryId;
        }
        return country.getId();
    }
}
