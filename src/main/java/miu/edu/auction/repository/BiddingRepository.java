package miu.edu.auction.repository;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Integer> {

    @Query(value = "select bid from Bidding bid "
            + "inner join fetch bid.product p "
            + "inner join fetch p.user seller "
            + "inner join p.categories cat "
            //+ "left join fetch bid.bidding_activities ba "
            //+ "where seller.enable = 1 and seller.profile_verified = 1 and seller.registration_verified = 1 "
            + "where seller.email <> :exclude_email "
            + "and (:category_id = 0 or cat.category_id = :category_id) "
            + "and bid.winner is null "
    )
    List<Bidding> findBiddingByCategory(Integer category_id, String exclude_email);

    //    @Query("select bid from Bidding bid inner join fetch bid.winner u where u.email = :email")
    @Query("select bid from Bidding bid inner join fetch bid.winner u inner join bid.payments p where bid.payment_duedate >= :paymentDate and u.email = :email and p.paymentDate is null")
    List<Bidding> findByWinner(String email, LocalDateTime paymentDate);

    @Query("select bid from Bidding bid inner join fetch bid.payments p inner join fetch p.user_payment u where u.email = :email")
    List<Bidding> findByUserBidding(String email);

    @Override
    @Query(value = "select bid from Bidding bid "
            + "inner join fetch bid.product pr "
            //+ "inner join fetch bid.payments p "
            //+ "inner join fetch bid.bidding_activities ba "
            + "left join fetch bid.winner win "
            + "where bid.bidding_id = :integer")
    Optional<Bidding> findById(Integer integer);
}
