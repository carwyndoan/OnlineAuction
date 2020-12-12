package miu.edu.auction.service.impl;


import miu.edu.auction.domain.Category;
import miu.edu.auction.repository.CategoryRepository;
import miu.edu.auction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryRepository repo;

    @Override
    public Category addCategory(Category category) {
        for(Category c:getAllCategories()) {
            if (c.equals(category)) {
                System.out.println("Name= "+category.getName());
                return null;
            }
        }
            System.out.println("descrip= "+category.getDescription());
            return repo.save(category);
    }
    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();

    }

    @Override
    public void deleteCategory(int category_id) {
            repo.deleteById(category_id);
        //  .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


    }

    @Override
    public Category updateCateogry(Category category) {

        return repo.save(category);
    }

    @Override
    public Category getCategory(int category_id) {
        return repo.findById(category_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + category_id));

    }
}
