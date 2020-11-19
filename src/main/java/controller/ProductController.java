package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.ProductRepository;
import responses.BaseResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
          this.productRepository = productRepository;
    }

    @RequestMapping(value = "/save")
    public BaseResponse addProduct(@RequestBody Product p) {
        BaseResponse response = new BaseResponse();
        try{
            Date insertionDate = new Date();
            p.setDate(insertionDate);
            this.productRepository.save(p);

            response.setCode(200);
            response.setStatus("Data inserted with success");
            return response;
        } catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }
    
    @RequestMapping(value = "/get")
    public List<Product> getProduct(@RequestParam(value = "name", defaultValue = "") String name) {
        if(name.length() > 0){
            return this.productRepository.findByName(name);
        } else {
            return this.productRepository.findAll();
        }
    }

    @RequestMapping(value = "/update")
    public BaseResponse updateProduct(@RequestBody Product p){
        BaseResponse response = new BaseResponse();
        try {
        	String name = p.getName();
            Product oldP = productRepository.findOneByName(name);

            if(p.getDescription().length() > 0){
                oldP.setDescription(p.getDescription());
            }

            if(p.getPrice().length() > 0){
                oldP.setPrice(p.getPrice());
            }

            productRepository.save(oldP);

            response.setCode(200);
            response.setStatus("Product successfully updated.");

        }catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/delete")
    public BaseResponse deleteProduct(@RequestParam(value = "name", defaultValue = "") String name){
        BaseResponse response = new BaseResponse();

        try {
            if (name.length() > 0) {
                productRepository.delete(productRepository.findByName(name));
            }

            response.setCode(200);
            response.setStatus("Product successfully updated.");
        } catch (Exception e){
            response.setCode(e.hashCode());
            response.setStatus(e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/filter")
    public List<Product> filterProducts(@RequestBody String[] words) {
        List<Product> filteredProducts = new ArrayList<Product>();

        try {
            for(String word: words){
                filteredProducts.addAll(productRepository.findByNameContainingOrDescriptionContaining(word, word));
            }

            return filteredProducts;
        } catch (Exception e){
            return filteredProducts;
        }
    }

    @RequestMapping(value = "/category")
    public List<Product> getProductByCategory(@RequestParam(value = "category", defaultValue = "") String category){
        return productRepository.findByCategory(category);
    }
}
