package miu.edu.auction.repository;


import miu.edu.auction.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface UserRepository extends CrudRepository<User, Integer> {
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByEmail(String email);



    List<User> findByRegistrationVerified(int verification);
    int deleteByDriverLicense(String drivingLiscence);
    int findByDriverLicense(String driverLiscence);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.registrationVerified =1  " +
            "where u.driverLicense ='driverLiscence'")
    int updateByDrivingLiscence(String driverLiscence);


}