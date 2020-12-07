package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingRepository extends CrudRepository<Bidding, Integer> {

    @Query("select bid from Bidding bid inner join Product p inner join Category c inner join User u " +
            "where c.category_id = :category_id and u.email <> :exclude_email")
    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);

    List<Bidding> findAllByWinner(Integer user_id);
}
