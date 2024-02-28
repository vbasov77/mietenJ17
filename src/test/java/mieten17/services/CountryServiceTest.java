package mieten17.services;


import mieten17.models.Country;
import mieten17.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCountryId() {
        String countryName = "Россия";
        Long id = 1L;
        Country country = new Country();
        country.setCountry(countryName);
        country.setId(id);

        if (countryName == null) {
            Country newCountry = new Country();
            newCountry.setCountry("Россия");
            countryRepository.save(newCountry);
            Mockito.verify(countryRepository, Mockito.times(1)).save(newCountry);
        } else {
            when(countryRepository.findIdByCountry(countryName)).thenReturn(country);
            Long countryFoundId = countryService.getCountryId(countryName);
            assertEquals(id, countryFoundId);
        }

    }

}
