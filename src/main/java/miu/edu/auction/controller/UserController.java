package miu.edu.auction.controller;

import miu.edu.auction.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class UserController {
    @GetMapping(value = {"/userform"})
    public String loadUserForm(@ModelAttribute("user") User user) {
        return "UserForm";
    }

    @PostMapping(value = {"/saveuser"})
    public String saveUser(){
        return "UserList";
    }
}
