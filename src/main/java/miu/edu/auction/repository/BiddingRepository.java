package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.BiddingDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingRepository extends CrudRepository<Bidding, Integer> {

    @Query(value = "select bid from Bidding bid "
            + "inner join fetch bid.product p "
            + "inner join p.user seller "
            + "inner join p.categories cat "
            + "left join fetch bid.bidding_activities ba "
            + "where seller.email <> :exclude_email "
            + "and (:category_id = 0 or cat.category_id = :category_id)"
            )
    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);

    @Query(value = "select bid from Bidding bid inner join fetch  User u where u.user_id= ?1")
    List<Bidding> findByWinner(@Param("user_id")Integer user_id);

//    SELECT ph FROM Employee e JOIN e.phones ph WHERE ph LIKE '1%'
}