package miu.edu.auction.controller;

import miu.edu.auction.domain.User;
import miu.edu.auction.dto.UserVerificationDTO;
import miu.edu.auction.repository.UserRepository;
import miu.edu.auction.service.DMVService;
import miu.edu.auction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    Logger logger= LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    DMVService dmvService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
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

        logger.info("admin login....................");

        return "security/admin_login_form";
        //return "redirect:/adminhome";
    }
    @GetMapping("/adminhome")
    public String getDispalayAdminPage(Model model){
        logger.error("entering adminhome metthod");
        List<UserVerificationDTO> userVerificationDTOList = new ArrayList<>();
        List<User> userList = userRepository.findByRegistrationVerified(0);

        logger.error("size"+userList.size());
        //userList.forEach(System.out::println);

        userList.forEach(user -> {
            UserVerificationDTO userVerificationDTO = new UserVerificationDTO();
            userVerificationDTO.setName(user.getName());
            userVerificationDTO.setLicenseNumber(user.getDriverLicense());
            userVerificationDTO.setIsVerfied(getVerifiedUser(user.getName(),user.getDriverLicense()));
            userVerificationDTOList.add(userVerificationDTO);
        });
        model.addAttribute("users",userVerificationDTOList);

        userVerificationDTOList.forEach(System.out::println);

        logger.error("getting out adminhome metthod");


        return "admin/list";
    }

    public String getVerifiedUser(String name,  String licence){
        logger.error("entering verification helper method");
        if(dmvService.getDMV(name, licence) != null)
            return "verified";
        return "not verified";
    }
     @GetMapping("/approve")
    public String approve(@RequestParam("id") String lisence){

        logger.error("liscee++++++++++++++="+lisence);

         int updatedUsers = userService.updateByDrivingLiscence(lisence);

         logger.info("Number of updated user after verification....:"+ updatedUsers);

         return "redirect:/admin/adminhome";

     }
    @GetMapping("/decline")
    public String decline(@RequestParam("id") String lisence){
        int deletedUsers=userService.deleteByDriverLicense(lisence);
        logger.info("Number of deleted user ..."+deletedUsers );

        return "redirect:/admin/adminhome";

    }


    @RequestMapping("/login-error")
    public String loginError(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout,
                             Model model) {
        logger.error("error Error method+++++++++++++++" + error);
        logger.error("logout Error method+++++++++++++++" + logout);
        model.addAttribute("loginError", true);
        return "security/login";
    }

}
