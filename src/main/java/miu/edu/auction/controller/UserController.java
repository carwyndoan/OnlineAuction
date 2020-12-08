package miu.edu.auction.controller;

import miu.edu.auction.domain.User;
import miu.edu.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/userform"})
    public String loadUserForm(@ModelAttribute("user") User user) {
        return "/registration/UserForm";
    }

    @PostMapping(value = {"/saveuser"})
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/registration/UserForm";

//        String[] errors = bindingResult.getSuppressedFields();

        User savedUser = userService.saveUserWithVerificationKey(user);
        return "/registration/success";
    }

    @GetMapping("/edituser/{id}")
    public String edit(@PathVariable int id, Model model) {
        userService.findById(id).ifPresent(u -> model.addAttribute("user", u));
        return "registration/EditUser";
    }

    @PostMapping(value = {"/updateuser"})
    public String updateUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/registration/EditUser";
        userService.saveUser(user);
        return "/registration/success";
    }

    @RequestMapping("/listuser")
    public String list(@RequestParam("size") int pageSize, @RequestParam("number") int pageNumber, Model model) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> pUser = userService.findUserListPaging(pageable);
        model.addAttribute("list", pUser.getContent());
        return "registration/list";
    }


}
