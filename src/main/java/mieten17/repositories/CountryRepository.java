package mieten17.repositories;

import mieten17.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findIdByCountry(String name);
}
