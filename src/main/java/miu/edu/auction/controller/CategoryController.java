package miu.edu.auction.controller;

import miu.edu.auction.domain.Category;
import miu.edu.auction.domain.Product;
import miu.edu.auction.service.ProductCategoryService;
import miu.edu.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    ProductCategoryService service;

    @Autowired
    ProductService productservice;

    @GetMapping("/welcome")
    public String welcomehome(Model model) {
        model.addAttribute("wel", "Auction");
        model.addAttribute("come", "Project");
        return "category/welcome";

    }

    @GetMapping("/addCategory")
    public String showSignUpForm(@ModelAttribute("category") Category category) {
        return "category/add-category";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());

            return "category/add-category";
        }

        service.addCategory(category);
        model.addAttribute("category", category);
        redirect.addFlashAttribute(category);
        return "redirect:/Categoryadded";
        // return "category/categoryDetail";
    }
        @GetMapping("/Categoryadded")
        public String categoryAdded(){
            return "category/categoryDetail";
        }


    @GetMapping("/category")
    public String getCategoryById(@RequestParam("id") int category_id, Model model) {
        model.addAttribute("category", service.getCategory(category_id));
        return "category/add-category";
    }

    // additional CRUD methods
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int category_id, Model model) {
        Category category = service.getCategory(category_id);
        model.addAttribute("category", category);
        return "category/update-category";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int category_id, @Valid Category category,
                             BindingResult result, Model model) {
//         Category cate= service.getCategory(category_id);
//         if(cate==null){
//             throw new CategoryNotFoundException("message");
//                     throw new IllegalArgumentException(new CategoryNotFoundException(""))

//         }

         service.updateCateogry(category);
        model.addAttribute("categories", service.getAllCategories());
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        List<Product> lists= productservice.findByCategories_id();
       if(lists==null){
           service.deleteCategory(id);
       }
         else
             model.addAttribute("erros", "it has product");
         return "category/categoryList";
    }

    @GetMapping("/categories")
    public String list(Category category, Model model) {
        List<Category> categoryList = service.getAllCategories();
        model.addAttribute("categories", categoryList);

        return "category/categoryList";
    }
}


