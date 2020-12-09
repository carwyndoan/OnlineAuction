package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment save(Payment payment);

    @Query("select p from Payment p inner join fetch p.biddingPayment b inner join fetch p.user_payment u where b.bidding_id = :biddingID and u.user_id = :userID")
    Payment findPaymentByBiddingID(Integer biddingID, Integer userID);
}
