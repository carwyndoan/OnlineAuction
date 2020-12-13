package miu.edu.auction.repository;

import miu.edu.auction.domain.PayPalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaypalDataRepository extends JpaRepository<PayPalData, Integer> {
    @Query(value = "select p from PayPalData p " +
            "where p.order_id = :order_id")
    PayPalData findPayPalDataByOrderId(String order_id);

    @Query(value = "select p from PayPalData p " +
            "where p.bidding_id = :bidding_id")
    List<PayPalData> findPayPalDataByBiddingId(Integer bidding_id);
}
