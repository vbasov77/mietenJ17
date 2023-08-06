package mieten17.controllers;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import mieten17.models.Employee;
import mieten17.models.Json;
import mieten17.models.Language;
import mieten17.models.User;
import mieten17.repositories.CoordinatesRepository;
import mieten17.repositories.ObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ObjectController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ObjRepository objRepository;

    @Autowired
    CoordinatesRepository coordinatesRepository;

    @GetMapping("/add_obj")
    public String addAddressPage() {
        return "objects/add_address";
    }

//    @PostMapping("/add_address")
//    public String addAddress(String address, @AuthenticationPrincipal User user) {
//        Obj obj = new Obj();
//        obj.setUserId(user.getId());
//        obj.setAddress(address);
//        objRepository.save(obj);
//        return "objects/add_address";
//    }

    @PostMapping("/add_object")
    @ResponseBody
    public boolean addObject(@RequestBody String data, @AuthenticationPrincipal User user) throws IOException {

//        String coordinates = String.valueOf(request.getParameter("coordinates"));
//        String address = String.valueOf(request.getParameter("address"));
//        Obj obj = new Obj();
//        obj.setUserId(user.getId());
//        obj.setAddress(address);
//        obj.setCoordinates(Collections.singleton(coordinates));
//        objRepository.save(obj);
//        File file = new File("file.json");
//        Employee employee = objectMapper.readValue(file, Employee.class);

//        Json json = objectMapper.readValue(data, Json.class);
//        System.out.println(json.getResponse());
        return false;
    }


}
