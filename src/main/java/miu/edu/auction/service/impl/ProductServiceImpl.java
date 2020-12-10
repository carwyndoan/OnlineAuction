package miu.edu.auction.service.impl;

import miu.edu.auction.domain.Product;
import miu.edu.auction.repository.ProductRepository;
import miu.edu.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repo;
    @Override
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) repo.findAll();
    }

    @Override
    public void deleteProduct(int id) {
        repo.deleteById(id);


    }

    @Override
    public Product updateProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public Product getProduct(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCategories_id() {
        return null;//repo.findByCategories_id();
    }

}
