package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingRepository extends CrudRepository<Bidding, Integer> {

    @Query(value = "select bid from Bidding bid inner join Product p inner join Category c inner join User u join fetch bid.bidding_activities " +
            "where (c.category_id = :category_id or :category_id = 0) " +
            "and u.email <> :exclude_email " +
            "and u.enable = 1 " +
            "and u.registration_verified = 1" +
            "and u.profile_verified = 1")
    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);

}