package mieten17.repositories;

import jakarta.transaction.Transactional;
import mieten17.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Video findVideoByObjId(Long objId);

    @Transactional
    @Modifying
    @Query(value = "update video set video = :videoPath where obj_id = :objId",
            nativeQuery = true)
    public void updateVideo(String videoPath, Long objId);
}