package miu.edu.auction.repository;

import miu.edu.auction.domain.DMV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DMVRepository extends JpaRepository<DMV,Long> {
    DMV findByNameAndLicense(String name, String license);
//    @Query(value = "select * from DMV where name like:'name' and like :'license'",nativeQuery = true )
//    DMV findByName(String name, String license);

}
