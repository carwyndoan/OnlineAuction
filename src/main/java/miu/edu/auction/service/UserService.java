package miu.edu.auction.service;

import miu.edu.auction.domain.User;


public interface UserService {

    User findUserByEmail(String email);
    User saveUser(User user);
    public User saveUserWithVerificationKey(User user);
}