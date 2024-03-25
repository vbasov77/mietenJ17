package mieten17.controllers;

import mieten17.models.Image;
import mieten17.models.Obj;
import mieten17.repositories.ImageRepository;
import mieten17.services.ObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ObjController {
    @Autowired
    private ObjService objService;
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping("/obj/id{id}")
    public String viewObj(@PathVariable Long id, Model model) {
        try {
            Obj obj = objService.getObjById(id);
            List<Image> img = imageRepository.findAllByObjId(id);
            List<String> images = new ArrayList<>();
            if (!img.isEmpty()) {
                for (int i = 0; i < img.size(); i++) {
                    images.add(img.get(i).getPath());
                }
            } else {
                images = null;
            }
            model.addAttribute("data", obj);
            model.addAttribute("images", images);
            return "objects/view_obj";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "errors/error_not_found";
        }

    }
}
