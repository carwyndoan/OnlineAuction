package miu.edu.auction.repository;

import miu.edu.auction.domain.PayPalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaypalDataRepository extends JpaRepository<PayPalData, Integer> {
    @Query(value = "select p from PayPalData p " +
            "where p.order_id = :order_id")
    PayPalData findPayPalDataByOrderId(String order_id);

    @Query(value = "select p from PayPalData p " +
            "where p.bidding_id = :bidding_id")
    List<PayPalData> findPayPalDataByBiddingId(Integer bidding_id);

    @Query(value = "select p from PayPalData p " +
            "where p.confirm_id = :capture_id")
    PayPalData findPayPalDataByConfirmId(String capture_id);

    @Query(value = "select p from PayPalData p " +
            "where p.authorization_id = :authorize_id")
    PayPalData findPayPalDataByAuthorizationId(String authorize_id);

    @Query(value = "select p from PayPalData p " +
            "where p.local_payment_id = :local_payment_id")
    List<PayPalData> findPayPalDataByLocalPaymentId(Integer local_payment_id);
}
