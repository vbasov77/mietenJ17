package mieten17.repositories;

import jakarta.transaction.Transactional;
import mieten17.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByObjId(Long objId);

    @Transactional
    @Modifying
    @Query(value = "delete from images where path = :pathName",
            nativeQuery = true)
    void deleteImg(String pathName);
}
