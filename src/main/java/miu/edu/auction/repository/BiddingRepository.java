package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Integer> {

    @Query(value = "select bid from Bidding bid "
            + "inner join fetch bid.product p "
            + "inner join p.user seller "
            + "inner join p.categories cat "
            + "left join fetch bid.bidding_activities ba "
            + "where seller.email <> :exclude_email "
            + "and (:category_id = 0 or cat.category_id = :category_id)"
            )
    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);

    @Query("select bid from Bidding bid inner join fetch bid.winner u where u.email = :email")
    List<Bidding> findByWinner(String email);

//    @Override
//    Optional<Bidding> findById(Integer integer);


    //    @Query("select bid from Bidding bid")
//    Collection<Bidding> findByWinner(@Param("user_id")Integer user_id);


//    SELECT ph FROM Employee e JOIN e.phones ph WHERE ph LIKE '1%'
}
