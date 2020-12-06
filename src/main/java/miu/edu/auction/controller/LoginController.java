package miu.edu.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

//    @RequestMapping("/login")
//    public String login() {
//        System.out.println("Login.....");
//        return "login";
//    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        System.out.println("loginError.....");
        model.addAttribute("loginError", true);
        return "/login";
    }


}
