package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.Bidding_Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BiddingActivitiesRepository extends JpaRepository<Bidding_Activities, Integer> {

    @Query(value = "select max(a.amount) from Bidding_Activities a "
            //+ "inner join Bidding b "
            //+ "where b.bidding_id = :bidding_id"
            + "where a.bidding.bidding_id = :bidding_id"
    )
    Double getMaxBiddingActivitiesByBidding(Integer bidding_id);

    @Query(value = "select ba from Bidding_Activities ba "
            + "inner join fetch ba.bidding_user "
        + "where ba.bidding.bidding_id = :bidding_id "
        + "and ba.amount = :maxAmount")
    Bidding_Activities getMaxBiddingActivity(Integer bidding_id, Double maxAmount);
}
