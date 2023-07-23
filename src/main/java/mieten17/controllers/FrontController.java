package mieten17.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class FrontController {

    @GetMapping("/")
    public String front() {
        return "front";
    }


}
