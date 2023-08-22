package mieten17.repositories;

import jakarta.transaction.Transactional;
import mieten17.models.Obj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ObjRepository extends JpaRepository<Obj, Long> {
    Obj findObjById(Long id);


    @Transactional
    @Modifying
    @Query(value = "update objects set published = :published where user_id = :userId",
            nativeQuery = true)
    public void updatePublished(int published, Long userId);


}