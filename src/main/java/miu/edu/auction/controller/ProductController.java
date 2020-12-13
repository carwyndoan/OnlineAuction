package miu.edu.auction.controller;

 import miu.edu.auction.domain.Product;
 import miu.edu.auction.service.BiddingService;
 import miu.edu.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.util.StringUtils;
 import org.springframework.validation.BindingResult;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 import javax.validation.Valid;
  import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.nio.file.StandardCopyOption;
  import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    BiddingService biddingService;

    @GetMapping("/addproduct")
    public String getProductForm(@ModelAttribute("product") Product product) {
        return "Product/add-product";

    }

//    @PostMapping("/addproduct")
//    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            model.addAttribute("errors", result.getAllErrors());
//            return "Product/add-product";
//        }
//        productService.addProduct(product);
//        model.addAttribute("product", product);
//        redirect.addFlashAttribute(product);
//        return  "redirect:/Productadded";
//
//    }
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

//    @GetMapping("/products")
//    public String findAll(Product product, Model model,Principal principal ) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String role= auth.getAuthorities().toString();
//
////        List<Product> productList = productService.getAllProduct();
//        List<Product> productList = productService.getAllProductWithRole(role);
//        model.addAttribute("products", productList);
//        return "product/productList";
//    }
      @GetMapping("/productsCustomer")
      public String customerfindAll(Product product, Model model  ) {
        List<Product> productList = productService.getAllProductCustomer();
         model.addAttribute("products", productList);
        return "product/productList";
    }
    @GetMapping("/productsSeller")
    public String productfindAll(Product product, Model model  ) {
        List<Product> productList = productService.getAllProductSeller();
        model.addAttribute("products", productList);
        return "product/productList";
    }


    @GetMapping("/product")
    public String getProductById(@RequestParam("id") int productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        return "/Product/product";
    }

    @GetMapping("/product{Id}")
    public void deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
    }


@PostMapping("/addproduct")
public String addProductImage(@Valid @ModelAttribute("product") Product product,
                              BindingResult result, Model model, RedirectAttributes redirect,
                              @RequestParam("files")MultipartFile file) throws IOException {

    if (result.hasErrors()) {
        model.addAttribute("errors", result.getAllErrors());
        return "";
    }

    String directory=Paths.get("").toAbsolutePath().toString();
    System.out.println("directory= "+product.getStatus());

    String fileName= StringUtils.cleanPath(file.getOriginalFilename());

    Files.copy(file.getInputStream(), Paths.get(directory+"\\src\\main\\resources\\static\\images\\"+fileName), StandardCopyOption.REPLACE_EXISTING);

    product.setImage_path(fileName);
//    product.setUser(user);
    productService.addProduct(product);
    model.addAttribute("product", product);
    redirect.addFlashAttribute(product);
    return  "redirect:/Productadded";

}
 }