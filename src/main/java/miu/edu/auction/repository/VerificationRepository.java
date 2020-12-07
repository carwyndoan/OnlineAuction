package miu.edu.auction.repository;


import miu.edu.auction.domain.User;
import miu.edu.auction.domain.Verification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface VerificationRepository extends CrudRepository<Verification, Integer> {
//    List<Verification> findByUser_id(int user_id);
    Verification save(Verification verification);
    Verification findByUserAndType(User user, @NotNull int type);
}