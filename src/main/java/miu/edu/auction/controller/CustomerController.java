package miu.edu.auction.controller;

import lombok.extern.java.Log;
import miu.edu.auction.domain.Category;
import miu.edu.auction.domain.Product;
import miu.edu.auction.model.PagerModel;
import miu.edu.auction.repository.CategoryRepository;
import miu.edu.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
@Log
public class CustomerController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 4;
    private static final int[] PAGE_SIZES = {4, 8};

    @GetMapping("/home")
    public String main(Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
                       @RequestParam("page") Optional<Integer> page) {

        //List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;


        Page<Product> products = productRepository.findAll(PageRequest.of(evalPage, evalPageSize));
        PagerModel pager = new PagerModel(products.getTotalPages(),products.getNumber(),BUTTONS_TO_SHOW);

        model.addAttribute("photos", products);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

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

    public String detail(Model model,@RequestParam("id") Integer id ) {
        Product product = productRepository.findById(id).get();

        model.addAttribute("product", product);

        return "detail";
    }
}
