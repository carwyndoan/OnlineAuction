package miu.edu.auction.controller;//package miu.edu.auction.controller;//package miu.edu.auction.controller;

 import miu.edu.auction.domain.Product;
  import miu.edu.auction.service.BiddingService;
 import miu.edu.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
 import java.security.Principal;
 import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    BiddingService biddingService;

    @GetMapping("/addproduct")
    public String getProductForm(@ModelAttribute("product") Product product) {
        return "Product/add-product";

    }


    @PostMapping("/addproduct")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "Product/add-product";
        }
        productService.addProduct(product);
        model.addAttribute("product", product);
        redirect.addFlashAttribute(product);
        return "redirect:/Productadded";
    }

    @GetMapping("/Productadded")
    public String productAdded() {
        return "Product/productDetail";
    }


    @PostMapping(value = {"/updateuser"})
    public String updateProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/Product/add-product";
        productService.updateProduct(product);
        return "Product/productDetail";
    }

    @GetMapping("/products")
    public String findAll(Product product, Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("products", productList);
        return "product/productList";
    }

    @GetMapping("/product")
    public String getProductById(@RequestParam("id") int productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        return "/Product/product";
    }

    @GetMapping("/product{Id}")
    public void deleteProduct(@PathVariable("") int id) {
        Product product = productService.getProduct(id);
        // switch (ReleaseStatus.SAVEANDRELEASE) {
        // productService.deleteProduct(id);
    }
//    @GetMapping("/delete")
//    public String deleteProduct(@RequestParam("Id") int productId, Model model, Principal principal, RedirectAttributes redirectAttributes) {
//        Product product;
//        Product.productStatus status = Product.productStatus.SAVEANDRELEASE;
//        List<Product> products = null;
//        try {
//           // product = productService.getProduct(productId);//productService.find(productId);
//            //products = biddingService.fin//itemService.findAllByItemStatusAndProduct(itemStatus, product);
//            if (products.size() >= 1) {
//                redirectAttributes.addFlashAttribute("errorMessage", "You can not deleted this product , Order History exist  ");
//
//            } else {
//               productService.deleteProduct(productId);  //categoryService.deleteProduct(product);
//                redirectAttributes.addFlashAttribute("successMessage", "Product deleted  Successfully ");
//            }
//
//        } catch (RuntimeException ex) {
//            redirectAttributes.addFlashAttribute("errorMessage", "You can not deleted this product , Error  Occurred  ");
//
//        }
//        return "redirect:/seller/productsList";
//
//    }
}




