package miu.edu.auction.service;

 import miu.edu.auction.domain.Product;

import java.util.List;

public interface ProductService {


    Product addProduct(Product product);
    List<Product> getAllProduct();
    void deleteProduct(int p);
    Product updateProduct(Product product);
    Product getProduct(int id);
    List<Product> findByCategories_id();

}
