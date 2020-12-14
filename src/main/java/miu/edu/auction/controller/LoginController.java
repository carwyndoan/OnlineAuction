package miu.edu.auction.controller;

import miu.edu.auction.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoginController {
    Logger logger= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/index")
    public String index() {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
       if(auth.getAuthorities().toString().contains("ROLE_ADMIN")){
           logger.error("found admin"+auth.getAuthorities().toString());
           return "redirect:/admin/adminhome";

       }
        else if(auth.getAuthorities().toString().contains("ROLE_SELLER")) {

            logger.error("seller -----" + auth.getAuthorities().toString());

            // redirect to  seller landing page should be provided here

        }
       else if(auth.getAuthorities().toString().contains("ROLE_USER")) {

           logger.error("customer ------" + auth.getAuthorities().toString());

           // redirect to  customer landing page should be provided here


       }
        logger.error("else ------" + auth.getAuthorities().toString());


        return "index";
    }

    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("error", errorMessge);
        return "security/login";
    }


    @RequestMapping("/login-error")
    public String loginError(Model model) {
        System.out.println("loginError.....");
        model.addAttribute("loginError", true);
        return "security/login";
    }
}
