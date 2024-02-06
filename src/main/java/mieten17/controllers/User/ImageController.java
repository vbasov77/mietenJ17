package mieten17.controllers.User;


import mieten17.models.Image;
import mieten17.services.ImageService;
import mieten17.services.RandomService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class ImageController {

    @Autowired
    private RandomService randomService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/delete_img", method = RequestMethod.GET)
    public @ResponseBody Object deleteImg(@RequestParam("file") String file) {
        File fil = new File("D:/STUD/Spring/img_for_mieten17/" + file);
        fil.delete();
        imageService.deleteImg(file);
        Map<String, Object> object = new HashMap<>();
        object.put("answer", "ok");

        return object;

    }

    @RequestMapping(value = "/add_img/id{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object addImage(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        if (!file.isEmpty()) {
            try {

                String imgName = randomService.random() + ".jpg";
                StringBuilder fileNames = new StringBuilder();
                Path fileNameAndPath = Paths.get("D:/STUD/Spring/img_for_mieten17/", imgName);
                fileNames.append(imgName);
                Files.write(fileNameAndPath, file.getBytes());

                imageService.createImg(id, imgName);
                List<Image> images = imageService.getImages(id);
                List<String> imgArr = new ArrayList<>();
                for (int i = 0; i < images.size(); i++) {
                    imgArr.add(images.get(i).getPath());
                }
                Map<String, Object> object = new HashMap<>();
                object.put("answer", "ok");
                object.put("fil", imgArr.toString().replaceAll("\\[", "").replaceAll("\\]", ""));

                return object;

            } catch (Exception e) {
                return "error";
            }
        }
        return null;
    }
}

