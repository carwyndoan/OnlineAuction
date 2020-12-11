package miu.edu.auction.service;

import miu.edu.auction.domain.Category;

import java.util.List;

public interface ProductCategoryService {

    Category addCategory(Category category);
    List<Category> getAllCategories();
    void deleteCategory(int category_id);
    Category updateCateogry(Category category);
    Category getCategory(int category_id);


}
