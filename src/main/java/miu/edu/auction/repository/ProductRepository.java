package miu.edu.auction.repository;

import miu.edu.auction.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    @Query("Select p from Product p where p.description like %:str%")
    List<Product> findByNameLike(String str);
}
