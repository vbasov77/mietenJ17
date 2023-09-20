package mieten17.services;

import jakarta.servlet.http.HttpSession;
import mieten17.models.Obj;
import mieten17.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjService {


    @Autowired
    private FilterService filterService;

    @Autowired
    private ObjRepository objRepository;

    public void save(Obj obj) {
        objRepository.save(obj);
    }

    public List<Obj> getAllObjByLocalityId(String localityId, Integer published) {
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

    public List<Obj> getFilterObj(String localityName, Integer capacity, String countRooms, Integer priceFrom, Integer priceTo,
                                  Integer areaFrom, Integer areaTo, List<String> balcony, Integer notFirst,
            /*String notEnd,*/String children, String animals, String smoking, String party, String documents, String monthly, HttpSession session) {


        String localityId = filterService.localityIdSession(localityName, session);// по городу

        String countRoomsDb = filterService.countRoomsSession(countRooms, session); // по количеству комнат

        Integer capacityDb = filterService.capacitySession(capacity, session);// Поиск по количеству человек

        Integer priceFromDb = filterService.priceFromSession(priceFrom, session); // по цене от
        Integer priceToDb = filterService.priceToDbSession(priceTo, session); // по цене до

        Integer areaFromDb = filterService.areaFromSession(areaFrom, session); // площадь от
        Integer areaToDb = filterService.areaToDbSession(areaTo, session); // площадь до

        String balconyDb = filterService.balconySession(balcony, session); // Поиск по наличию балкона - лоджии
        Integer notFirstDb = filterService.notFirstSession(notFirst, session); // не первый
//        String notEndDb = (notEnd != null) ? " d.floors" : null; // не последний -----> НЕ РАБОТАЕТ!!!!!!!!!!!!!!!!!!

        String childrenDb = filterService.childrenSession(children, session);// можно с детьми
        String animalsDb = filterService.animalsSession(animals, session);// можно с животными
        String smokingDb = filterService.smokingSession(smoking, session);// можно курить
        String partyDb = filterService.partySession(party, session);// разрешены вечеринки
        String documentsDb = filterService.documentsSession(documents, session);// есть отчётные документы
        String monthlyDb = filterService.monthlySession(monthly, session);// есть отчётные документы



//        System.out.println(localityId);
//        System.out.println(capacityDb);
//        System.out.println("countRoomsDb - " + countRoomsDb.isEmpty());
//        System.out.println("countRooms - " + countRooms.isEmpty());
//        System.out.println(priceFromDb);
//        System.out.println(priceToDb);
//        System.out.println(areaFromDb);
//        System.out.println(areaToDb);
//        System.out.println(balconyDb);
//        System.out.println(notFirstDb);
//        System.out.println(childrenDb);
//        System.out.println(animalsDb);
//        System.out.println(smokingDb);
//        System.out.println(partyDb);
//        System.out.println(documentsDb);
//        System.out.println(monthlyDb);
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
