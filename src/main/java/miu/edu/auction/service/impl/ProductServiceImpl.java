package miu.edu.auction.service.impl;

import miu.edu.auction.domain.Product;
import miu.edu.auction.repository.ProductRepository;
import miu.edu.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import java.util.ArrayList;
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
    public List<Product> getAllProductSeller() {
         List<Product> products = (List<Product>) repo.findAll();
        return products;
    }

    @Override
    public List<Product> getAllProductCustomer() {
        List<Product> newProducts = new ArrayList<>();

        List<Product> products = (List<Product>) repo.findAll();

        for (Product product : products) {
            if (product.getStatus() == 1)               //filter status
                newProducts.add(product);
        }

        return newProducts;
    }
//    @Override
//    public List<Product> getAllProductWithRole(String role) {
//
//        List<Product> newProducts=new ArrayList<>();
//        List<Product> products = (List<Product>)repo.findAll();
//
//        for(Product product:products){
//            if(product.getStatus()==0)
//                newProducts.add(product);
//        }

//        if(role=="ROLE_USER")
//            return newProducts;
//        else
//            return products;
//       return products;
//}


    @Override
    public void deleteProduct(int id) {
        Product product = getProduct(id);
        int bidStatus = product.getBidding().getStatus();

        if (product.getStatus() == 1 && bidStatus == 0)
            repo.deleteById(id);


    }

    @Override
    public Product updateProduct(Product product) {
        int bidStatus = product.getBidding().getStatus();
        int status = product.getStatus();
        if (status == 1 && bidStatus == 0)
            return repo.save(product);
        return null;
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
//         @Override
//         public Product saveFile(MultipartFile file){
//            String docname=file.getOriginalFilename();
//            try{
////                Product product=new Product(docname, file.getContentType(),file.getBytes());
//            }catch (Exception e){
//                 e.printStackTrace();
//            }
//            return null;


//         @Override
//         public Optional<Product> getFile(Integer fileId){
//           return repo.findById(fileId);
//         }
//         @Override
//         public List<Product> getFiles(){
//        return (List<Product>) repo.findAll();
//         }

