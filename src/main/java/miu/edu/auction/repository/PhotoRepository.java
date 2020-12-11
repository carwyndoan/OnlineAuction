package miu.edu.auction.repository;

import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query(value = "select p from Photo p "
            + "where p.product_id = :productId")
    List<Photo> findProductId(Integer productId);
}
