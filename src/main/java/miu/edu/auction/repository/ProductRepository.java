package miu.edu.auction.repository;

import miu.edu.auction.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
  //  List<Product> findAll();

    @Query("Select p from Product p where p.description like %:str%")
    Page<Product> findByNameLike(String str,Pageable pageable);

    @Query("Select p from Product p JOIN p.categories c where c.category_id=:categoryId")
    Page<Product> findByCategory(Integer categoryId, Pageable pageable);


  //  boolean findByBiddingContainsAndStatus();
}
