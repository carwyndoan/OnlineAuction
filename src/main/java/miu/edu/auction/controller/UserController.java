package miu.edu.auction.controller;

import miu.edu.auction.domain.User;
import miu.edu.auction.domain.Verification;
import miu.edu.auction.service.UserService;
import miu.edu.auction.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/userform"})
    public String loadUserForm(@ModelAttribute("user") User user) {
        return "UserForm";
    }

    @PostMapping(value = {"/saveuser"})
    public String saveUser(User user) {
        User savedUser = userService.saveUserWithVerificationKey(user);
        return "success";
    }
}
