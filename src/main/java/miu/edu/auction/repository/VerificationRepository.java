package miu.edu.auction.repository;


import miu.edu.auction.domain.User;
import miu.edu.auction.domain.Verification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface VerificationRepository extends CrudRepository<Verification, Integer> {
    Verification findByUserAndType(User user, @NotNull int type);

    Verification findByUser(User user);
    //List<Verification> findVerificationsByUserOrderByGenerated_timeDesc(User user);
    //findVerificationByUserAndVerificationCodeTypeOrderByDateDesc(User user,VerificationCodeType VerificationCodeType.PASSWORD_RESET);
}