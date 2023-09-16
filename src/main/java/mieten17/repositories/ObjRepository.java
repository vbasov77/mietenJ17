package mieten17.repositories;

import jakarta.transaction.Transactional;
import mieten17.models.Obj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjRepository extends JpaRepository<Obj, Long> {

    Obj findObjById(Long id);

    @Transactional
    @Query(value = "select o.id, o.published, o.user_id, d.title, d.price, d.count_rooms, d.capacity," +
            "(select a.locality from addresses a where obj_id = o.id) " +
            "address, (select i.path from images i where obj_id = o.id order by i.id limit 1) " +
            "path from objects o left join addresses a on o.id = a.obj_id " +
            "left join details d on o.id = d.obj_id where a.locality_id = :localityId and o.published = :published",
            nativeQuery = true)
    List<Obj> getAllObj(Long localityId, Integer published);

    @Transactional
    @Query(value = "select o.id, d.title, d.price, d.count_rooms, d.capacity, o.published, o.user_id," +
            "(select a.locality from addresses a where obj_id = o.id)address, " +
            "(select i.path from images i where obj_id = o.id order by i.id limit 1)image " +
            "from objects o " +
            "left join addresses a on o.id = a.obj_id " +
            "left join rules r on o.id = r.obj_id " +
            "left join details d on o.id = d.obj_id " +
            "where a.locality_id like :localityId and o.published = 1 and d.count_rooms like :countRoomsDb " +
            "and d.capacity >= :capacityDb and d.price >= :priceFromDb and d.price <= :priceToDb " +
            "and d.area >= :areaFromDb and d.area <= :areaToDb and d.balcony like :balconyDb " +
            "and d.floor not like :notFirstDb and r.children like :childrenDb and r.animals like :animalsDb " +
            "and r.smoking like :smokingDb and r.party like :partyDb and r.documents like :documentsDb and r.monthly like " +
            ":monthlyDb", nativeQuery = true)
    List<Obj> getFilterObj(Long localityId, Integer capacityDb, String countRoomsDb, Integer priceFromDb, Integer priceToDb,
                           Float areaFromDb, Float areaToDb, String balconyDb, Integer notFirstDb, /*String notEndDb,*/
                           String childrenDb, String animalsDb, String smokingDb, String partyDb, String documentsDb,
                           String monthlyDb);


    @Transactional
    @Query(value = "select o.user_id, o.id, o.address, o.published, d.title, d.price, d.count_rooms, d.capacity, " +
            "(select i.path from images i where obj_id = o.id order by i.id limit 1) path " +
            "from objects o left join addresses a on o.id = a.obj_id left join details d " +
            "on o.id = d.obj_id where o.user_id = :userId", nativeQuery = true)
    List<Obj> getMyObj(Long userId);

    @Transactional
    @Modifying
    @Query(value = "update objects set published = 1 where id = :id", nativeQuery = true)
    void updatePublished(Long id);

    @Transactional
    @Modifying
    @Query(value = "update objects set published = 0 where id = :id", nativeQuery = true)
    void takeOff(Long id);

    void deleteById(Long id);
}