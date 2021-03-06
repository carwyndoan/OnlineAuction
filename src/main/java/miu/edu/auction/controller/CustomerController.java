package miu.edu.auction.controller;

import lombok.extern.java.Log;
import miu.edu.auction.domain.Category;
import miu.edu.auction.domain.Photo;
import miu.edu.auction.domain.Product;
import miu.edu.auction.model.PagerModel;
import miu.edu.auction.repository.CategoryRepository;
import miu.edu.auction.repository.PhotoRepository;
import miu.edu.auction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    PhotoRepository photoRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 4;
    private static final int[] PAGE_SIZES = {4, 8};

    @GetMapping("/home")
    public String main(Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
                       @RequestParam("page") Optional<Integer> page, @RequestParam(required = false, name = "sortVal") Integer sortVal,
                       @RequestParam(required = false, defaultValue = "0", name = "categoryId") Integer categoryId) {

        //List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();


        Optional<Integer> optSort = Optional.ofNullable(sortVal);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Sort sort = Sort.by("price").ascending();

        if (optSort.isPresent()) {
            if (optSort.get() == 1) {
                sort = Sort.by("price").ascending();
            }

            if (optSort.get() == 2) {
                sort = Sort.by("price").descending();
            }

            if (optSort.get() == 3) {
                System.out.println(" sort = Sort.by(\"product_id\").ascending();");
                sort = Sort.by("product_id").ascending();
            }

            if (optSort.get() == 4) {
                System.out.println("sort = Sort.by(\"product_id\").descending();");
                sort = Sort.by("product_id").descending();
            }
        }

        System.out.println("categoryId = " + categoryId);


        if (categoryId == 0) {
            System.out.println(" if ");

            Page<Product> products = productRepository.findAll(PageRequest.of(evalPage, evalPageSize, sort));
            PagerModel pager = new PagerModel(products.getTotalPages(), products.getNumber(), BUTTONS_TO_SHOW);
            model.addAttribute("photos", products);
            model.addAttribute("pager", pager);

        } else {

            System.out.println(" else ");

            Page<Product> productByCategory = productRepository.findByCategory(categoryId, PageRequest.of(evalPage, evalPageSize));
            PagerModel pager = new PagerModel(productByCategory.getTotalPages(), productByCategory.getNumber(), BUTTONS_TO_SHOW);
            model.addAttribute("photos", productByCategory);
            model.addAttribute("pager", pager);

        }

        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);

        model.addAttribute("categories", categories);


        return "home2"; //view

    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("searchValue") String s, @RequestParam("pageSize") Optional<Integer> pageSize,
                         @RequestParam("page") Optional<Integer> page) {
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Page<Product> products = productRepository.findByNameLike(s, PageRequest.of(evalPage, evalPageSize));
        List<Category> categories = categoryRepository.findAll();
//  Page<Product> productByCategory = productRepository.findByCategory(categoryId, PageRequest.of(evalPage, evalPageSize));

        model.addAttribute("photos", products);
        model.addAttribute("categories", categories);

        return "home2"; //view

    }


    @GetMapping("/detail")

    public String detail(Model model, @RequestParam("id") Integer id) {
        Product product = productRepository.findById(id).get();
        List<Photo> photoList = photoRepository.findProductId(id);

        System.out.println("photoList size = " + photoList.size());

        model.addAttribute("product", product);
        model.addAttribute("photoList", photoList);

        return "detail";
    }
}
