package apap.tugas.sivaksin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class baseController {

    @GetMapping("/")
    private String home() {
        return "home";
    }

}