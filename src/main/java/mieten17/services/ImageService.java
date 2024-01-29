package mieten17.services;

import mieten17.models.Image;
import mieten17.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void createImg(Long objId, String pathImg) {
        Image image = new Image();
        image.setObjId(objId);
        image.setPath(pathImg);
        imageRepository.save(image);
    }

    public List<Image> getImages(Long objId) {
        return imageRepository.findAllByObjId(objId);
    }

    public void deleteImg(String imgPath) {
        imageRepository.deleteImg(imgPath);
    }

}
