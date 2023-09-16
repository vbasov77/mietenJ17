package mieten17.services;

import mieten17.models.Obj;
import mieten17.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjService {

    @Autowired
    private ObjRepository objRepository;

    public void save(Obj obj) {
        objRepository.save(obj);
    }

    public List<Obj> getAllObj(Long localityId, Integer published) {
        List<Obj> allObj = objRepository.getAllObj(localityId, published);
        return allObj;
    }

    public Obj getObjById(Long id) {
        Obj obj = objRepository.findObjById(id);
        return obj;
    }

    public List<Obj> getFilterObj(Long localityId, Integer capacityDb, String countRoomsDb, Integer priceFromDb, Integer priceToDb,
                                  Float areaFromDb, Float areaToDb, String balconyDb, Integer notFirstDb,
            /*String notEndDb,*/String childrenDb, String animalsDb, String smokingDb, String partyDb, String documentsDb, String monthlyDb) {
        return objRepository.getFilterObj(localityId, capacityDb, countRoomsDb, priceFromDb, priceToDb, areaFromDb, areaToDb,
                balconyDb, notFirstDb, /*notEndDb,*/ childrenDb, animalsDb, smokingDb, partyDb, documentsDb, monthlyDb);
    }
    public List<Obj> getMyObj(Long userId){
        return objRepository.getMyObj(userId);
    }

    public void updatePublished(Long id){
        objRepository.updatePublished(id);
    }
    public void takeOff(Long id){
        objRepository.takeOff(id);
    }

    public void deleteObjById(Long id){
        objRepository.deleteById(id);
    }

}