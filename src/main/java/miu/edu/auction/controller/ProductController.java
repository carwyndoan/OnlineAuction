package miu.edu.auction.controller;//package miu.edu.auction.controller;//package miu.edu.auction.controller;

 import miu.edu.auction.domain.Product;
  import miu.edu.auction.service.BiddingService;
 import miu.edu.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.core.io.ByteArrayResource;
 import org.springframework.http.HttpHeaders;
 import org.springframework.http.MediaType;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
 import org.springframework.util.StringUtils;
 import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
 import org.springframework.web.multipart.MultipartFile;
 import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
 import java.awt.*;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Paths;
 import java.nio.file.StandardCopyOption;
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


//    @PostMapping("/addproduct")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "product/add-product";
        }
        productService.addProduct(product);
        model.addAttribute("product", product);
        redirect.addFlashAttribute(product);
        return  "redirect:/Productadded";

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
    public void deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);

}

//    @GetMapping("/getfile")
//   public String get(Model model){
//    List<Product> docs=productService.getFiles();
//    model.addAttribute("docs" , docs);
//    return "doc";
//    }
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
    @PostMapping("/getfile")
    public String uploadMultipleFiles(@RequestParam("files")MultipartFile[] files){
    for(MultipartFile file:files) {
        productService.saveFile(file);
    }
    return "redirect:/";
}
//    @GetMapping("/downloadFile/{fileId}")
//public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
//    Product doc=productService.getFile(fileId).get();
//    return ResponseEntity.ok()
//            .contentType(MediaType.parseMediaType(doc.getDocType()))
//            //.headers(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=" + doc.getDocName())
//            .header(HttpHeaders.CONTENT_DISPOSITION,"attachement:filename=\""+doc.getDocName()+"\"")
//            .body(new ByteArrayResource(doc.getData()));
//
//}
}