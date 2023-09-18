package mieten17.repositories;

import jakarta.transaction.Transactional;
import mieten17.models.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    Detail findDetailByObjId(Long objId);

    @Transactional
    @Modifying
    @Query(value = "update details set floor = :floor, floors = :floors, balcony = :balcony, " +
            "area = :area,  price = :price, capacity = :capacity, count_rooms = :countRooms, service = :service, " +
            "comfort = :comfort, parking = :parking, text_obj = :textObj where obj_id = :objId",
            nativeQuery = true)
    public void updateDetail(int floor, int floors, String balcony, Integer area, int price, int capacity,
                             String countRooms, String service, String comfort, String parking, String textObj,
                             Long objId);


}
