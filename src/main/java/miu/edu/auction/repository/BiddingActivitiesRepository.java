package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Bidding_Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingActivitiesRepository extends JpaRepository<Bidding_Activities, Integer> {

    @Query(value = "select max(a.amount) from Bidding_Activities a "
            + "inner join Bidding b "
            + "where b.bidding_id = :bidding_id")
    Double getMaxBidByBidding(Integer bidding_id);

    @Query("select act from Bidding_Activities act inner join fetch act.bidding_user u inner join fetch act.bidding b inner join fetch b.product product where b.bidding_id = :bidding_id")
    List<Bidding_Activities> findByBidding(Integer bidding_id);


    @Query(value = "select ba from Bidding_Activities ba "
            + "inner join fetch ba.bidding_user "
        + "where ba.bidding.bidding_id = :bidding_id "
        + "and ba.amount = :maxAmount")
    Bidding_Activities getMaxBiddingActivity(Integer bidding_id, Double maxAmount);

    @Query(value = "select ba from Bidding_Activities ba " +
            "where ba.bidding.bidding_id = :bidding_id")
    List<Bidding_Activities> findBidding_ActivitiesByBidding(Integer bidding_id);
}
