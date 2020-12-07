package miu.edu.auction.repository;


import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface UserRepository extends CrudRepository<User, Integer> {
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByEmail(String email);
}