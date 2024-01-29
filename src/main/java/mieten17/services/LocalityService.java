package mieten17.services;


import mieten17.models.Locality;
import mieten17.repositories.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalityService {

    @Autowired
    private LocalityRepository localityRepository;

    public Long getLocalityId(String localityName, Long countryId) {
        Locality locality = localityRepository.findLocalityByLocality(localityName);

        if (locality == null) {
            Locality loc = new Locality();
            loc.setLocality(localityName);
            loc.setCountryId(countryId);
            localityRepository.save(loc);

            Long newLocality = localityRepository.findLocalityByLocality(localityName).getId();
            return newLocality;
        }
        return locality.getId();
    }

}
