package mieten17.services;

import mieten17.models.Detail;
import mieten17.models.User;
import mieten17.repositories.DetailRepository;
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
public class DetailServiceTest {

    @InjectMocks
    private DetailService detailService;

    @Mock
    private DetailRepository detailRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void TestCreateDetailOrUpdate() {
        //Тест на добавление новых деталей
        Detail newDetail = new Detail();
        newDetail.setObjId(1L);
        detailRepository.save(newDetail);
        Mockito.verify(detailRepository, Mockito.times(1)).save(newDetail);

        //Тест на поиск деталей по objId
        when(detailRepository.findDetailByObjId(1L)).thenReturn(newDetail);
        Detail foundDetail = detailRepository.findDetailByObjId(1L);
        assertEquals(1L, foundDetail.getObjId());

        // Тест на обновление деталей
        detailRepository.updateDetail(1, 1, "", 4, 1200,
                1, "", "", "", "", "", 1L);

        Mockito.verify(detailRepository, Mockito.times(1)).updateDetail(1, 1, "", 4, 1200,
                1, "", "", "", "", "", 1L);


    }
}
