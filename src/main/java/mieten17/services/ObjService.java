package mieten17.services;

import jakarta.servlet.http.HttpSession;
import mieten17.models.Locality;
import mieten17.models.Obj;
import mieten17.repositories.LocalityRepository;
import mieten17.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjService {

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private ObjRepository objRepository;

    public void save(Obj obj) {
        objRepository.save(obj);
    }

    public List<Obj> getAllObjByLocalityId(Long localityId, Integer published) {
        List<Obj> allObj = objRepository.getAllObjByLocalityId(localityId, published);
        return allObj;
    }

    public List<Obj> findObjsByPublished(int published) {
        return objRepository.findObjsByPublished(published);
    }

    public Obj getObjById(Long id) {
        Obj obj = objRepository.findObjById(id);
        return obj;
    }

    public List<Obj> getFilterObj(String list, Integer capacity, String countRooms, Integer priceFrom, Integer priceTo,
                                  Integer areaFrom, Integer areaTo, List <String> balcony, String notFirst,
            /*String notEnd,*/String children, String animals, String smoking, String party, String documents, String monthly, HttpSession session) {

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


        return objRepository.getFilterObj(localityId, capacityDb, countRoomsDb, priceFromDb, priceToDb, areaFromDb, areaToDb,
                balconyDb, notFirstDb, /*notEndDb,*/ childrenDb, animalsDb, smokingDb, partyDb, documentsDb, monthlyDb);
    }

    public List<Obj> getMyObj(Long userId) {
        return objRepository.getMyObj(userId);
    }

    public void updatePublished(Long id) {
        objRepository.updatePublished(id);
    }

    public void takeOff(Long id) {
        objRepository.takeOff(id);
    }

    public void deleteObjById(Long id) {
        objRepository.deleteById(id);
    }

}
