package mieten17.services;

import mieten17.models.Obj;
import mieten17.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjService {
    @Autowired
    private ObjRepository objRepository;

    public void save(Obj obj) {
        objRepository.save(obj);
    }


}
