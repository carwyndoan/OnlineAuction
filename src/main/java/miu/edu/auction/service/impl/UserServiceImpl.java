package miu.edu.auction.service.impl;

import miu.edu.auction.domain.User;
import miu.edu.auction.domain.Verification;
import miu.edu.auction.repository.UserRepository;
import miu.edu.auction.repository.VerificationRepository;
import miu.edu.auction.service.UserService;
import miu.edu.auction.service.VerificationService;
import miu.edu.auction.utils.GenerationUnique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationRepository verificationRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User saveUserWithVerificationKey(User user) {
        //Save User
        User savedUser = saveUser(user);
        //Save Verification with UniqueNumber for verification
        Verification verification = new Verification();
        verification.setCode(GenerationUnique.hash(savedUser.getEmail()));
        verification.setGenerated_time(LocalDateTime.now());
        verification.setUser(savedUser);
        verification.setType(0);
        verification.setStatus(0);
        verificationRepository.save(verification);
        //Send Email
        return savedUser;
    }

}
