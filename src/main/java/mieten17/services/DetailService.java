package mieten17.services;


import mieten17.models.Detail;
import mieten17.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {

    @Autowired
    private DetailRepository detailRepository;

    public void createDetailOrUpdate(Long objId, int floor, int floors, String balcony, Integer area, int price,
                             int capacity, String countRooms, String service, String comfort,
                             String parking, String textObj) {

        Detail checkDetail = detailRepository.findDetailByObjId(objId);
        if (checkDetail == null) {
            Detail details = new Detail();
            details.setObjId(objId);
            details.setFloor(floor);
            details.setFloors(floors);
            details.setBalcony(balcony);
            details.setArea(area);
            details.setPrice(price);
            details.setCapacity(capacity);
            details.setCountRooms(countRooms);
            details.setService(service);
            details.setComfort(comfort);
            details.setParking(parking);
            details.setTextObj(textObj);
            detailRepository.save(details);
        } else {
            detailRepository.updateDetail( floor, floors, balcony, area, price, capacity, countRooms,
                    service, comfort, parking, textObj, objId);
        }

    }

}
