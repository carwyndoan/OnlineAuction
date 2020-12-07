package miu.edu.auction.service.impl;

import lombok.Setter;
import miu.edu.auction.domain.Verification;
import miu.edu.auction.repository.VerificationRepository;
import miu.edu.auction.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {
    @Autowired
    VerificationRepository verificationRepository;

    @Override
    public List<Verification> findByUser(int user_id) {
//        return verificationRepository.findByUser_id(user_id);
        return null;
    }

    @Override
    public Verification saveVerification(Verification verification) {
        return verificationRepository.save(verification);
    }

    @Override
    public Verification findByUserAndType(int user_id, int type) {
        return null;
    }
}
