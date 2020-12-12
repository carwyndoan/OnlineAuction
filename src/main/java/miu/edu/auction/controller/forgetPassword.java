package miu.edu.auction.controller;

import miu.edu.auction.domain.User;
import miu.edu.auction.domain.Verification;
import miu.edu.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import miu.edu.auction.service.VerificationService;
import javax.validation.Valid;

@Controller
public class forgetPassword {
    @Autowired
    UserService userService;
    @Autowired
    VerificationService verificationService;

    @GetMapping("/forget_password")
    public String forgetPassword(@ModelAttribute("user")User user){

      //  System.out.println(user.getEmail());//null
        return "security/forget_password_form";
    }
    @PostMapping("/forget_password" )
    public String postForgetPassword(@ModelAttribute("user") User user, RedirectAttributes
            redirectAttributes) {

        System.out.println("useremail........" + user.getEmail());
        User savedUser = userService.findUserByEmail(user.getEmail());
//        System.out.println(savedUser.getEmail());

        User user1;
        if(savedUser != null ) {

            System.out.println("model = " + "send email...................");
            user1 = userService.saveUserWithVerificationKey(savedUser);

//         model.addAttribute("user", user1);
            redirectAttributes.addFlashAttribute("user",user1);
            System.out.println("user.......................");
            return "redirect:/verification_code/" + user1.getEmail();
        }
        else{
            return "403";
        }


    }
    @GetMapping("/verification_code/{email}")
    public String getVerificationCode(@PathVariable("email") String email,Model model){
        model.addAttribute("email", email);
        return "security/verify";
    }
    @PostMapping("/verification_code")
    public String verifyCode(@RequestParam("email") String userEmail,
                             @RequestParam("code") String verificationcode,
                             @RequestParam("password") String password,
                             @RequestParam("verifyPassword") String verifyPassword,
                             RedirectAttributes redirectAttributes){
    if(!password.equals(verifyPassword)){
        redirectAttributes.addFlashAttribute("error", "password must match");
      return  "redirect:/verification_code/" + userEmail;
    }  else {
        try {
            verificationService.resetPassword(userEmail, verificationcode, password);
            redirectAttributes.addFlashAttribute("success", "You have successfully reset your password");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return  "redirect:/verification_code/" + userEmail ;
        }
    }

        return "redirect:/login";

}

    }

