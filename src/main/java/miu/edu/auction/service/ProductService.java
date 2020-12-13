package miu.edu.auction.service;

 import miu.edu.auction.domain.Product;
 import org.springframework.security.core.Authentication;
 import org.springframework.web.multipart.MultipartFile;

 import java.util.List;
 import java.util.Optional;

public interface ProductService {


    Product addProduct(Product product);
    void deleteProduct(int p);
    Product updateProduct(Product product);
    Product getProduct(int id);
    List<Product> findByCategories_id();
    List<Product> getAllProductCustomer();
    List<Product> getAllProductSeller();






    }
