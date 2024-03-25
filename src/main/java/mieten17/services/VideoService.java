package mieten17.services;

import mieten17.models.Video;
import mieten17.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public void save(Long objId, String path) {
        Video checkVideo = videoRepository.findVideoByObjId(objId);
        if (checkVideo == null) {
            Video video = new Video();
            video.setObjId(objId);
            video.setVideo(path);
            videoRepository.save(video);
        } else {
            // Обновляем БД
            videoRepository.updateVideo(path, objId);
        }
    }
}
