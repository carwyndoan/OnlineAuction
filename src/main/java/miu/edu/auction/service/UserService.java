package miu.edu.auction.service;

import miu.edu.auction.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface UserService {

    User findUserByEmail(String email);
    Optional<User> findById(int id);
    User saveUser(User user);
    public User saveUserWithVerificationKey(User user);
    public Page<User> findUserListPaging(Pageable pageable);
}