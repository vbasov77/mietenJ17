package mieten17.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import mieten17.models.*;
import mieten17.repositories.*;
import mieten17.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
public class ObjectController {
    public static final String LOCALITY = "locality";
    public static final String COUNTRY = "country";
    public static final String STREET = "street";
    public static final String HOUSE = "house";

    @Autowired
    private VideoService videoService;
    @Autowired
    private LocalityService localityService;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private DetailService detailService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AddressService addressService;


    @Autowired
    private ObjService objService;

    @Autowired
    private ObjRepository objRepository;

    @Autowired
    CoordinatesRepository coordinatesRepository;

    @GetMapping("/auth/add_obj")
    public String addAddressPage() {
        return "objects/add_address";
    }


    @PostMapping("/auth/add_object")
    @ResponseBody
    public Long addObject(@RequestBody String data, @AuthenticationPrincipal User user) throws IOException {
        DocumentContext response = JsonPath.parse(data);

        String coordinatesStr = response.read("$.response.GeoObjectCollection.featureMember[0].GeoObject.Point.pos");

        // Для дальнейшей читабельности нужно перевернуть порядок координат местами и записать в БД
        String[] coordinatesArr = coordinatesStr.split(" ");
        String coordinates = coordinatesArr[1] + " " + coordinatesArr[0];

        // Вытаскиваем адрес для записи в БД
        String address = response.read("$.response.GeoObjectCollection.metaDataProperty.GeocoderResponseMetaData.request");

        Obj obj = new Obj();
        obj.setUserId(user.getId());
        obj.setAddress(address);
        obj.setPublished(0);
        obj.setCoordinates(Collections.singleton(coordinates));

        List object =
                response.read("$.response.GeoObjectCollection.featureMember[0]." +
                        "GeoObject.metaDataProperty.GeocoderMetaData.Address.Components");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Kind> kinds = objectMapper.readValue(object.toString(), new TypeReference<List<Kind>>() {
        });
        String locality = kinds.stream().filter(kind -> kind.getKind().equals(LOCALITY)).map(kind -> kind.getName()).findFirst().orElse("Ошибка " + LOCALITY);
        String country = kinds.stream().filter(kind -> kind.getKind().equals(COUNTRY)).map(kind -> kind.getName()).findFirst().orElse("Ошибка " + COUNTRY);
        String street = kinds.stream().filter(kind -> kind.getKind().equals(STREET)).map(kind -> kind.getName()).findFirst().orElse("Ошибка " + STREET);
        String house = kinds.stream().filter(kind -> kind.getKind().equals(HOUSE)).map(kind -> kind.getName()).findFirst().orElse("Ошибка " + HOUSE);
        String search = "Ошибка";
        if (locality.toLowerCase().indexOf(search.toLowerCase()) != -1
                || country.toLowerCase().indexOf(search.toLowerCase()) != -1
                || street.toLowerCase().indexOf(search.toLowerCase()) != -1
                || house.toLowerCase().indexOf(search.toLowerCase()) != -1) {
            return -1L;
        }
        Long id = objRepository.save(obj).getId();
        Long countryId = countryService.getCountryId(country);
        Long localityId = localityService.getLocalityId(locality, countryId);
        String addressStr = street + ", " + house;
        Address shortAddress = new Address();
        shortAddress.setObjId(id);
        shortAddress.setCountryId(countryId);
        shortAddress.setLocalityId(localityId);
        shortAddress.setLocality(addressStr);
        addressService.save(shortAddress);

        return id;
    }

    @GetMapping("/auth/edit_obj/id{id}")
    public String editObjPage(@PathVariable Long id, Model model) {
        String regex = "\\[|\\]";
        model.addAttribute("locality", id);
        Obj obj = objService.getObjById(id);
        Detail detail = detailRepository.findDetailByObjId(id);
        Rule rule = ruleRepository.findRuleByObjId(id);
        String address = obj.getAddress().toString();
        if (detail != null) {
            int price = detail.getPrice();
            Integer area = detail.getArea();
            int floor = detail.getFloor();
            int floors = detail.getFloors();
            String balcony = detail.getBalcony();
            String parking = detail.getParking();
            String countRooms = detail.getCountRooms();
            int capacity = detail.getCapacity();
            String service = detail.getService();
            String comfort = detail.getComfort();
            String textObj = detail.getTextObj().toString();
            if (textObj.equals("null")) {
                textObj = "";
            }
            model.addAttribute("area", area);
            model.addAttribute("price", price);
            model.addAttribute("floor", floor);
            model.addAttribute("floors", floors);
            model.addAttribute("balcony", balcony);
            model.addAttribute("parking", parking);
            model.addAttribute("countRooms", countRooms);
            model.addAttribute("capacity", capacity);
            model.addAttribute("service", service);
            model.addAttribute("comfort", comfort);
            model.addAttribute("text_obj", textObj);

        }
        if (rule != null) {
            String children = rule.getChildren();
            String animals = rule.getAnimals().toString();
            String smoking = rule.getSmoking().toString();

            String party = rule.getParty().toString();
            String documents = rule.getDocuments().toString();
            String monthly = rule.getMonthly().toString();
            model.addAttribute("children", children);
            model.addAttribute("animals", animals);
            model.addAttribute("smoking", smoking);
            model.addAttribute("party", party);
            model.addAttribute("documents", documents);
            model.addAttribute("monthly", monthly);
        }

        String video = obj.getVideo().toString().replaceAll(regex, "");
        List<Image> img = imageRepository.findAllByObjId(obj.getId());
        List<String> images = new ArrayList<>();
        if (!img.isEmpty()) {
            for (int i = 0; i < img.size(); i++) {
                images.add(img.get(i).getPath());
            }
        } else {
            images = null;
        }
        model.addAttribute("id", obj.getId());
        model.addAttribute("address", address);
        model.addAttribute("video", video);
        model.addAttribute("images", images);

        return "objects/edit_obj";
    }

    @PostMapping("/auth/publish")
    @ResponseBody
    public Object published(@RequestParam Long id) {
        Map<String, Object> object = new HashMap<>();
        objService.updatePublished(id);
        object.put("answer", "ok");
        return object;
    }

    @PostMapping("/auth/take_off")
    @ResponseBody
    public Object takeOff(@RequestParam Long id) {
        Map<String, Object> object = new HashMap<>();
        objService.takeOff(id);
        object.put("answer", "ok");
        return object;
    }

    @PostMapping("/auth/delete_obj")
    @ResponseBody
    public Object deleteObj(@RequestParam Long id){
        objService.deleteObjById(id);
        Map<String, Object> object = new HashMap<>();
        object.put("answer", "ok");
        return object;
    }

    @PostMapping("/auth/edit_obj/id{id}")
    @ResponseBody
    public Object editObj(
                          @RequestParam int price,
                          @RequestParam Integer area,
                          @RequestParam int floor,
                          @RequestParam int floors,
                          @RequestParam("balcony[]") List<String> balcony,
                          @RequestParam("parking[]") List<String> parking,
                          @RequestParam String countRooms,
                          @RequestParam int capacity,
                          @RequestParam("service[]") List<String> service,
                          @RequestParam("comfort[]") List<String> comfort,
                          @RequestParam String children,
                          @RequestParam String animals,
                          @RequestParam String smoking,
                          @RequestParam String party,
                          @RequestParam String documents,
                          @RequestParam String monthly,
                          @RequestParam String textObj,
                          @RequestParam String video,
                          @PathVariable Long id) {
        Detail checkDetails = detailRepository.findDetailByObjId(id);
        ruleService.createRules(id, children, animals, smoking, party,
                documents, monthly);


        String balconyStr = String.join(",", balcony); // Из массива в строку
        String parkingStr = String.join(",", parking);
        String serviceStr = String.join(",", service);
        String comfortStr = String.join(",", comfort);

        detailService.createDetailOrUpdate(id, floor, floors, balconyStr, area, price, capacity, countRooms,
                serviceStr, comfortStr, parkingStr, textObj);

        if (!video.isEmpty()) {
            videoService.save(id, video);
        }

        Map<String, Object> object = new HashMap<>();
        object.put("answer", "ok");
        return object;
    }

    @RequestMapping("/obj/id{id}")
    public String viewObj(@PathVariable Long id, Model model) {
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
    }

    @GetMapping("/auth/my_obj")

    public String myObjects(@AuthenticationPrincipal User user, Model model) {
        List<Obj> objs = objService.getMyObj(user.getId());
        model.addAttribute("data", objs);
        return "objects/my";
    }






}
