package miu.edu.auction.repository;

import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.domain.PayPalOutData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaypalOutDataRepository extends JpaRepository<PayPalOutData, Integer> {
//    @Query(value = "select p from PayPalOutData p " +
//            "where p.order_id = :order_id")
//    PayPalOutData findPayPalOutDataByOrderId(String order_id);
//
//    @Query(value = "select p from PayPalOutData p " +
//            "where p.bidding_id = :bidding_id")
//    List<PayPalOutData> findPayPalOutDataByBiddingId(Integer bidding_id);
//
//    @Query(value = "select p from PayPalOutData p " +
//            "where p.confirm_id = :capture_id")
//    PayPalOutData findPayPalOutDataByConfirmId(String capture_id);
//
//    @Query(value = "select p from PayPalOutData p " +
//            "where p.authorization_id = :authorize_id")
//    PayPalOutData findPayPalOutDataByAuthorizationId(String authorize_id);

    @Query(value = "select p from PayPalOutData p " +
            "where p.local_payment_id = :local_payment_id")
    List<PayPalOutData> findPayPalOutDataByLocalPaymentId(Integer local_payment_id);
}
