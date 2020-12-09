package miu.edu.auction.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log
public class CustomerController {

    @GetMapping("/home")
    public String main(Model model) {



        return "/customer/index"; //view
    }

    @GetMapping("/detail")

    public String detail(Model model) {
        return "/customer/detail";

    }
}
