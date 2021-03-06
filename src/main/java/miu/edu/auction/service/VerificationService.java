package miu.edu.auction.service;

import miu.edu.auction.domain.Verification;

import java.util.List;

public interface VerificationService {
    List<Verification> findByUser(int user_id);
   // Verification saveVerification(Verification verification);
    Verification findByUserAndType(int user_id, int type);

    void resetPassword(String userEmail, String verificationcode, String password) throws Exception;
    void resetPassword(String email, String verificationcode) throws Exception;
    //void resetPassword(String userEmail, String verificationcode, String password) throws Exception;
}
