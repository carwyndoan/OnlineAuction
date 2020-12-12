package miu.edu.auction.service.impl;

import lombok.Setter;
import miu.edu.auction.domain.User;
import miu.edu.auction.domain.Verification;
import miu.edu.auction.repository.UserRepository;
import miu.edu.auction.repository.VerificationRepository;
import miu.edu.auction.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {
     @Autowired
     VerificationRepository verificationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Verification> findByUser(int user_id) {
     //  return verificationRepository.findByUser_id(user_id);
        return null;
    }

//    @Override
//    public Verification saveVerification(Verification verification) {
//        return verificationRepository.save(verification);
//    }

    @Override
    public Verification findByUserAndType(int user_id, int type) {
        return null;
    }

    @Override
    public void resetPassword(String email, String verificationcode, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        System.out.println("user.............." + user.getEmail());
        if (user != null) {
          Verification verification =
                    verificationRepository.findByUser(user);
//            //                findVerificationsByUserAndCodeOrderByGenerated_timeDesc(user);
//
//          //  Verification verification = verifications.get(0);
//            // check the time here
//            // check if it expires

           LocalDateTime verificationCreatedDate = verification.getGenerated_time();
            LocalDateTime currentDate = LocalDateTime.now();
            System.out.println("time difference= "+ChronoUnit.MINUTES.between(verificationCreatedDate, currentDate));

//
//            // it expired therefore generate a new one and send it
          if((ChronoUnit.MINUTES.between(verificationCreatedDate, currentDate) > 10)){
              throw new Exception("The verification code you entered has expired. request a new code.");
           }
          if(!verification.getCode().equals(verificationcode)){
                throw new Exception("Verification Code miss match .");
            }else {

            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);

          }

       }else {
           throw new Exception("User does not exist in the database");
       }
        }
    @Override
    public void resetPassword(String email, String verificationcode) throws Exception {
        User user = userRepository.findByEmail(email);
        System.out.println("user.............." + user.getEmail());
        if (user != null) {
            Verification verification =
                    verificationRepository.findByUser(user);
//            //                findVerificationsByUserAndCodeOrderByGenerated_timeDesc(user);
//
//          //  Verification verification = verifications.get(0);
//            // check the time here
//            // check if it expires

            LocalDateTime verificationCreatedDate = verification.getGenerated_time();
            LocalDateTime currentDate = LocalDateTime.now();
            System.out.println("time difference= "+ChronoUnit.MINUTES.between(verificationCreatedDate, currentDate));

//
//            // it expired therefore generate a new one and send it
            if((ChronoUnit.MINUTES.between(verificationCreatedDate, currentDate) > 10)){
                throw new Exception("The verification code you entered has expired. request a new code.");
            }
            if(!verification.getCode().equals(verificationcode)){
                throw new Exception("Verification Code miss match .");
            }else {

               // user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                System.out.println("user....." + user.getPassword());

            }

        }else {
            throw new Exception("User does not exist in the database");
        }
    }

    }

