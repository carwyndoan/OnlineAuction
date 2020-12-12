package miu.edu.auction.service;

 import miu.edu.auction.domain.Product;
 import org.springframework.web.multipart.MultipartFile;

 import java.util.List;
 import java.util.Optional;

public interface ProductService {


    Product addProduct(Product product);
    List<Product> getAllProduct();
    void deleteProduct(int p);
    Product updateProduct(Product product);
    Product getProduct(int id);
    List<Product> findByCategories_id();
    Product saveFile(MultipartFile file);
    Optional<Product> getFile(Integer fileId);
    List<Product> getFiles();




}
