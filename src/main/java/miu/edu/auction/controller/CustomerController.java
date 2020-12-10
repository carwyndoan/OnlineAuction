package miu.edu.auction.controller;

import lombok.extern.java.Log;
import miu.edu.auction.domain.Category;
import miu.edu.auction.domain.Product;
import miu.edu.auction.repository.CategoryRepository;
import miu.edu.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log
public class CustomerController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String main(Model model) {

        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("photos", products);
        model.addAttribute("categories", categories);

        return "home2"; //view

    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("searchValue") String s) {

        List<Product> products = productRepository.findByNameLike(s);
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("photos", products);
        model.addAttribute("categories", categories);

        return "home2"; //view

    }


    @GetMapping("/detail")

    public String detail(Model model) {
        return "detail";

    }
}
